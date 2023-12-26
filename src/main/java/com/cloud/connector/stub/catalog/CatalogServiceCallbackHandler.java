/**
 * CatalogServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.9  Built on : Nov 16, 2018 (12:05:37 GMT)
 */
package com.cloud.connector.stub.catalog;


/**
 *  CatalogServiceCallbackHandler Callback class, Users can extend this class and implement
 *  their own receiveResult and receiveError methods.
 */
public abstract class CatalogServiceCallbackHandler {
    protected Object clientData;
    
    /*test*/

    /**
     * User can pass in any object that needs to be accessed once the NonBlocking
     * Web service call is finished and appropriate method of this CallBack is called.
     * @param clientData Object mechanism by which the user can pass in user data
     * that will be avilable at the time this callback is called.
     */
    public CatalogServiceCallbackHandler(Object clientData) {
        this.clientData = clientData;
    }

    /**
     * Please use this constructor if you don't want to set any clientData
     */
    public CatalogServiceCallbackHandler() {
        this.clientData = null;
    }

    /**
     * Get the client data
     */
    public Object getClientData() {
        return clientData;
    }

    /**
     * auto generated Axis2 call back method for uploadObject method
     * override this method for handling normal response from uploadObject operation
     */
    public void receiveResultuploadObject(
        com.cloud.connector.stub.catalog.CatalogServiceStub.UploadObjectResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from uploadObject operation
     */
    public void receiveErroruploadObject(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for createObject method
     * override this method for handling normal response from createObject operation
     */
    public void receiveResultcreateObject(
        com.cloud.connector.stub.catalog.CatalogServiceStub.CreateObjectResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from createObject operation
     */
    public void receiveErrorcreateObject(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getFolderContentsInSession method
     * override this method for handling normal response from getFolderContentsInSession operation
     */
    public void receiveResultgetFolderContentsInSession(
        com.cloud.connector.stub.catalog.CatalogServiceStub.GetFolderContentsInSessionResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getFolderContentsInSession operation
     */
    public void receiveErrorgetFolderContentsInSession(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getMobileAppStorePathInSession method
     * override this method for handling normal response from getMobileAppStorePathInSession operation
     */
    public void receiveResultgetMobileAppStorePathInSession(
        com.cloud.connector.stub.catalog.CatalogServiceStub.GetMobileAppStorePathInSessionResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getMobileAppStorePathInSession operation
     */
    public void receiveErrorgetMobileAppStorePathInSession(
        java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for createObjectInSession method
     * override this method for handling normal response from createObjectInSession operation
     */
    public void receiveResultcreateObjectInSession(
        com.cloud.connector.stub.catalog.CatalogServiceStub.CreateObjectInSessionResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from createObjectInSession operation
     */
    public void receiveErrorcreateObjectInSession(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getObject method
     * override this method for handling normal response from getObject operation
     */
    public void receiveResultgetObject(
        com.cloud.connector.stub.catalog.CatalogServiceStub.GetObjectResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getObject operation
     */
    public void receiveErrorgetObject(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for updateObjectInSession method
     * override this method for handling normal response from updateObjectInSession operation
     */
    public void receiveResultupdateObjectInSession(
        com.cloud.connector.stub.catalog.CatalogServiceStub.UpdateObjectInSessionResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from updateObjectInSession operation
     */
    public void receiveErrorupdateObjectInSession(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for downloadXLIFF method
     * override this method for handling normal response from downloadXLIFF operation
     */
    public void receiveResultdownloadXLIFF(
        com.cloud.connector.stub.catalog.CatalogServiceStub.DownloadXLIFFResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from downloadXLIFF operation
     */
    public void receiveErrordownloadXLIFF(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for downloadObject method
     * override this method for handling normal response from downloadObject operation
     */
    public void receiveResultdownloadObject(
        com.cloud.connector.stub.catalog.CatalogServiceStub.DownloadObjectResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from downloadObject operation
     */
    public void receiveErrordownloadObject(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for downloadObjectInSession method
     * override this method for handling normal response from downloadObjectInSession operation
     */
    public void receiveResultdownloadObjectInSession(
        com.cloud.connector.stub.catalog.CatalogServiceStub.DownloadObjectInSessionResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from downloadObjectInSession operation
     */
    public void receiveErrordownloadObjectInSession(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for uploadXLIFFInSession method
     * override this method for handling normal response from uploadXLIFFInSession operation
     */
    public void receiveResultuploadXLIFFInSession(
        com.cloud.connector.stub.catalog.CatalogServiceStub.UploadXLIFFInSessionResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from uploadXLIFFInSession operation
     */
    public void receiveErroruploadXLIFFInSession(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getObjectInfoInSession method
     * override this method for handling normal response from getObjectInfoInSession operation
     */
    public void receiveResultgetObjectInfoInSession(
        com.cloud.connector.stub.catalog.CatalogServiceStub.GetObjectInfoInSessionResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getObjectInfoInSession operation
     */
    public void receiveErrorgetObjectInfoInSession(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getObjectInSession method
     * override this method for handling normal response from getObjectInSession operation
     */
    public void receiveResultgetObjectInSession(
        com.cloud.connector.stub.catalog.CatalogServiceStub.GetObjectInSessionResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getObjectInSession operation
     */
    public void receiveErrorgetObjectInSession(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for renameObject method
     * override this method for handling normal response from renameObject operation
     */
    public void receiveResultrenameObject(
        com.cloud.connector.stub.catalog.CatalogServiceStub.RenameObjectResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from renameObject operation
     */
    public void receiveErrorrenameObject(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getMobileAppStorePath method
     * override this method for handling normal response from getMobileAppStorePath operation
     */
    public void receiveResultgetMobileAppStorePath(
        com.cloud.connector.stub.catalog.CatalogServiceStub.GetMobileAppStorePathResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getMobileAppStorePath operation
     */
    public void receiveErrorgetMobileAppStorePath(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for uploadXLIFF method
     * override this method for handling normal response from uploadXLIFF operation
     */
    public void receiveResultuploadXLIFF(
        com.cloud.connector.stub.catalog.CatalogServiceStub.UploadXLIFFResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from uploadXLIFF operation
     */
    public void receiveErroruploadXLIFF(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for deleteObjectInSession method
     * override this method for handling normal response from deleteObjectInSession operation
     */
    public void receiveResultdeleteObjectInSession(
        com.cloud.connector.stub.catalog.CatalogServiceStub.DeleteObjectInSessionResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from deleteObjectInSession operation
     */
    public void receiveErrordeleteObjectInSession(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for updateObject method
     * override this method for handling normal response from updateObject operation
     */
    public void receiveResultupdateObject(
        com.cloud.connector.stub.catalog.CatalogServiceStub.UpdateObjectResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from updateObject operation
     */
    public void receiveErrorupdateObject(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for createFolderInSession method
     * override this method for handling normal response from createFolderInSession operation
     */
    public void receiveResultcreateFolderInSession(
        com.cloud.connector.stub.catalog.CatalogServiceStub.CreateFolderInSessionResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from createFolderInSession operation
     */
    public void receiveErrorcreateFolderInSession(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for objectExist method
     * override this method for handling normal response from objectExist operation
     */
    public void receiveResultobjectExist(
        com.cloud.connector.stub.catalog.CatalogServiceStub.ObjectExistResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from objectExist operation
     */
    public void receiveErrorobjectExist(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for downloadXLIFFInSession method
     * override this method for handling normal response from downloadXLIFFInSession operation
     */
    public void receiveResultdownloadXLIFFInSession(
        com.cloud.connector.stub.catalog.CatalogServiceStub.DownloadXLIFFInSessionResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from downloadXLIFFInSession operation
     */
    public void receiveErrordownloadXLIFFInSession(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for objectExistInSession method
     * override this method for handling normal response from objectExistInSession operation
     */
    public void receiveResultobjectExistInSession(
        com.cloud.connector.stub.catalog.CatalogServiceStub.ObjectExistInSessionResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from objectExistInSession operation
     */
    public void receiveErrorobjectExistInSession(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for copyObjectInSession method
     * override this method for handling normal response from copyObjectInSession operation
     */
    public void receiveResultcopyObjectInSession(
        com.cloud.connector.stub.catalog.CatalogServiceStub.CopyObjectInSessionResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from copyObjectInSession operation
     */
    public void receiveErrorcopyObjectInSession(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for renameObjectInSession method
     * override this method for handling normal response from renameObjectInSession operation
     */
    public void receiveResultrenameObjectInSession(
        com.cloud.connector.stub.catalog.CatalogServiceStub.RenameObjectInSessionResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from renameObjectInSession operation
     */
    public void receiveErrorrenameObjectInSession(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for copyObject method
     * override this method for handling normal response from copyObject operation
     */
    public void receiveResultcopyObject(
        com.cloud.connector.stub.catalog.CatalogServiceStub.CopyObjectResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from copyObject operation
     */
    public void receiveErrorcopyObject(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for createFolder method
     * override this method for handling normal response from createFolder operation
     */
    public void receiveResultcreateFolder(
        com.cloud.connector.stub.catalog.CatalogServiceStub.CreateFolderResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from createFolder operation
     */
    public void receiveErrorcreateFolder(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for uploadObjectInSession method
     * override this method for handling normal response from uploadObjectInSession operation
     */
    public void receiveResultuploadObjectInSession(
        com.cloud.connector.stub.catalog.CatalogServiceStub.UploadObjectInSessionResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from uploadObjectInSession operation
     */
    public void receiveErroruploadObjectInSession(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getFolderContents method
     * override this method for handling normal response from getFolderContents operation
     */
    public void receiveResultgetFolderContents(
        com.cloud.connector.stub.catalog.CatalogServiceStub.GetFolderContentsResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getFolderContents operation
     */
    public void receiveErrorgetFolderContents(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for deleteObject method
     * override this method for handling normal response from deleteObject operation
     */
    public void receiveResultdeleteObject(
        com.cloud.connector.stub.catalog.CatalogServiceStub.DeleteObjectResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from deleteObject operation
     */
    public void receiveErrordeleteObject(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getObjectInfo method
     * override this method for handling normal response from getObjectInfo operation
     */
    public void receiveResultgetObjectInfo(
        com.cloud.connector.stub.catalog.CatalogServiceStub.GetObjectInfoResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getObjectInfo operation
     */
    public void receiveErrorgetObjectInfo(java.lang.Exception e) {
    }
}
