package com.cloud.connector.stub.security;

import java.util.Properties;

import javax.activation.DataHandler;

import org.apache.log4j.Logger;

import com.cloud.connector.stub.report.ReportServiceStub;
import com.cloud.connector.stub.report.ReportServiceStub.CreateReportInSession;
import com.cloud.connector.stub.report.ReportServiceStub.CreateReportInSessionResponse;
import com.cloud.connector.stub.report.ReportServiceStub.ReportRequest;
import com.cloud.connector.stub.report.ReportServiceStub.RunReportInSession;
import com.cloud.connector.stub.report.ReportServiceStub.RunReportInSessionResponse;

public class ReportHelper {
	
	static final Logger log = Logger.getLogger(ReportHelper.class);

	// Service call for creating report
	public static String createReportService(String soapUrl,String loginResponseToken,String requestId,String dataModelUrl) throws Exception {
		log.info("Start of createReportService #####");
		String createReportResponse="";
		try {
			Properties props = com.cloud.connector.stub.security.LoginCreateDataModelHelper.loadProperties();
			
			ReportServiceStub reportStub = new ReportServiceStub(
					soapUrl + props.getProperty("report-service-url"));

			CreateReportInSession createReportInSession54 = new CreateReportInSession();
			createReportInSession54.setBipSessionToken(loginResponseToken);
			createReportInSession54.setReportName(requestId + ".xdo");
			createReportInSession54.setFolderAbsolutePathURL(props.getProperty("absolute-path"));
			createReportInSession54.setDataModelURL(dataModelUrl);
			createReportInSession54.setUpdateFlag(false);

			CreateReportInSessionResponse createReportInSessionRes = reportStub
					.createReportInSession(createReportInSession54);
			createReportResponse = createReportInSessionRes.getCreateReportInSessionReturn();
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return createReportResponse;
	}
	
	// service call to RunReport
	public static DataHandler runReport(String soapUrl,String loginResponseToken,String createReportResponse) throws Exception {
		log.info("Start of runReport ####### ");
		DataHandler dt=null;
		try {
			Properties props = com.cloud.connector.stub.security.LoginCreateDataModelHelper.loadProperties();
			ReportServiceStub reportStub = new ReportServiceStub(
					soapUrl + props.getProperty("report-service-url"));
			RunReportInSession runReportInSession = new RunReportInSession();
			ReportRequest reportRequest = new ReportRequest();
			reportRequest.setAttributeFormat("xml");
			reportRequest.setReportAbsolutePath(createReportResponse);
			reportRequest.setSizeOfDataChunkDownload(-1);

			runReportInSession.setBipSessionToken(loginResponseToken);
			runReportInSession.setReportRequest(reportRequest);

			RunReportInSessionResponse runReportInSessionRes = reportStub
					.runReportInSession(runReportInSession);

			 dt = runReportInSessionRes.getRunReportInSessionReturn().getReportBytes();
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return dt;
	}
	
}
