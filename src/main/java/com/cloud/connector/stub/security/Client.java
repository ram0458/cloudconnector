package com.cloud.connector.stub.security;

import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.cloud.connector.model.CloudDataProcess;
import com.cloud.connector.model.CrCloudStatusInformation;
import com.cloud.connector.stub.catalog.CatalogServiceStub;
import com.cloud.connector.util.ClientConnection;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Client {
    @Autowired
    Adhoc adhoc;

    @Value("${security-service-url}")
    private String securityServiceUrl;

    @Value("${catalog-service-url}")
    private String catalogServiceUrl;

    @Value("${report-service-url}")
    private String reportServiceUrl;

    @Value("${absolute-path}")
    private String absolutePath;

    @Async
    public void processRequests(JdbcTemplate jdbcTemplate, CloudDataProcess cldDataProcessPo, CrCloudStatusInformation crCloudStatusInformation) throws Exception {
        Connection clientCon = null;
        Connection con = null;
        ResultSet resultSet = null;
        String loginResponseToken = "";
        String extractionFlag = null;
        String metaData = null;
        String tableName = null;

        CatalogServiceStub catalogStub = null;
        int totalCount = 0;
        Properties props = LoginCreateDataModelHelper.loadProperties();
        UUID uuid = UUID.randomUUID();
        Integer requestId = cldDataProcessPo.getId();
        String newRequestId = uuid.toString();
        String lookupFlag = cldDataProcessPo.getLookupFlag();
        String dynamicSqlQuery = cldDataProcessPo.getSqlQuery();

        //getting connection from Jdbc Template
        con = Objects.requireNonNull(jdbcTemplate.getDataSource()).getConnection();
        PreparedStatement cloudDataConfigStmnt = con
				.prepareStatement("select * from cr_cloud_login_details where pod_id=?");
		cloudDataConfigStmnt.setLong(1, cldDataProcessPo.getPodId());
        resultSet = cloudDataConfigStmnt.executeQuery();
        String userName = "";
        String password = "";
        String soapUrl = "";
        if (resultSet.next()) {
            log.info("Inside if ###");
            userName = resultSet.getString("username");
            password = resultSet.getString("password");
            soapUrl = resultSet.getString("url");
            log.info("soapUrl#####" + soapUrl);
        }
        resultSet.close();

        if (cldDataProcessPo.getTableName() != null && !cldDataProcessPo.getTableName().isEmpty()) {
            extractionFlag = "Y";
            tableName = cldDataProcessPo.getTableName();

        } else
            extractionFlag = "N";

        // int mapSetId = cldDataProcessPo.getMapSetId();

        String scheduledJobCall = "";
        if (cldDataProcessPo.getScheduledJobCall() == null)
            scheduledJobCall = "N";
        else
            scheduledJobCall = cldDataProcessPo.getScheduledJobCall();
        try {
        	PreparedStatement updateStatus = con.prepareStatement(
					"update cr_cloud_status_information set status='processing',request_id =? where status_id=?");
			updateStatus.setString(1, newRequestId);
			updateStatus.setObject(2, crCloudStatusInformation.getStatusId());
            int count = updateStatus.executeUpdate();
            log.info("No of rows updated :::::" + count);
            updateStatus.close();

            // Authentication service
            loginResponseToken = LoginCreateDataModelHelper.loginService(soapUrl, userName, password);
            log.info("authenticationToken:::::" + loginResponseToken);

            catalogStub = new CatalogServiceStub(soapUrl + catalogServiceUrl);
            PreparedStatement stmt = con.prepareStatement("select * from cr_pod_information where pod_id =?");
			stmt.setLong(1, cldDataProcessPo.getPodId());
            // execute query
            ResultSet rset = stmt.executeQuery();

            String clientConnection = null;
            String clientPassword = null;
            String clientUser = null;
            while (rset.next()) {
                clientConnection = rset.getString("pod_target_url");
                clientUser = rset.getString("pod_db_user");
                clientPassword = rset.getString("pod_db_password");
            }
            rset.close();
            stmt.close();
            // Establishing client connection
            clientCon = ClientConnection.getConnection(clientConnection, clientUser, clientPassword);

            // Service call for creating DataModel in Cloud
            if (!extractionFlag.equalsIgnoreCase("Y")) {
                JSONObject xmlJSONObj = Utils.bipSoapCall(newRequestId, catalogStub, loginResponseToken, soapUrl, dynamicSqlQuery);

                int PRETTY_PRINT_INDENT_FACTOR = 4;
                log.debug("xmlJSONObj:::::::" + xmlJSONObj);
                if (lookupFlag != null && lookupFlag != "") {
                    if (lookupFlag.equalsIgnoreCase("Y"))
                        insertIntoLookup(xmlJSONObj, clientCon, scheduledJobCall);
                }

                String jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
                log.debug("jsonPrettyPrintString:::::::" + jsonPrettyPrintString);

                // converting Json String to csv string format
                String csv = Utils.convertingJsontoCsv(jsonPrettyPrintString, newRequestId, dynamicSqlQuery);
                // saving csv string as lob
                csv = csv.replaceAll("\\'", "''");

                // String columnNames = Utils.modifyQuery(dynamicSqlQuery);

            	PreparedStatement saveLob = clientCon.prepareStatement(
						"insert into  cr_file_details(FILE_TYPE,FILE_NAME,FILE_CONTENT,CREATION_DATE,CREATED_BY,LAST_UPDATE_DATE,LAST_UPDATED_BY,CLD_FILE_ID) values('csv',?,?,sysdate,'Cloud-Connector',sysdate,'Cloud-Connector',?)");

				Clob myClob = clientCon.createClob();
				myClob.setString(1, csv);
				saveLob.setString(1, newRequestId + ".csv");
				saveLob.setClob(2, myClob);
				saveLob.setObject(3, crCloudStatusInformation.getStatusId());

                int rowsInserted = saveLob.executeUpdate();
                saveLob.close();
                log.info("No of rows inserted :::::" + rowsInserted);
            } else {

                DatabaseMetaData dbm = clientCon.getMetaData();
                ResultSet tables = dbm.getTables(null, null, tableName.toUpperCase(), null);
                if (tables.next()) {
                    // Table exists
                    log.info("MasterTable Already Exists");
                    PreparedStatement trunStmt = clientCon.prepareStatement("truncate table " +validTableName(tableName));
                    int countplus = trunStmt.executeUpdate();
                    tables.close();
                    trunStmt.close();

                }
                adhoc.adhocMethod(cldDataProcessPo, newRequestId, catalogStub, loginResponseToken, soapUrl, clientCon, extractionFlag);

            }
            // create the statement object
            PreparedStatement updateStmnt = con
					.prepareStatement("update cr_cloud_status_information set status='completed' where status_id= ?");
			updateStmnt.setObject(1, crCloudStatusInformation.getStatusId());
            int rowsUpdated = updateStmnt.executeUpdate();
            updateStmnt.close();
            log.info("No of rows updated :::::" + rowsUpdated);
        } catch (Exception e) {
        	PreparedStatement updateStmnt = con
					.prepareStatement("update cr_cloud_status_information set status='error'  where status_id= ?");
			updateStmnt.setObject(1, crCloudStatusInformation.getStatusId());
            int rowsUpdated = updateStmnt.executeUpdate();
            log.info("No of rows updated :::::" + rowsUpdated);
            updateStmnt.close();
            e.printStackTrace();
        } finally {
            con.close();
            if (clientCon != null) {
                clientCon.close();
            }
        }
    }
    private static String validTableName(String tableName) {
        // Perform validation logic here (e.g., check against existing tables)
	 return tableName.replaceAll("[^a-zA-Z0-9_$]", "");
    }

    private static void insertIntoLookup(JSONObject xmlJSONObj, Connection con, String scheduledJobCall) throws SQLException {
        //  System.out.println(xmlJSONObj.toString());

        JSONObject dataDsObj = xmlJSONObj.optJSONObject("ROWSET");
        JSONArray arr = null;
        JSONObject g1Obj = null;
        if (dataDsObj != null)
            arr = dataDsObj.optJSONArray("ROW");

        // JSONArray arr = xmlJSONObj.getJSONObject("DATA_DS").getJSONArray("G_1");

        String lookupType = "";
        String lookupCode = "";
        // ResultSet set = null;
        ResultSet rs = null;
        ResultSet resltSet = null;
        // ResultSet result = null;
        ResultSet lookupCodeSet = null;
        PreparedStatement lookupsetSelect = null;
        // PreparedStatement lookupset = null;
        // PreparedStatement lookupValueSelect = null;
        PreparedStatement lookupCodeSelect = null;
        if (dataDsObj != null)
            g1Obj = dataDsObj.optJSONObject("ROW");
        if (g1Obj != null) {
            arr = new JSONArray();
            arr.put(g1Obj);
        }
        if (arr != null) {
            for (int i = 0; i < arr.length(); i++) {

                try {

                    lookupType = arr.getJSONObject(i).getString("LOOKUP_TYPE");
                    log.info("lookupType::::::::::" + lookupType);

                    Object lookupCodeObj = arr.getJSONObject(i).get("LOOKUP_CODE");
                    log.info("lookupCodeObj::::::::::" + String.valueOf(lookupCodeObj));

                    lookupCode = String.valueOf(lookupCodeObj);

                    /*
                     * if (lookupCodeObj instanceof Integer) lookupCode = Integer.toString(((Number)
                     * (lookupCodeObj)).intValue()); if (lookupCodeObj instanceof String) lookupCode
                     * = lookupCodeObj.toString();
                     */

                    lookupsetSelect = con.prepareStatement("Select * from cr_lookup_sets where lookup_set_name=?");
					lookupsetSelect.setString(1, lookupType);
                    rs = lookupsetSelect.executeQuery();
                    Long lookupSetId = null;
                    if (rs.next()) {
                        lookupSetId = new BigDecimal(rs.getString("lookup_set_id")).longValue();
                    } else {

                    	PreparedStatement lookupsetInsert = con.prepareStatement(
								"Insert into cr_lookup_sets (lookup_set_name,last_updated_by,last_update_date,lookup_flag) values (?,'ConvertRite',sysdate,'Y')");
						lookupsetInsert.setString(1, lookupType);
                        lookupsetInsert.executeUpdate();
                        lookupsetInsert.close();
                        resltSet = lookupsetSelect.executeQuery();
                        if (resltSet.next()) {
                            lookupSetId = new BigDecimal(resltSet.getString("lookup_set_id")).longValue();
                        }
                    }

                    if (lookupSetId != null) {
                    	lookupCodeSelect = con.prepareStatement(
								"select * from  cr_lookup_values where lookup_set_id=? and  lookup_value=? order by lookup_value_id desc");
						lookupCodeSelect.setLong(1, lookupSetId);
						lookupCodeSelect.setString(2,lookupCode);

                        lookupCodeSet = lookupCodeSelect.executeQuery();

                        Long lookUpId = null;
                        if (lookupCodeSet.next()) {
                            lookUpId = new BigDecimal(lookupCodeSet.getString("LOOKUP_VALUE_ID")).longValue();

                        }
                        log.info("lookUpId:::::::::" + lookUpId);
                        if (lookUpId == null) {
                        	PreparedStatement lookupValueInsert = con.prepareStatement(
									"Insert into cr_lookup_values(lookup_set_id,lookup_value,last_updated_by,last_update_date) values(?, ?,'ConvertRite',sysdate)");
							lookupValueInsert.setLong(1, lookupSetId);
							lookupValueInsert.setString(2, lookupCode);
                            lookupValueInsert.executeUpdate();
                            lookupValueInsert.close();
                        }

                    }

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    // throw new Exception();

                } finally {
                    try {

                        if (rs != null)
                            rs.close();
                        if (resltSet != null)
                            resltSet.close();
                        /*
                         * if (set != null) set.close(); if (result != null) result.close();
                         */
                        if (lookupCodeSet != null)
                            lookupCodeSet.close();
                    } catch (SQLException ex) {
                        ex.getMessage();
                    }
                    try {
                        if (lookupsetSelect != null)
                            lookupsetSelect.close();
                        /*
                         * if (lookupset != null) lookupset.close(); if (lookupValueSelect != null)
                         * lookupValueSelect.close();
                         */
                        if (lookupCodeSelect != null)
                            lookupCodeSelect.close();
                    } catch (SQLException ex) {
                        ex.getMessage();
                    }

                }
            }
        }
    }


}


