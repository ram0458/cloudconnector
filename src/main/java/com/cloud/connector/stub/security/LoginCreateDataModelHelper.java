package com.cloud.connector.stub.security;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.util.ByteArrayDataSource;

import org.apache.log4j.Logger;

import com.cloud.connector.stub.catalog.CatalogServiceStub;
import com.cloud.connector.stub.catalog.CatalogServiceStub.CreateObjectInSession;
import com.cloud.connector.stub.catalog.CatalogServiceStub.CreateObjectInSessionResponse;
import com.cloud.connector.stub.catalog.CatalogServiceStub.DeleteObjectInSession;
import com.cloud.connector.stub.catalog.CatalogServiceStub.DeleteObjectInSessionResponse;
import com.cloud.connector.stub.security.SecurityServiceStub.Login;
import com.cloud.connector.stub.security.SecurityServiceStub.LoginResponse;

public class LoginCreateDataModelHelper {

	static final Logger log = Logger.getLogger(LoginCreateDataModelHelper.class);

	// loading propertyfile
	public static Properties loadProperties() throws Exception {
		// log.info("Start Of loadProperties#######");

		Properties props = new Properties();
		InputStream inputStream = LoginCreateDataModelHelper.class.getResourceAsStream("/application.properties");
//		String path = "./config/application.properties";
//		FileInputStream inputStream = new FileInputStream(path);
		try {
			props.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception();
		}
		return props;
	}

	// Authentication service
	public static String loginService(String soapUrl, String userName, String password) throws Exception {
		log.info("Start Of loginService#######");

		LoginResponse response = null;
		String loginResponseToken = "";
		try {
			SecurityServiceStub stub = new SecurityServiceStub(
					soapUrl + loadProperties().getProperty("security-service-url"));
			Login login = new Login();
			login.setUserID(userName);
			login.setPassword(password);
			response = stub.login(login);
			loginResponseToken = response.getLoginReturn();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
		return loginResponseToken;
	}

	public static boolean deleteObject(CatalogServiceStub catalogStub, String objectAbsolutePath,
									   String loginResponseToken) throws Exception {
		log.info("Start Of deleteObject#######");
		boolean deleteObjectResp = false;
		try {
			DeleteObjectInSession deleteObjectInSession = new DeleteObjectInSession();
			deleteObjectInSession.setObjectAbsolutePath(objectAbsolutePath);
			deleteObjectInSession.setBipSessionToken(loginResponseToken);
			DeleteObjectInSessionResponse deleteObjectInSessionResponse = catalogStub
					.deleteObjectInSession(deleteObjectInSession);
			deleteObjectResp = deleteObjectInSessionResponse.getDeleteObjectInSessionReturn();
		} catch (Exception e) {
			throw new Exception();
		}
		return deleteObjectResp;
	}

	// Service call for creating DataModel in Cloud
	public static String createDataModel(CatalogServiceStub catalogStub, String requestId, String loginResponseToken,
										 String dynamicSqlQuery) throws Exception {
		log.info("Start Of createDataModel#######");

		// String requestId = "";
		CreateObjectInSessionResponse createObjectResponse = null;
		String dataModelUrl = "";
		try {
			// requestId = rs.getString("REQUEST_ID");
			log.info("requestId##### " + requestId);
			/*
			 * String sqlQuery = dynamicSqlQuery.toUpperCase();
			 * log.info("sqlQuery##########" + sqlQuery);
			 * log.info(sqlQuery.lastIndexOf("SELECT")); String subString =
			 * sqlQuery.substring(sqlQuery.lastIndexOf("SELECT") + 6,
			 * sqlQuery.indexOf("FROM")).trim(); log.info(subString); String[] columnNames =
			 * subString.split(",");
			 */

			/*
			 * String subQuery = Utils.modifyQuery(dynamicSqlQuery); String
			 * columnNames[]=subQuery.split(",");
			 */
			StringBuffer xml = new StringBuffer();
			int i = 1;
			xml.append("<?xml version = '1.0' encoding = 'utf-8'?>\r\n"
					+ "<dataModel xmlns=\"http://xmlns.oracle.com/oxp/xmlp\" version=\"2.0\" xmlns:xdm=\"http://xmlns.oracle.com/oxp/xmlp\" xmlns:xsd=\"http://wwww.w3.org/2001/XMLSchema\" defaultDataSourceRef=\"demo\">\r\n"
					+ "   <description>\r\n"
					+ "      <![CDATA[undefined]]>\r\n"
					+ "   </description>\r\n"
					+ "   <dataProperties>\r\n"
					+ "      <property name=\"include_parameters\" value=\"false\"/>\r\n"
					+ "      <property name=\"include_null_Element\" value=\"false\"/>\r\n"
					+ "      <property name=\"include_rowsettag\" value=\"false\"/>\r\n"
					+ "      <property name=\"xml_tag_case\" value=\"upper\"/>\r\n"
					+ "      <property name=\"generate_output_format\" value=\"csv\"/>\r\n"
					+ "      <property name=\"optimize_query_executions\" value=\"false\"/>\r\n"
					+ "      <property name=\"sql_monitor_report_generated\" value=\"undefined\"/>\r\n"
					+ "   </dataProperties>\r\n"
					+ "   <dataSets>\r\n"
					+ "      <dataSet name=\"RESULT_DATA\" type=\"simple\">\r\n"
					+ "         <sql dataSourceRef=\"ApplicationDB_HCM\" nsQuery=\"true\" sp=\"true\" xmlRowTagName=\"\" bindMultiValueAsCommaSepStr=\"false\">\r\n"
					+ "            <![CDATA[DECLARE\r\n"
					+ "       type refcursor is REF CURSOR;\r\n"
					+ "       xdo_cursor  refcursor;\r\n"
					+ "	   var clob;\r\n"
					+ "    BEGIN\r\n"
					+ "      var := :qryStmt;\r\n"
					+ "	  OPEN :xdo_cursor FOR SELECT * FROM \r\n"
					+ " ( \r\n"
					+ dynamicSqlQuery+"\r\n"
					+ " ) ;\r\n"
					+ "    END;]]>\r\n"
					+ "         </sql>\r\n"
					+ "      </dataSet>\r\n"
					+ "   </dataSets>\r\n"
					+ "   <output rootName=\"DATA_DS\" uniqueRowName=\"false\">\r\n"
					+ "      <nodeList name=\"RESULT_DATA\"/>\r\n"
					+ "   </output>\r\n"
					+ "   <eventTriggers/>\r\n"
					+ "   <lexicals/>\r\n"
					+ "   <valueSets/>\r\n"
					+ "   <parameters>\r\n"
					+ "      <parameter name=\"qryStmt\" defaultValue=\"1\" dataType=\"xsd:string\" rowPlacement=\"1\">\r\n"
					+ "         <input label=\"Query1\"/>\r\n"
					+ "      </parameter>\r\n"
					+ "      <parameter name=\"xdo_cursor\" dataType=\"xsd:string\" rowPlacement=\"1\">\r\n"
					+ "         <input label=\"xdo_cursor\"/>\r\n"
					+ "      </parameter>\r\n"
					+ "   </parameters>\r\n"
					+ "   <bursting/>\r\n"
					+ "   <display>\r\n"
					+ "      <layouts>\r\n"
					+ "         <layout name=\"RESULT_DATA\" left=\"284px\" top=\"407px\"/>\r\n"
					+ "         <layout name=\"DATA_DS\" left=\"6px\" top=\"351px\"/>\r\n"
					+ "      </layouts>\r\n"
					+ "      <groupLinks/>\r\n"
					+ "   </display>\r\n"
					+ "</dataModel>");

			// String encode =
			// "PD94bWwgdmVyc2lvbiA9ICcxLjAnIGVuY29kaW5nID0gJ3V0Zi04Jz8+CjxkYXRhTW9kZWwgeG1sbnM9Imh0dHA6Ly94bWxucy5vcmFjbGUuY29tL294cC94bWxwIiB2ZXJzaW9uPSIyLjAiIHhtbG5zOnhkbT0iaHR0cDovL3htbG5zLm9yYWNsZS5jb20vb3hwL3htbHAiIHhtbG5zOnhzZD0iaHR0cDovL3d3d3cudzMub3JnLzIwMDEvWE1MU2NoZW1hIiBkZWZhdWx0RGF0YVNvdXJjZVJlZj0iZGVtbyI+CiAgIDxkZXNjcmlwdGlvbj4KICAgICAgPCFbQ0RBVEFbTG9va3VwVmFsdWVzXV0+CiAgIDwvZGVzY3JpcHRpb24+CiAgIDxkYXRhUHJvcGVydGllcz4KICAgICAgPHByb3BlcnR5IG5hbWU9ImluY2x1ZGVfcGFyYW1ldGVycyIgdmFsdWU9InRydWUiLz4KICAgICAgPHByb3BlcnR5IG5hbWU9ImluY2x1ZGVfbnVsbF9FbGVtZW50IiB2YWx1ZT0iZmFsc2UiLz4KICAgICAgPHByb3BlcnR5IG5hbWU9ImluY2x1ZGVfcm93c2V0dGFnIiB2YWx1ZT0iZmFsc2UiLz4KICAgICAgPHByb3BlcnR5IG5hbWU9ImV4Y2x1ZGVfdGFnc19mb3JfbG9iIiB2YWx1ZT0iZmFsc2UiLz4KICAgICAgPHByb3BlcnR5IG5hbWU9InhtbF90YWdfY2FzZSIgdmFsdWU9InVwcGVyIi8+CiAgICAgIDxwcm9wZXJ0eSBuYW1lPSJzcWxfbW9uaXRvcl9yZXBvcnRfZ2VuZXJhdGVkIiB2YWx1ZT0iZmFsc2UiLz4KICAgICAgPHByb3BlcnR5IG5hbWU9Im9wdGltaXplX3F1ZXJ5X2V4ZWN1dGlvbnMiIHZhbHVlPSJmYWxzZSIvPgogICA8L2RhdGFQcm9wZXJ0aWVzPgogICA8ZGF0YVNldHM+CiAgICAgIDxkYXRhU2V0IG5hbWU9Ikxvb2t1cFZhbHVlcyIgdHlwZT0iY29tcGxleCI+CiAgICAgICAgIDxzcWwgZGF0YVNvdXJjZVJlZj0iQXBwbGljYXRpb25EQl9GU0NNIj4KICAgICAgICAgICAgPCFbQ0RBVEFbc2VsZWN0ICogZnJvbSBmbmRfbG9va3VwX3ZhbHVlcyB3aGVyZSBsb29rdXBfdHlwZSA9ICdQT1pfVkVORE9SX1RZUEUnIGFuZCBsYW5ndWFnZT0nVVMnXV0+CiAgICAgICAgIDwvc3FsPgogICAgICA8L2RhdGFTZXQ+CiAgIDwvZGF0YVNldHM+CiAgIDxvdXRwdXQgcm9vdE5hbWU9IkRBVEFfRFMiIHVuaXF1ZVJvd05hbWU9ImZhbHNlIj4KICAgICAgPG5vZGVMaXN0IG5hbWU9ImRhdGEtc3RydWN0dXJlIj4KICAgICAgICAgPGRhdGFTdHJ1Y3R1cmUgdGFnTmFtZT0iREFUQV9EUyI+CiAgICAgICAgICAgIDxncm91cCBuYW1lPSJHXzEiIGxhYmVsPSJHXzEiIHNvdXJjZT0iTG9va3VwVmFsdWVzIj4KICAgICAgICAgICAgICAgPGVsZW1lbnQgbmFtZT0iTE9PS1VQX1RZUEUiIHZhbHVlPSJMT09LVVBfVFlQRSIgbGFiZWw9IkxPT0tVUF9UWVBFIiBkYXRhVHlwZT0ieHNkOnN0cmluZyIgYnJlYWtPcmRlcj0iIiBmaWVsZE9yZGVyPSIyIi8+CiAgICAgICAgICAgICAgIDxlbGVtZW50IG5hbWU9IkNSRUFURURfQlkiIHZhbHVlPSJDUkVBVEVEX0JZIiBsYWJlbD0iQ1JFQVRFRF9CWSIgZGF0YVR5cGU9InhzZDpzdHJpbmciIGJyZWFrT3JkZXI9IiIgZmllbGRPcmRlcj0iMTAiLz4KICAgICAgICAgICAgICAgPGVsZW1lbnQgbmFtZT0iQ1JFQVRJT05fREFURSIgdmFsdWU9IkNSRUFUSU9OX0RBVEUiIGxhYmVsPSJDUkVBVElPTl9EQVRFIiBkYXRhVHlwZT0ieHNkOmRhdGUiIGJyZWFrT3JkZXI9IiIgZmllbGRPcmRlcj0iMTEiIGZvcm1hdE1hc2s9IiIvPgogICAgICAgICAgICAgICA8ZWxlbWVudCBuYW1lPSJMQVNUX1VQREFURURfQlkiIHZhbHVlPSJMQVNUX1VQREFURURfQlkiIGxhYmVsPSJMQVNUX1VQREFURURfQlkiIGRhdGFUeXBlPSJ4c2Q6c3RyaW5nIiBicmVha09yZGVyPSIiIGZpZWxkT3JkZXI9IjEyIi8+CiAgICAgICAgICAgICAgIDxlbGVtZW50IG5hbWU9IkxBU1RfVVBEQVRFX0xPR0lOIiB2YWx1ZT0iTEFTVF9VUERBVEVfTE9HSU4iIGxhYmVsPSJMQVNUX1VQREFURV9MT0dJTiIgZGF0YVR5cGU9InhzZDpzdHJpbmciIGJyZWFrT3JkZXI9IiIgZmllbGRPcmRlcj0iMTMiLz4KICAgICAgICAgICAgICAgPGVsZW1lbnQgbmFtZT0iTEFTVF9VUERBVEVfREFURSIgdmFsdWU9IkxBU1RfVVBEQVRFX0RBVEUiIGxhYmVsPSJMQVNUX1VQREFURV9EQVRFIiBkYXRhVHlwZT0ieHNkOmRhdGUiIGJyZWFrT3JkZXI9IiIgZmllbGRPcmRlcj0iMTQiIGZvcm1hdE1hc2s9IiIvPgogICAgICAgICAgICAgICA8ZWxlbWVudCBuYW1lPSJWSUVXX0FQUExJQ0FUSU9OX0lEIiB2YWx1ZT0iVklFV19BUFBMSUNBVElPTl9JRCIgbGFiZWw9IlZJRVdfQVBQTElDQVRJT05fSUQiIGRhdGFUeXBlPSJ4c2Q6ZG91YmxlIiBicmVha09yZGVyPSIiIGZpZWxkT3JkZXI9IjE2Ii8+CiAgICAgICAgICAgICAgIDxlbGVtZW50IG5hbWU9IlJPV19JRCIgdmFsdWU9IlJPV19JRCIgbGFiZWw9IlJPV19JRCIgZGF0YVR5cGU9InhzZDpzdHJpbmciIGJyZWFrT3JkZXI9IiIgZmllbGRPcmRlcj0iMSIvPgogICAgICAgICAgICAgICA8ZWxlbWVudCBuYW1lPSJDSEFOR0VfU0lOQ0VfTEFTVF9SRUZSRVNIIiB2YWx1ZT0iQ0hBTkdFX1NJTkNFX0xBU1RfUkVGUkVTSCIgbGFiZWw9IkNIQU5HRV9TSU5DRV9MQVNUX1JFRlJFU0giIGRhdGFUeXBlPSJ4c2Q6c3RyaW5nIiBicmVha09yZGVyPSIiIGZpZWxkT3JkZXI9IjE4Ii8+CiAgICAgICAgICAgICAgIDxlbGVtZW50IG5hbWU9IkxBTkdVQUdFIiB2YWx1ZT0iTEFOR1VBR0UiIGxhYmVsPSJMQU5HVUFHRSIgZGF0YVR5cGU9InhzZDpzdHJpbmciIGJyZWFrT3JkZXI9IiIgZmllbGRPcmRlcj0iMyIvPgogICAgICAgICAgICAgICA8ZWxlbWVudCBuYW1lPSJMT09LVVBfQ09ERSIgdmFsdWU9IkxPT0tVUF9DT0RFIiBsYWJlbD0iTE9PS1VQX0NPREUiIGRhdGFUeXBlPSJ4c2Q6c3RyaW5nIiBicmVha09yZGVyPSIiIGZpZWxkT3JkZXI9IjQiLz4KICAgICAgICAgICAgICAgPGVsZW1lbnQgbmFtZT0iTUVBTklORyIgdmFsdWU9Ik1FQU5JTkciIGxhYmVsPSJNRUFOSU5HIiBkYXRhVHlwZT0ieHNkOnN0cmluZyIgYnJlYWtPcmRlcj0iIiBmaWVsZE9yZGVyPSI1Ii8+CiAgICAgICAgICAgICAgIDxlbGVtZW50IG5hbWU9IkRFU0NSSVBUSU9OIiB2YWx1ZT0iREVTQ1JJUFRJT04iIGxhYmVsPSJERVNDUklQVElPTiIgZGF0YVR5cGU9InhzZDpzdHJpbmciIGJyZWFrT3JkZXI9IiIgZmllbGRPcmRlcj0iNiIvPgogICAgICAgICAgICAgICA8ZWxlbWVudCBuYW1lPSJFTkFCTEVEX0ZMQUciIHZhbHVlPSJFTkFCTEVEX0ZMQUciIGxhYmVsPSJFTkFCTEVEX0ZMQUciIGRhdGFUeXBlPSJ4c2Q6c3RyaW5nIiBicmVha09yZGVyPSIiIGZpZWxkT3JkZXI9IjciLz4KICAgICAgICAgICAgICAgPGVsZW1lbnQgbmFtZT0iU1RBUlRfREFURV9BQ1RJVkUiIHZhbHVlPSJTVEFSVF9EQVRFX0FDVElWRSIgbGFiZWw9IlNUQVJUX0RBVEVfQUNUSVZFIiBkYXRhVHlwZT0ieHNkOmRhdGUiIGJyZWFrT3JkZXI9IiIgZmllbGRPcmRlcj0iOCIgZm9ybWF0TWFzaz0iIi8+CiAgICAgICAgICAgICAgIDxlbGVtZW50IG5hbWU9IkVORF9EQVRFX0FDVElWRSIgdmFsdWU9IkVORF9EQVRFX0FDVElWRSIgbGFiZWw9IkVORF9EQVRFX0FDVElWRSIgZGF0YVR5cGU9InhzZDpkYXRlIiBicmVha09yZGVyPSIiIGZpZWxkT3JkZXI9IjkiIGZvcm1hdE1hc2s9IiIvPgogICAgICAgICAgICAgICA8ZWxlbWVudCBuYW1lPSJTT1VSQ0VfTEFORyIgdmFsdWU9IlNPVVJDRV9MQU5HIiBsYWJlbD0iU09VUkNFX0xBTkciIGRhdGFUeXBlPSJ4c2Q6c3RyaW5nIiBicmVha09yZGVyPSIiIGZpZWxkT3JkZXI9IjE1Ii8+CiAgICAgICAgICAgICAgIDxlbGVtZW50IG5hbWU9IlRFUlJJVE9SWV9DT0RFIiB2YWx1ZT0iVEVSUklUT1JZX0NPREUiIGxhYmVsPSJURVJSSVRPUllfQ09ERSIgZGF0YVR5cGU9InhzZDpzdHJpbmciIGJyZWFrT3JkZXI9IiIgZmllbGRPcmRlcj0iMTciLz4KICAgICAgICAgICAgICAgPGVsZW1lbnQgbmFtZT0iQVRUUklCVVRFX0NBVEVHT1JZIiB2YWx1ZT0iQVRUUklCVVRFX0NBVEVHT1JZIiBsYWJlbD0iQVRUUklCVVRFX0NBVEVHT1JZIiBkYXRhVHlwZT0ieHNkOnN0cmluZyIgYnJlYWtPcmRlcj0iIiBmaWVsZE9yZGVyPSIxOSIvPgogICAgICAgICAgICAgICA8ZWxlbWVudCBuYW1lPSJBVFRSSUJVVEUxIiB2YWx1ZT0iQVRUUklCVVRFMSIgbGFiZWw9IkFUVFJJQlVURTEiIGRhdGFUeXBlPSJ4c2Q6c3RyaW5nIiBicmVha09yZGVyPSIiIGZpZWxkT3JkZXI9IjIwIi8+CiAgICAgICAgICAgICAgIDxlbGVtZW50IG5hbWU9IkFUVFJJQlVURTIiIHZhbHVlPSJBVFRSSUJVVEUyIiBsYWJlbD0iQVRUUklCVVRFMiIgZGF0YVR5cGU9InhzZDpzdHJpbmciIGJyZWFrT3JkZXI9IiIgZmllbGRPcmRlcj0iMjEiLz4KICAgICAgICAgICAgICAgPGVsZW1lbnQgbmFtZT0iQVRUUklCVVRFMyIgdmFsdWU9IkFUVFJJQlVURTMiIGxhYmVsPSJBVFRSSUJVVEUzIiBkYXRhVHlwZT0ieHNkOnN0cmluZyIgYnJlYWtPcmRlcj0iIiBmaWVsZE9yZGVyPSIyMiIvPgogICAgICAgICAgICAgICA8ZWxlbWVudCBuYW1lPSJBVFRSSUJVVEU0IiB2YWx1ZT0iQVRUUklCVVRFNCIgbGFiZWw9IkFUVFJJQlVURTQiIGRhdGFUeXBlPSJ4c2Q6c3RyaW5nIiBicmVha09yZGVyPSIiIGZpZWxkT3JkZXI9IjIzIi8+CiAgICAgICAgICAgICAgIDxlbGVtZW50IG5hbWU9IkFUVFJJQlVURTUiIHZhbHVlPSJBVFRSSUJVVEU1IiBsYWJlbD0iQVRUUklCVVRFNSIgZGF0YVR5cGU9InhzZDpzdHJpbmciIGJyZWFrT3JkZXI9IiIgZmllbGRPcmRlcj0iMjQiLz4KICAgICAgICAgICAgICAgPGVsZW1lbnQgbmFtZT0iQVRUUklCVVRFNiIgdmFsdWU9IkFUVFJJQlVURTYiIGxhYmVsPSJBVFRSSUJVVEU2IiBkYXRhVHlwZT0ieHNkOnN0cmluZyIgYnJlYWtPcmRlcj0iIiBmaWVsZE9yZGVyPSIyNSIvPgogICAgICAgICAgICAgICA8ZWxlbWVudCBuYW1lPSJBVFRSSUJVVEU3IiB2YWx1ZT0iQVRUUklCVVRFNyIgbGFiZWw9IkFUVFJJQlVURTciIGRhdGFUeXBlPSJ4c2Q6c3RyaW5nIiBicmVha09yZGVyPSIiIGZpZWxkT3JkZXI9IjI2Ii8+CiAgICAgICAgICAgICAgIDxlbGVtZW50IG5hbWU9IkFUVFJJQlVURTgiIHZhbHVlPSJBVFRSSUJVVEU4IiBsYWJlbD0iQVRUUklCVVRFOCIgZGF0YVR5cGU9InhzZDpzdHJpbmciIGJyZWFrT3JkZXI9IiIgZmllbGRPcmRlcj0iMjciLz4KICAgICAgICAgICAgICAgPGVsZW1lbnQgbmFtZT0iQVRUUklCVVRFOSIgdmFsdWU9IkFUVFJJQlVURTkiIGxhYmVsPSJBVFRSSUJVVEU5IiBkYXRhVHlwZT0ieHNkOnN0cmluZyIgYnJlYWtPcmRlcj0iIiBmaWVsZE9yZGVyPSIyOCIvPgogICAgICAgICAgICAgICA8ZWxlbWVudCBuYW1lPSJBVFRSSUJVVEUxMCIgdmFsdWU9IkFUVFJJQlVURTEwIiBsYWJlbD0iQVRUUklCVVRFMTAiIGRhdGFUeXBlPSJ4c2Q6c3RyaW5nIiBicmVha09yZGVyPSIiIGZpZWxkT3JkZXI9IjI5Ii8+CiAgICAgICAgICAgICAgIDxlbGVtZW50IG5hbWU9IkFUVFJJQlVURTExIiB2YWx1ZT0iQVRUUklCVVRFMTEiIGxhYmVsPSJBVFRSSUJVVEUxMSIgZGF0YVR5cGU9InhzZDpzdHJpbmciIGJyZWFrT3JkZXI9IiIgZmllbGRPcmRlcj0iMzAiLz4KICAgICAgICAgICAgICAgPGVsZW1lbnQgbmFtZT0iQVRUUklCVVRFMTIiIHZhbHVlPSJBVFRSSUJVVEUxMiIgbGFiZWw9IkFUVFJJQlVURTEyIiBkYXRhVHlwZT0ieHNkOnN0cmluZyIgYnJlYWtPcmRlcj0iIiBmaWVsZE9yZGVyPSIzMSIvPgogICAgICAgICAgICAgICA8ZWxlbWVudCBuYW1lPSJBVFRSSUJVVEUxMyIgdmFsdWU9IkFUVFJJQlVURTEzIiBsYWJlbD0iQVRUUklCVVRFMTMiIGRhdGFUeXBlPSJ4c2Q6c3RyaW5nIiBicmVha09yZGVyPSIiIGZpZWxkT3JkZXI9IjMyIi8+CiAgICAgICAgICAgICAgIDxlbGVtZW50IG5hbWU9IkFUVFJJQlVURTE0IiB2YWx1ZT0iQVRUUklCVVRFMTQiIGxhYmVsPSJBVFRSSUJVVEUxNCIgZGF0YVR5cGU9InhzZDpzdHJpbmciIGJyZWFrT3JkZXI9IiIgZmllbGRPcmRlcj0iMzMiLz4KICAgICAgICAgICAgICAgPGVsZW1lbnQgbmFtZT0iQVRUUklCVVRFMTUiIHZhbHVlPSJBVFRSSUJVVEUxNSIgbGFiZWw9IkFUVFJJQlVURTE1IiBkYXRhVHlwZT0ieHNkOnN0cmluZyIgYnJlYWtPcmRlcj0iIiBmaWVsZE9yZGVyPSIzNCIvPgogICAgICAgICAgICAgICA8ZWxlbWVudCBuYW1lPSJUQUciIHZhbHVlPSJUQUciIGxhYmVsPSJUQUciIGRhdGFUeXBlPSJ4c2Q6c3RyaW5nIiBicmVha09yZGVyPSIiIGZpZWxkT3JkZXI9IjM1Ii8+CiAgICAgICAgICAgICAgIDxlbGVtZW50IG5hbWU9IlNFVF9JRCIgdmFsdWU9IlNFVF9JRCIgbGFiZWw9IlNFVF9JRCIgZGF0YVR5cGU9InhzZDpkb3VibGUiIGJyZWFrT3JkZXI9IiIgZmllbGRPcmRlcj0iMzYiLz4KICAgICAgICAgICAgPC9ncm91cD4KICAgICAgICAgPC9kYXRhU3RydWN0dXJlPgogICAgICA8L25vZGVMaXN0PgogICA8L291dHB1dD4KICAgPGV2ZW50VHJpZ2dlcnMvPgogICA8bGV4aWNhbHMvPgogICA8cGFyYW1ldGVycy8+CiAgIDx2YWx1ZVNldHMvPgogICA8YnVyc3RpbmcvPgogICA8dmFsaWRhdGlvbnM+CiAgICAgIDx2YWxpZGF0aW9uPk48L3ZhbGlkYXRpb24+CiAgIDwvdmFsaWRhdGlvbnM+CiAgIDxkaXNwbGF5PgogICAgICA8bGF5b3V0cz4KICAgICAgICAgPGxheW91dCBuYW1lPSJMb29rdXBWYWx1ZXMiIGxlZnQ9IjI4NnB4IiB0b3A9IjBweCIvPgogICAgICAgICA8bGF5b3V0IG5hbWU9IkRBVEFfRFMiIGxlZnQ9IjZweCIgdG9wPSIyNjlweCIvPgogICAgICA8L2xheW91dHM+CiAgICAgIDxncm91cExpbmtzLz4KICAgPC9kaXNwbGF5Pgo8L2RhdGFNb2RlbD4K";

			// DataSource source = new ByteArrayDataSource(xml.getBytes(), "text/plain;
			// charset=UTF-8");
			// Decoder decoder = Base64.getDecoder();
			// DataSource source = new ByteArrayDataSource(decoder.decode(encode),
			// "text/plain; charset=UTF-8");
			//System.out.println("Xml to String ::::"+xml);
			DataSource source = new ByteArrayDataSource(xml.toString().getBytes(), "text/plain;charset=UTF-8");
			DataHandler dataHandler = new DataHandler(source);

			CreateObjectInSession createObject = new CreateObjectInSession();
			createObject.setObjectData(dataHandler);
			// createObject.setObjectName("Test_JOBS_STATS");
			createObject.setObjectName(requestId);
			createObject.setObjectType("xdm");
			createObject.setFolderAbsolutePathURL(loadProperties().getProperty("absolute-path"));
			createObject.setObjectDescription(" chunck dataset");
			createObject.setBipSessionToken(loginResponseToken);
			createObjectResponse = catalogStub.createObjectInSession(createObject);
			dataModelUrl = createObjectResponse.getCreateObjectInSessionReturn();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception();
		}
		return dataModelUrl;
	}

}