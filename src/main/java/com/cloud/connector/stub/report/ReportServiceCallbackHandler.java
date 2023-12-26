/**
 * ReportServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.9  Built on : Nov 16, 2018 (12:05:37 GMT)
 */
package com.cloud.connector.stub.report;


/**
 *  ReportServiceCallbackHandler Callback class, Users can extend this class and implement
 *  their own receiveResult and receiveError methods.
 */
public abstract class ReportServiceCallbackHandler {
    protected Object clientData;

    /**
     * User can pass in any object that needs to be accessed once the NonBlocking
     * Web service call is finished and appropriate method of this CallBack is called.
     * @param clientData Object mechanism by which the user can pass in user data
     * that will be avilable at the time this callback is called.
     */
    public ReportServiceCallbackHandler(Object clientData) {
        this.clientData = clientData;
    }

    /**
     * Please use this constructor if you don't want to set any clientData
     */
    public ReportServiceCallbackHandler() {
        this.clientData = null;
    }

    /**
     * Get the client data
     */
    public Object getClientData() {
        return clientData;
    }

    /**
     * auto generated Axis2 call back method for runDataModelInSession method
     * override this method for handling normal response from runDataModelInSession operation
     */
    public void receiveResultrunDataModelInSession(
        com.cloud.connector.stub.report.ReportServiceStub.RunDataModelInSessionResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from runDataModelInSession operation
     */
    public void receiveErrorrunDataModelInSession(Exception e) {
    }

    /**
     * auto generated Axis2 call back method for runDataModel method
     * override this method for handling normal response from runDataModel operation
     */
    public void receiveResultrunDataModel(
        com.cloud.connector.stub.report.ReportServiceStub.RunDataModelResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from runDataModel operation
     */
    public void receiveErrorrunDataModel(Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getXDOSchema method
     * override this method for handling normal response from getXDOSchema operation
     */
    public void receiveResultgetXDOSchema(
        com.cloud.connector.stub.report.ReportServiceStub.GetXDOSchemaResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getXDOSchema operation
     */
    public void receiveErrorgetXDOSchema(Exception e) {
    }

    /**
     * auto generated Axis2 call back method for updateXLIFFForReport method
     * override this method for handling normal response from updateXLIFFForReport operation
     */
    public void receiveResultupdateXLIFFForReport(
        com.cloud.connector.stub.report.ReportServiceStub.UpdateXLIFFForReportResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from updateXLIFFForReport operation
     */
    public void receiveErrorupdateXLIFFForReport(Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getReportParameters method
     * override this method for handling normal response from getReportParameters operation
     */
    public void receiveResultgetReportParameters(
        com.cloud.connector.stub.report.ReportServiceStub.GetReportParametersResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getReportParameters operation
     */
    public void receiveErrorgetReportParameters(Exception e) {
    }

    /**
     * auto generated Axis2 call back method for uploadTemplateForReport method
     * override this method for handling normal response from uploadTemplateForReport operation
     */
    public void receiveResultuploadTemplateForReport(
        com.cloud.connector.stub.report.ReportServiceStub.UploadTemplateForReportResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from uploadTemplateForReport operation
     */
    public void receiveErroruploadTemplateForReport(Exception e) {
    }

    /**
     * auto generated Axis2 call back method for uploadReportDataChunkInSession method
     * override this method for handling normal response from uploadReportDataChunkInSession operation
     */
    public void receiveResultuploadReportDataChunkInSession(
        com.cloud.connector.stub.report.ReportServiceStub.UploadReportDataChunkInSessionResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from uploadReportDataChunkInSession operation
     */
    public void receiveErroruploadReportDataChunkInSession(
        Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getTemplateAvailableXLIFFs method
     * override this method for handling normal response from getTemplateAvailableXLIFFs operation
     */
    public void receiveResultgetTemplateAvailableXLIFFs(
        com.cloud.connector.stub.report.ReportServiceStub.GetTemplateAvailableXLIFFsResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getTemplateAvailableXLIFFs operation
     */
    public void receiveErrorgetTemplateAvailableXLIFFs(Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getListOfSubjectArea method
     * override this method for handling normal response from getListOfSubjectArea operation
     */
    public void receiveResultgetListOfSubjectArea(
        com.cloud.connector.stub.report.ReportServiceStub.GetListOfSubjectAreaResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getListOfSubjectArea operation
     */
    public void receiveErrorgetListOfSubjectArea(Exception e) {
    }

    /**
     * auto generated Axis2 call back method for removeTemplateForReport method
     * override this method for handling normal response from removeTemplateForReport operation
     */
    public void receiveResultremoveTemplateForReport(
        com.cloud.connector.stub.report.ReportServiceStub.RemoveTemplateForReportResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from removeTemplateForReport operation
     */
    public void receiveErrorremoveTemplateForReport(Exception e) {
    }

    /**
     * auto generated Axis2 call back method for uploadReportDataChunk method
     * override this method for handling normal response from uploadReportDataChunk operation
     */
    public void receiveResultuploadReportDataChunk(
        com.cloud.connector.stub.report.ReportServiceStub.UploadReportDataChunkResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from uploadReportDataChunk operation
     */
    public void receiveErroruploadReportDataChunk(Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getTemplate method
     * override this method for handling normal response from getTemplate operation
     */
    public void receiveResultgetTemplate(
        com.cloud.connector.stub.report.ReportServiceStub.GetTemplateResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getTemplate operation
     */
    public void receiveErrorgetTemplate(Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getReportParametersInSession method
     * override this method for handling normal response from getReportParametersInSession operation
     */
    public void receiveResultgetReportParametersInSession(
        com.cloud.connector.stub.report.ReportServiceStub.GetReportParametersInSessionResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getReportParametersInSession operation
     */
    public void receiveErrorgetReportParametersInSession(Exception e) {
    }

    /**
     * auto generated Axis2 call back method for uploadXLIFFForReport method
     * override this method for handling normal response from uploadXLIFFForReport operation
     */
    public void receiveResultuploadXLIFFForReport(
        com.cloud.connector.stub.report.ReportServiceStub.UploadXLIFFForReportResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from uploadXLIFFForReport operation
     */
    public void receiveErroruploadXLIFFForReport(Exception e) {
    }

    /**
     * auto generated Axis2 call back method for downloadReportDataChunkInSession method
     * override this method for handling normal response from downloadReportDataChunkInSession operation
     */
    public void receiveResultdownloadReportDataChunkInSession(
        com.cloud.connector.stub.report.ReportServiceStub.DownloadReportDataChunkInSessionResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from downloadReportDataChunkInSession operation
     */
    public void receiveErrordownloadReportDataChunkInSession(
        Exception e) {
    }

    /**
     * auto generated Axis2 call back method for removeObjectFromCache method
     * override this method for handling normal response from removeObjectFromCache operation
     */
    public void receiveResultremoveObjectFromCache(
        com.cloud.connector.stub.report.ReportServiceStub.RemoveObjectFromCacheResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from removeObjectFromCache operation
     */
    public void receiveErrorremoveObjectFromCache(Exception e) {
    }

    /**
     * auto generated Axis2 call back method for updateReportDefinition method
     * override this method for handling normal response from updateReportDefinition operation
     */
    public void receiveResultupdateReportDefinition(
        com.cloud.connector.stub.report.ReportServiceStub.UpdateReportDefinitionResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from updateReportDefinition operation
     */
    public void receiveErrorupdateReportDefinition(Exception e) {
    }

    /**
     * auto generated Axis2 call back method for uploadTemplateForReportInSession method
     * override this method for handling normal response from uploadTemplateForReportInSession operation
     */
    public void receiveResultuploadTemplateForReportInSession(
        com.cloud.connector.stub.report.ReportServiceStub.UploadTemplateForReportInSessionResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from uploadTemplateForReportInSession operation
     */
    public void receiveErroruploadTemplateForReportInSession(
        Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getTemplateAvailableLocalesInSession method
     * override this method for handling normal response from getTemplateAvailableLocalesInSession operation
     */
    public void receiveResultgetTemplateAvailableLocalesInSession(
        com.cloud.connector.stub.report.ReportServiceStub.GetTemplateAvailableLocalesInSessionResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getTemplateAvailableLocalesInSession operation
     */
    public void receiveErrorgetTemplateAvailableLocalesInSession(
        Exception e) {
    }

    /**
     * auto generated Axis2 call back method for updateTemplateForReport method
     * override this method for handling normal response from updateTemplateForReport operation
     */
    public void receiveResultupdateTemplateForReport(
        com.cloud.connector.stub.report.ReportServiceStub.UpdateTemplateForReportResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from updateTemplateForReport operation
     */
    public void receiveErrorupdateTemplateForReport(Exception e) {
    }

    /**
     * auto generated Axis2 call back method for uploadXLIFFForReportInSession method
     * override this method for handling normal response from uploadXLIFFForReportInSession operation
     */
    public void receiveResultuploadXLIFFForReportInSession(
        com.cloud.connector.stub.report.ReportServiceStub.UploadXLIFFForReportInSessionResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from uploadXLIFFForReportInSession operation
     */
    public void receiveErroruploadXLIFFForReportInSession(Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getReportSampleData method
     * override this method for handling normal response from getReportSampleData operation
     */
    public void receiveResultgetReportSampleData(
        com.cloud.connector.stub.report.ReportServiceStub.GetReportSampleDataResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getReportSampleData operation
     */
    public void receiveErrorgetReportSampleData(Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getListOfSubjectAreaInSession method
     * override this method for handling normal response from getListOfSubjectAreaInSession operation
     */
    public void receiveResultgetListOfSubjectAreaInSession(
        com.cloud.connector.stub.report.ReportServiceStub.GetListOfSubjectAreaInSessionResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getListOfSubjectAreaInSession operation
     */
    public void receiveErrorgetListOfSubjectAreaInSession(Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getReportSampleDataInSession method
     * override this method for handling normal response from getReportSampleDataInSession operation
     */
    public void receiveResultgetReportSampleDataInSession(
        com.cloud.connector.stub.report.ReportServiceStub.GetReportSampleDataInSessionResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getReportSampleDataInSession operation
     */
    public void receiveErrorgetReportSampleDataInSession(Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getTemplateInSession method
     * override this method for handling normal response from getTemplateInSession operation
     */
    public void receiveResultgetTemplateInSession(
        com.cloud.connector.stub.report.ReportServiceStub.GetTemplateInSessionResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getTemplateInSession operation
     */
    public void receiveErrorgetTemplateInSession(Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getReportDefinitionInSession method
     * override this method for handling normal response from getReportDefinitionInSession operation
     */
    public void receiveResultgetReportDefinitionInSession(
        com.cloud.connector.stub.report.ReportServiceStub.GetReportDefinitionInSessionResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getReportDefinitionInSession operation
     */
    public void receiveErrorgetReportDefinitionInSession(Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getMobileAppDefinitionInSession method
     * override this method for handling normal response from getMobileAppDefinitionInSession operation
     */
    public void receiveResultgetMobileAppDefinitionInSession(
        com.cloud.connector.stub.report.ReportServiceStub.GetMobileAppDefinitionInSessionResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getMobileAppDefinitionInSession operation
     */
    public void receiveErrorgetMobileAppDefinitionInSession(
        Exception e) {
    }

    /**
     * auto generated Axis2 call back method for createReportInSession method
     * override this method for handling normal response from createReportInSession operation
     */
    public void receiveResultcreateReportInSession(
        com.cloud.connector.stub.report.ReportServiceStub.CreateReportInSessionResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from createReportInSession operation
     */
    public void receiveErrorcreateReportInSession(Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getTemplateAvailableLocales method
     * override this method for handling normal response from getTemplateAvailableLocales operation
     */
    public void receiveResultgetTemplateAvailableLocales(
        com.cloud.connector.stub.report.ReportServiceStub.GetTemplateAvailableLocalesResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getTemplateAvailableLocales operation
     */
    public void receiveErrorgetTemplateAvailableLocales(Exception e) {
    }

    /**
     * auto generated Axis2 call back method for runReportInSession method
     * override this method for handling normal response from runReportInSession operation
     */
    public void receiveResultrunReportInSession(
        com.cloud.connector.stub.report.ReportServiceStub.RunReportInSessionResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from runReportInSession operation
     */
    public void receiveErrorrunReportInSession(Exception e) {
    }

    /**
     * auto generated Axis2 call back method for updateMobileAppDefinition method
     * override this method for handling normal response from updateMobileAppDefinition operation
     */
    public void receiveResultupdateMobileAppDefinition(
        com.cloud.connector.stub.report.ReportServiceStub.UpdateMobileAppDefinitionResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from updateMobileAppDefinition operation
     */
    public void receiveErrorupdateMobileAppDefinition(Exception e) {
    }

    /**
     * auto generated Axis2 call back method for updateReportDefinitionInSession method
     * override this method for handling normal response from updateReportDefinitionInSession operation
     */
    public void receiveResultupdateReportDefinitionInSession(
        com.cloud.connector.stub.report.ReportServiceStub.UpdateReportDefinitionInSessionResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from updateReportDefinitionInSession operation
     */
    public void receiveErrorupdateReportDefinitionInSession(
        Exception e) {
    }

    /**
     * auto generated Axis2 call back method for runReport method
     * override this method for handling normal response from runReport operation
     */
    public void receiveResultrunReport(
        com.cloud.connector.stub.report.ReportServiceStub.RunReportResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from runReport operation
     */
    public void receiveErrorrunReport(Exception e) {
    }

    /**
     * auto generated Axis2 call back method for updateXLIFFForReportInSession method
     * override this method for handling normal response from updateXLIFFForReportInSession operation
     */
    public void receiveResultupdateXLIFFForReportInSession(
        com.cloud.connector.stub.report.ReportServiceStub.UpdateXLIFFForReportInSessionResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from updateXLIFFForReportInSession operation
     */
    public void receiveErrorupdateXLIFFForReportInSession(Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getXDOSchemaInSession method
     * override this method for handling normal response from getXDOSchemaInSession operation
     */
    public void receiveResultgetXDOSchemaInSession(
        com.cloud.connector.stub.report.ReportServiceStub.GetXDOSchemaInSessionResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getXDOSchemaInSession operation
     */
    public void receiveErrorgetXDOSchemaInSession(Exception e) {
    }

    /**
     * auto generated Axis2 call back method for removeTemplateForReportInSession method
     * override this method for handling normal response from removeTemplateForReportInSession operation
     */
    public void receiveResultremoveTemplateForReportInSession(
        com.cloud.connector.stub.report.ReportServiceStub.RemoveTemplateForReportInSessionResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from removeTemplateForReportInSession operation
     */
    public void receiveErrorremoveTemplateForReportInSession(
        Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getTemplateParameters method
     * override this method for handling normal response from getTemplateParameters operation
     */
    public void receiveResultgetTemplateParameters(
        com.cloud.connector.stub.report.ReportServiceStub.GetTemplateParametersResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getTemplateParameters operation
     */
    public void receiveErrorgetTemplateParameters(Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getTemplateParametersInSession method
     * override this method for handling normal response from getTemplateParametersInSession operation
     */
    public void receiveResultgetTemplateParametersInSession(
        com.cloud.connector.stub.report.ReportServiceStub.GetTemplateParametersInSessionResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getTemplateParametersInSession operation
     */
    public void receiveErrorgetTemplateParametersInSession(
        Exception e) {
    }

    /**
     * auto generated Axis2 call back method for updateTemplateForReportInSession method
     * override this method for handling normal response from updateTemplateForReportInSession operation
     */
    public void receiveResultupdateTemplateForReportInSession(
        com.cloud.connector.stub.report.ReportServiceStub.UpdateTemplateForReportInSessionResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from updateTemplateForReportInSession operation
     */
    public void receiveErrorupdateTemplateForReportInSession(
        Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getReportDefinition method
     * override this method for handling normal response from getReportDefinition operation
     */
    public void receiveResultgetReportDefinition(
        com.cloud.connector.stub.report.ReportServiceStub.GetReportDefinitionResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getReportDefinition operation
     */
    public void receiveErrorgetReportDefinition(Exception e) {
    }

    /**
     * auto generated Axis2 call back method for updateMobileAppDefinitionInSession method
     * override this method for handling normal response from updateMobileAppDefinitionInSession operation
     */
    public void receiveResultupdateMobileAppDefinitionInSession(
        com.cloud.connector.stub.report.ReportServiceStub.UpdateMobileAppDefinitionInSessionResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from updateMobileAppDefinitionInSession operation
     */
    public void receiveErrorupdateMobileAppDefinitionInSession(
        Exception e) {
    }

    /**
     * auto generated Axis2 call back method for createReport method
     * override this method for handling normal response from createReport operation
     */
    public void receiveResultcreateReport(
        com.cloud.connector.stub.report.ReportServiceStub.CreateReportResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from createReport operation
     */
    public void receiveErrorcreateReport(Exception e) {
    }

    /**
     * auto generated Axis2 call back method for removeObjectFromCacheInSession method
     * override this method for handling normal response from removeObjectFromCacheInSession operation
     */
    public void receiveResultremoveObjectFromCacheInSession(
        com.cloud.connector.stub.report.ReportServiceStub.RemoveObjectFromCacheInSessionResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from removeObjectFromCacheInSession operation
     */
    public void receiveErrorremoveObjectFromCacheInSession(
        Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getMobileAppDefinition method
     * override this method for handling normal response from getMobileAppDefinition operation
     */
    public void receiveResultgetMobileAppDefinition(
        com.cloud.connector.stub.report.ReportServiceStub.GetMobileAppDefinitionResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getMobileAppDefinition operation
     */
    public void receiveErrorgetMobileAppDefinition(Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getTemplateAvailableXLIFFsInSession method
     * override this method for handling normal response from getTemplateAvailableXLIFFsInSession operation
     */
    public void receiveResultgetTemplateAvailableXLIFFsInSession(
        com.cloud.connector.stub.report.ReportServiceStub.GetTemplateAvailableXLIFFsInSessionResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getTemplateAvailableXLIFFsInSession operation
     */
    public void receiveErrorgetTemplateAvailableXLIFFsInSession(
        Exception e) {
    }

    /**
     * auto generated Axis2 call back method for downloadReportDataChunk method
     * override this method for handling normal response from downloadReportDataChunk operation
     */
    public void receiveResultdownloadReportDataChunk(
        com.cloud.connector.stub.report.ReportServiceStub.DownloadReportDataChunkResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from downloadReportDataChunk operation
     */
    public void receiveErrordownloadReportDataChunk(Exception e) {
    }
}
