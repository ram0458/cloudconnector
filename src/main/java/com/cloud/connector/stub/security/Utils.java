package com.cloud.connector.stub.security;

import com.cloud.connector.pojo.TableMetaDataResPo;
import com.cloud.connector.stub.catalog.CatalogServiceStub;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.opencsv.CSVReader;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import java.io.*;
import java.sql.*;
import java.util.Properties;

@Component
@Slf4j
public class Utils {
    @Value("${file-upload-dir}")
    private String fileUploadDir;

    @Value("${sftp-client-host}")
    private String sftpClientHost;

    @Value("${sftp-client-username}")
    private String sftpClientUsername;

    @Value("${sftp.privatekey.path}")
    private String sftpPrivatePath;
    @Value("${sftp-client-password}")
    private String sftpClientPassword;


    public static String modifyQuery(String query) throws Exception {
        String subString = "";
        try {
            String sqlQuery = query.toUpperCase();
            log.info("sqlQuery##########" + sqlQuery);
            if (sqlQuery.contains("DISTINCT"))
                subString = sqlQuery.substring(sqlQuery.indexOf("DISTINCT") + 8, sqlQuery.indexOf("FROM")).trim();
            else
                subString = sqlQuery.substring(sqlQuery.indexOf("SELECT") + 6, sqlQuery.indexOf("FROM")).trim();
            log.info("subString:::::" + subString);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }
        return subString;
    }

	public static String validTableName(String tableName) {
		return tableName.replaceAll("[^a-zA-Z0-9_$]", "");
	}

	public static String validColumnNames(String columnNames) {
		return columnNames.replaceAll("[^a-zA-Z0-9_,$]", "");
	}
	public static String validFileName(String fileName) {
		return fileName.replaceAll("[^a-zA-Z0-9_.-]", "");
		
	}
	public static String validMetaData(String metaData) {
		return metaData.replaceAll("[^a-zA-Z0-9_(),\\s+\r\n]", "");
	}
    public static String converStringArrtoString(String[] strArr) throws Exception {
        StringBuilder sb = new StringBuilder();
        try {
            for (String s : strArr)
                sb.append(s).append(",");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }
        return sb.substring(0, sb.length() - 1);
    }

    public static String convertingJsontoCsv(String jsonPrettyPrintString, String requestId, String dynamicQuery) throws Exception {

        JSONObject output = new JSONObject(jsonPrettyPrintString);
        JSONArray arr = null;
        JSONObject g1Obj = null;
        String csv = "";
        JSONObject dataDsObj = output.optJSONObject("ROWSET");
        if (dataDsObj != null)
            arr = dataDsObj.optJSONArray("ROW");
        if (dataDsObj != null)
            g1Obj = dataDsObj.optJSONObject("ROW");
        System.out.println(arr + "arr::::::::");
        System.out.println(g1Obj + "g1Obj:::::::::;");
        File file = new File(requestId + ".csv");
        if (arr != null) {
            csv = CDL.toString(arr);
        } else if (g1Obj != null) {
            JSONArray jsonArr = new JSONArray();
            jsonArr.put(g1Obj);
            // System.out.println("enetringesifffffff::::::");
            // csv=g1Obj.toString();
            csv = CDL.toString(jsonArr);
            // System.out.println(csv+":::::::::csv");
        } else {
            // System.out.println("enetringesleeeeeeee:::::::");
            csv = modifyQuery(dynamicQuery);
            System.out.println(csv + ":::::::::csv");
            if (csv.contains(".")) {
                String[] columnNames = csv.split(",");
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < columnNames.length; i++) {
                    if (columnNames[i].contains(".")) {
                        String[] columnNameArr = columnNames[i].split("\\.");
                        columnNames[i] = columnNameArr[1];
                        sb.append(columnNames[i]);
                        if (i != columnNames.length - 1)
                            sb.append(",");
                    }
                }
                csv = sb.toString();
            }
        }
        return csv;
    }

    public static String clobToString(Clob data) throws Exception {
        log.info("Start of clobToString Method######");
        StringBuilder sb = new StringBuilder();
        try {
            Reader reader = data.getCharacterStream();
            BufferedReader br = new BufferedReader(reader);

            String line;
            while (null != (line = br.readLine())) {
                //  System.out.println(line + "::::::line");
                sb.append(line);
                sb.append("\n");
            }

            br.close();
        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        } catch (IOException e) {
            throw new Exception(e.getMessage());
        }
        return sb.toString();
    }

    //        static ChannelSftp setupJsch() throws Exception {
//        ChannelSftp channelSftp = null;
//        Session jschSession = null;
//        Properties props = LoginCreateDataModelHelper.loadProperties();
//
//        JSch jsch = new JSch();
//        jschSession = jsch.getSession(props.getProperty("sftp-client-username"),
//                props.getProperty("sftp-client-host"));
//        jschSession.setPassword(props.getProperty("sftp-client-password"));
//        java.util.Properties config = new java.util.Properties();
//        config.put("StrictHostKeyChecking", "no");
//        jschSession.setConfig(config);
//        jschSession.setTimeout(60000);
//        jschSession.connect();
//        channelSftp = (ChannelSftp) jschSession.openChannel("sftp");
//        channelSftp.connect();
//        //channelSftp.cd(props.getProperty("file-upload-dir"));
//        return channelSftp;
//    }
    public ChannelSftp setupJsch() throws Exception {
        JSch jSch = new JSch();
        Session session = null;
        ChannelSftp channelSftp = null;
        Properties props = LoginCreateDataModelHelper.loadProperties();
        // ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        //Resource[] AllResources = resourcePatternResolver.getResources("classpath*:schema/*.key");
        try {
            //  log.info("before Private Key Added.");
            //  jSch.addIdentity(sftpPrivatePath);

            session = jSch.getSession(sftpClientUsername,
                    sftpClientHost);
            session.setPassword(sftpClientPassword);
            log.info("Password added successfully.");

            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.setTimeout(6000);
            session.connect();
            channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();
            // channelSftp.cd(props.getProperty("file-upload-dir"));
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return channelSftp;
    }

    static void insertIntoTables(Connection con, String metadata, FileTransferResponsePo fileTransferResponsePo, String tableName, TableMetaDataResPo tableMetaDataResPo) throws Exception {
        String executeQuery = null;
        String extrnlTableName = null;
        String masterSql = null;
        String metaData = null;
        String columnNames = null;

        try {
            if (tableMetaDataResPo != null) {
                metaData = tableMetaDataResPo.getMetaData();
                columnNames = tableMetaDataResPo.getColumnNames();
            } else {
                metaData = metadata;
                columnNames = fileTransferResponsePo.getColumnNames();
            }
            DatabaseMetaData dbm = con.getMetaData();
            ResultSet tables = dbm.getTables(null, null, tableName.toUpperCase(), null);
            if (tables.next()) {
                // Table exists
                log.info("MasterTable Already Exists");
                tables.close();
            } else {

                masterSql = "CREATE TABLE " + tableName.toUpperCase() + "  (" + metaData
                        + ")";


                PreparedStatement stmnt = con.prepareStatement(masterSql);
                int masterCount = stmnt.executeUpdate();
                log.info(masterCount + "########count");
                stmnt.close();
            }

            extrnlTableName = "E_" + fileTransferResponsePo.getFileName().replace(".csv", "");
            // External Table Creation
			extrnlTableName=validTableName(extrnlTableName);

            String externalSql = "CREATE TABLE " + extrnlTableName + " (" + metaData + " )"
                    + "ORGANIZATION EXTERNAL\r\n" + "(TYPE ORACLE_LOADER\r\n" + "DEFAULT DIRECTORY G2N_TAB_MAIN\r\n"
                    + "ACCESS PARAMETERS\r\n" + "(\n" +
                    "records delimited by newline\n" +
                    "LOGFILE G2N_TAB_MAIN:'" + extrnlTableName + ".log'\r\n" +
                    "BADFILE G2N_TAB_MAIN:'" + extrnlTableName + ".bad'\r\n" +
                    "skip 1\n" +
                    "fields terminated by ',' optionally enclosed BY '\"' LDRTRIM\n" +
                    "missing field values are null\n" +
                    ")" + "LOCATION ('" + validFileName(fileTransferResponsePo.getFileName()) + "')\r\n" + ") REJECT LIMIT UNLIMITED";
            log.info(externalSql + ":::externalSql");

            PreparedStatement extrnlStmnt = con.prepareStatement(externalSql);
            int extrnlCount = extrnlStmnt.executeUpdate();
            log.info(extrnlCount + "########count");
            extrnlStmnt.close();
            columnNames=validColumnNames(columnNames);
            executeQuery = "insert into " + tableName + " (" + columnNames + "  )  select " +columnNames + " from "
                    + extrnlTableName;
            PreparedStatement insertStatement = con.prepareStatement(executeQuery);
            int count = insertStatement.executeUpdate();
            insertStatement.close();
            log.info(count + ":::::::Insert executeQuery");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        } finally {
            if (extrnlTableName != null) {
                String externalTableDrop = "DROP TABLE " + extrnlTableName;
                PreparedStatement dropStmnt = con.prepareStatement(externalTableDrop);
                int count = dropStmnt.executeUpdate();
                dropStmnt.close();
            }
        }

    }

    public static void insertIntoMappingValues(Connection con, FileTransferResponsePo fileTransferResponsePo, int mapsetId) throws Exception {
        String executeQuery = null;
        String extrnlTableName = null;

        try {
            extrnlTableName = "EXTERNAL_MAPPING_VALUES_" + mapsetId;
            // External Table Creation
            String externalSql = "CREATE TABLE " + extrnlTableName + " (" + fileTransferResponsePo.getMetadata() + " )"
                    + "ORGANIZATION EXTERNAL\r\n" + "(TYPE ORACLE_LOADER\r\n" + "DEFAULT DIRECTORY G2N_TAB_MAIN\r\n"
                    + "ACCESS PARAMETERS\r\n" + "(\r\n" + "records field names all files\r\n"
                    + "fields CSV without embedded record terminators\r\n" + "missing field values are null\r\n"
                    + "REJECT ROWS WITH ALL NULL FIELDS\r\n" + ")\r\n" + "LOCATION ('" + fileTransferResponsePo.getFileName() + "')\r\n" + ")";
            log.info(externalSql + ":::externalSql");
            PreparedStatement extrnlStmnt = con.prepareStatement(externalSql);
            int extrnlCount = extrnlStmnt.executeUpdate();
            log.info(extrnlCount + "########count");
            extrnlStmnt.close();


            // create the statement object
            String deleteQuery = "delete from XXR_MAPPING_VALUES where map_set_id =" + mapsetId;
            log.info(externalSql + ":::externalSql");
            PreparedStatement deleteStmt = con.prepareStatement(deleteQuery);
            int deleteCount = deleteStmt.executeUpdate();
            log.info(deleteCount + "########count");
            deleteStmt.close();

            executeQuery = "insert into XXR_MAPPING_VALUES (Map_line_id,Map_set_id," + fileTransferResponsePo.getColumnNames() + ",LAST_UPDATE_DATE ,"
                    + "Creation_date )  select xxr_map_line_id_s.nextval ," + mapsetId + "," + fileTransferResponsePo.getColumnNames() + ",sysdate," + "sysdate from "
                    + extrnlTableName;
            log.info(executeQuery);
            PreparedStatement insertStatement = con.prepareStatement(executeQuery);
            int count = insertStatement.executeUpdate();
            insertStatement.close();
            log.info(count + ":::::::Insert executeQuery");

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        } finally {
            if (extrnlTableName != null) {
                String externalTableDrop = "DROP TABLE " + extrnlTableName;
                PreparedStatement dropStmnt = con.prepareStatement(externalTableDrop);
                int count = dropStmnt.executeUpdate();
                dropStmnt.close();
            }
        }
    }

    public static FileTransferResponsePo metaDataFormation(FileInputStream fileInputStream, String extractionFlag, int mapSetId) throws Exception {
        String[] headers = null;
        BufferedReader br = null;
        StringBuffer sbs = null;
        StringBuffer mapBuffer = null;
        CSVReader reader = null;
        FileTransferResponsePo fileTransferResponsePo = new FileTransferResponsePo();
        try {
            log.info("metaDataFormation method");
            br = new BufferedReader(new InputStreamReader(fileInputStream));
            reader = new CSVReader(br);
            // if the first line is the header
            headers = reader.readNext();

            sbs = new StringBuffer();
            mapBuffer = new StringBuffer();

            int j = 0;
            for (String header : headers) {
                sbs.append(header);
                if (mapSetId != 0) {
                    mapBuffer.append(header + " varchar2(240)");
                }
                if (j != headers.length - 1) {
                    sbs.append(",");
                    if (mapSetId != 0) {
                        mapBuffer.append(",");
                    }
                }
                j++;
            }
            fileTransferResponsePo.setColumnNames(sbs.toString());
            fileTransferResponsePo.setMetadata(mapBuffer.toString());
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        } finally {
            if (br != null) {
                br.close();
            }
            if (reader != null) {
                reader.close();
            }
        }
        return fileTransferResponsePo;
    }


    public static JSONObject bipSoapCall(String requestId,
                                         CatalogServiceStub catalogStub, String loginResponseToken, String soapUrl, String dynamicSqlQuery) throws Exception {
        String createReportResponse = "";
        String dataModelUrl = null;
        JSONObject xmlJSONObj = null;
        try {
            dataModelUrl = LoginCreateDataModelHelper.createDataModel(catalogStub, requestId, loginResponseToken,
                    dynamicSqlQuery);
            log.info("dataModelUrl:::::" + dataModelUrl);

            // Service call for creating report
            createReportResponse = ReportHelper.createReportService(soapUrl, loginResponseToken, requestId,
                    dataModelUrl);
            log.info("createReportResponse:::::::::::" + createReportResponse);

            // service call to RunReport
            DataHandler dt = ReportHelper.runReport(soapUrl, loginResponseToken, createReportResponse);

            String xmlOutput = IOUtils.toString(dt.getDataSource().getInputStream(), "UTF-8");
            /*
             * String xmlOutput = new BufferedReader( new
             * InputStreamReader(dt.getDataSource().getInputStream())).lines()
             * .collect(Collectors.);
             */
            log.debug("xmlOutput:::::" + xmlOutput);

            int PRETTY_PRINT_INDENT_FACTOR = 4;
            boolean deleteDataModelResp = LoginCreateDataModelHelper.deleteObject(catalogStub, dataModelUrl,
                    loginResponseToken);
            log.info("deletedDataModel Response::::" + deleteDataModelResp);

            // Service call for deleting Report
            boolean deleteReportResp = LoginCreateDataModelHelper.deleteObject(catalogStub, createReportResponse,
                    loginResponseToken);
            log.info("deletedReport Response::::" + deleteReportResp);

            // xml to JsonObject
            xmlJSONObj = XML.toJSONObject(xmlOutput);

        } catch (Exception e) {
            if (dataModelUrl != null && dataModelUrl != "") {
                log.info("dataModelUrl:::::::" + dataModelUrl + "loginResponseToken:::::::::::" + loginResponseToken);
                // Service call for deleting Data Model
                boolean deleteDataModelResp = LoginCreateDataModelHelper.deleteObject(catalogStub, dataModelUrl,
                        loginResponseToken);
                log.info("deletedDataModel Response::::" + deleteDataModelResp);
            }
            if (createReportResponse != null && createReportResponse != "") {
                // Service call for deleting Report
                boolean deleteReportResp = LoginCreateDataModelHelper.deleteObject(catalogStub, createReportResponse,
                        loginResponseToken);
                log.info("deletedReport Response::::" + deleteReportResp);
            }
            e.printStackTrace();
        }
        return xmlJSONObj;
    }
}
