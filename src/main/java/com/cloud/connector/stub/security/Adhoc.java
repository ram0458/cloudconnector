package com.cloud.connector.stub.security;

import com.cloud.connector.model.CloudDataProcess;
import com.cloud.connector.pojo.TableMetaDataResPo;
import com.cloud.connector.stub.catalog.CatalogServiceStub;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.activation.DataHandler;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Slf4j
public class Adhoc {
    @Autowired
    FileTransferHelper fileTransferHelper;

    public void adhocMethod(
            CloudDataProcess cloudDataProcessPo, String requestId,
            CatalogServiceStub catalogStub, String loginResponseToken,
            String soapUrl, Connection con, String extractionFlag) throws Exception {
        Long batchSize;
        Long limit;
        int offset = 0;
        int mapSetId = 0;
        batchSize = cloudDataProcessPo.getBatchSize();
        limit = cloudDataProcessPo.getBatchSize();
        int fileCount = 1;
        String tableName=cloudDataProcessPo.getTableName();
        tableName=Utils.validTableName(tableName);
        while (batchSize == limit) {
            String offsetQuery = null;

            if (batchSize != 0) {
                offsetQuery = cloudDataProcessPo.getSqlQuery() + " OFFSET " + offset + " ROWS FETCH NEXT " + limit + " ROWS ONLY";
            } else {
                offsetQuery = cloudDataProcessPo.getSqlQuery();
            }
            TableMetaDataResPo tableMetaDataResPo = null;
            //     log.info(offsetQuery);
            JSONObject xmlJSONObj = Utils.bipSoapCall(requestId, catalogStub, loginResponseToken, soapUrl, offsetQuery);
            if(cloudDataProcessPo.getMetaData() == null) {
                tableMetaDataResPo = metaData(tableName, catalogStub, soapUrl, loginResponseToken);
            }
            int PRETTY_PRINT_INDENT_FACTOR = 4;
            log.debug("xmlJSONObj:::::::" + xmlJSONObj);
            String jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
            log.debug("jsonPrettyPrintString:::::::" + jsonPrettyPrintString);
            // CSV File Transfer to RemoteLocation
            String csvName = tableName + "_" + fileCount;
            FileTransferResponsePo fileTransferResponsePo = fileTransferHelper.fileTransferHelper(jsonPrettyPrintString, csvName, mapSetId, extractionFlag);
            batchSize = fileTransferResponsePo.getRowCount();
            if (batchSize != 0) {
               Utils.insertIntoTables(con, Utils.validMetaData(cloudDataProcessPo.getMetaData()), fileTransferResponsePo,tableName,tableMetaDataResPo);
            }
            if (batchSize > 0) {
                offset += limit;
            }
            fileCount += 1;
        }
    }

    public static TableMetaDataResPo metaData(String tableName, CatalogServiceStub catalogStub, String soapUrl,
                                              String loginResponseToken) throws Exception {
        String tempDataModelUrl = "";
        String tempCreateReportResponse = "";
        TableMetaDataResPo tableMetaDataResPo = new TableMetaDataResPo();
        StringBuffer sb = new StringBuffer();
        try {
            String modifiedSqlQuery = "SELECT C.COLUMN_ID, C.COLUMN_NAME,C.TABLE_NAME, C.DATA_TYPE, C.DATA_LENGTH,C.DATA_PRECISION,C.NULLABLE FROM ALL_TAB_COLS C WHERE C.TABLE_NAME = '"
                    + tableName + "' order by internal_Column_id ";

            UUID uuid = UUID.randomUUID();
            String tempRequestId = uuid.toString();
            System.out.println("tempRequestId:::" + tempRequestId);

            // Service call for creating Data Model
            tempDataModelUrl = LoginCreateDataModelHelper.createDataModel(catalogStub, tempRequestId,
                    loginResponseToken, modifiedSqlQuery);

            log.info("tempDataModelUrl:::::" + tempDataModelUrl);

            // Service call for creating report
            tempCreateReportResponse = ReportHelper.createReportService(soapUrl, loginResponseToken, tempRequestId,
                    tempDataModelUrl);
            log.info("tempCreateReportResponse:::::::::::" + tempCreateReportResponse);

            // service call to RunReport
            DataHandler dt = ReportHelper.runReport(soapUrl, loginResponseToken, tempCreateReportResponse);
            String xmlOutput = new BufferedReader(new InputStreamReader(dt.getDataSource().getInputStream())).lines()
                    .collect(Collectors.joining("\n"));
            log.info("xmlOutput:::::" + xmlOutput);
            // xml to JsonObject
            JSONObject xmlJSONObj = XML.toJSONObject(xmlOutput);
            JSONArray arr = xmlJSONObj.getJSONObject("ROWSET").getJSONArray("ROW");

            String columnName = "";
            String dataType = "";
            int dataLength;
            // Number precision;
            StringBuffer columNamesSb = new StringBuffer();
            for (int i = 0; i < arr.length(); i++) {
                columnName = arr.getJSONObject(i).getString("COLUMN_NAME");
                dataType = arr.getJSONObject(i).getString("DATA_TYPE");
                dataLength = arr.getJSONObject(i).getInt("DATA_LENGTH");

                columNamesSb.append(columnName);
                // precision= arr.getJSONObject(i).getNumber("DATA_PRECISION");
                if (dataType.contains("CHAR"))
                    sb.append(columnName + "   " + dataType + "(" + dataLength + ")");
                else if (dataType.contains("NUMBER"))
                    sb.append(columnName + "   " + dataType + "(" + dataLength + ")");
                else if (dataType.contains("DATE") || dataType.contains("TIMESTAMP"))
                    sb.append(columnName + "   VARCHAR2(245)");
                else if (dataType.contains("CLOB") )
                    sb.append(columnName + "   VARCHAR2(32000)");
                else
                    sb.append(columnName + "  " + dataType);
                if (i != arr.length() - 1) {
                    sb.append(",");
                    columNamesSb.append(",");
                }
            }
            tableMetaDataResPo.setColumnNames(columNamesSb.toString());
            tableMetaDataResPo.setMetaData(sb.toString());
            if (tempDataModelUrl != null && tempDataModelUrl != "") {
                log.info("tempDataModelUrl:::::::" + tempDataModelUrl + "loginResponseToken:::::::::::"
                        + loginResponseToken);
                boolean deleteTmpDataModelResp = LoginCreateDataModelHelper.deleteObject(catalogStub, tempDataModelUrl,
                        loginResponseToken);
                log.info("deleteTmpDataModelResp Response::::" + deleteTmpDataModelResp);
            }
            // Service call for deleting Report
            if (tempCreateReportResponse != null && tempCreateReportResponse != "") {
                boolean deleteTmpReportResp = LoginCreateDataModelHelper.deleteObject(catalogStub,
                        tempCreateReportResponse, loginResponseToken);
                log.info("deletedTempReport Response::::" + deleteTmpReportResp);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            if (tempDataModelUrl != null && tempDataModelUrl != "") {
                log.info("tempDataModelUrl:::::::" + tempDataModelUrl + "loginResponseToken:::::::::::"
                        + loginResponseToken);
                // Service call for deleting Data Model
                boolean deleteDataModelResp = LoginCreateDataModelHelper.deleteObject(catalogStub, tempDataModelUrl,
                        loginResponseToken);
                log.info("deletedDataModel Response::::" + deleteDataModelResp);
            }
            if (tempCreateReportResponse != null && tempCreateReportResponse != "") {
                // Service call for deleting Report
                boolean deleteReportResp = LoginCreateDataModelHelper.deleteObject(catalogStub,
                        tempCreateReportResponse, loginResponseToken);
                log.info("deletedReport Response::::" + deleteReportResp);
            }
            throw new Exception(e.getMessage());
        }
        return tableMetaDataResPo;
    }
}
