/**
 * SecurityServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.9  Built on : Nov 16, 2018 (12:05:37 GMT)
 */
package com.cloud.connector.stub.security;


/**
 *  SecurityServiceCallbackHandler Callback class, Users can extend this class and implement
 *  their own receiveResult and receiveError methods.
 */
public abstract class SecurityServiceCallbackHandler {
    protected Object clientData;

    /**
     * User can pass in any object that needs to be accessed once the NonBlocking
     * Web service call is finished and appropriate method of this CallBack is called.
     * @param clientData Object mechanism by which the user can pass in user data
     * that will be avilable at the time this callback is called.
     */
    public SecurityServiceCallbackHandler(Object clientData) {
        this.clientData = clientData;
    }

    /**
     * Please use this constructor if you don't want to set any clientData
     */
    public SecurityServiceCallbackHandler() {
        this.clientData = null;
    }

    /**
     * Get the client data
     */
    public Object getClientData() {
        return clientData;
    }

    /**
     * auto generated Axis2 call back method for deleteRole method
     * override this method for handling normal response from deleteRole operation
     */
    public void receiveResultdeleteRole(
        com.cloud.connector.stub.security.SecurityServiceStub.DeleteRoleResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from deleteRole operation
     */
    public void receiveErrordeleteRole(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for updateUser method
     * override this method for handling normal response from updateUser operation
     */
    public void receiveResultupdateUser(
        com.cloud.connector.stub.security.SecurityServiceStub.UpdateUserResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from updateUser operation
     */
    public void receiveErrorupdateUser(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for isAdmin method
     * override this method for handling normal response from isAdmin operation
     */
    public void receiveResultisAdmin(
        com.cloud.connector.stub.security.SecurityServiceStub.IsAdminResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from isAdmin operation
     */
    public void receiveErrorisAdmin(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getBIPHTTPSessionInterval method
     * override this method for handling normal response from getBIPHTTPSessionInterval operation
     */
    public void receiveResultgetBIPHTTPSessionInterval(
        com.cloud.connector.stub.security.SecurityServiceStub.GetBIPHTTPSessionIntervalResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getBIPHTTPSessionInterval operation
     */
    public void receiveErrorgetBIPHTTPSessionInterval(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for deleteUser method
     * override this method for handling normal response from deleteUser operation
     */
    public void receiveResultdeleteUser(
        com.cloud.connector.stub.security.SecurityServiceStub.DeleteUserResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from deleteUser operation
     */
    public void receiveErrordeleteUser(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for isExcelAnalyzer method
     * override this method for handling normal response from isExcelAnalyzer operation
     */
    public void receiveResultisExcelAnalyzer(
        com.cloud.connector.stub.security.SecurityServiceStub.IsExcelAnalyzerResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from isExcelAnalyzer operation
     */
    public void receiveErrorisExcelAnalyzer(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for createUser method
     * override this method for handling normal response from createUser operation
     */
    public void receiveResultcreateUser(
        com.cloud.connector.stub.security.SecurityServiceStub.CreateUserResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from createUser operation
     */
    public void receiveErrorcreateUser(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for isReportDeveloper method
     * override this method for handling normal response from isReportDeveloper operation
     */
    public void receiveResultisReportDeveloper(
        com.cloud.connector.stub.security.SecurityServiceStub.IsReportDeveloperResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from isReportDeveloper operation
     */
    public void receiveErrorisReportDeveloper(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getSecurityModel method
     * override this method for handling normal response from getSecurityModel operation
     */
    public void receiveResultgetSecurityModel(
        com.cloud.connector.stub.security.SecurityServiceStub.GetSecurityModelResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getSecurityModel operation
     */
    public void receiveErrorgetSecurityModel(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for isUserExists method
     * override this method for handling normal response from isUserExists operation
     */
    public void receiveResultisUserExists(
        com.cloud.connector.stub.security.SecurityServiceStub.IsUserExistsResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from isUserExists operation
     */
    public void receiveErrorisUserExists(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for checkObjectPermissionsInSession method
     * override this method for handling normal response from checkObjectPermissionsInSession operation
     */
    public void receiveResultcheckObjectPermissionsInSession(
        com.cloud.connector.stub.security.SecurityServiceStub.CheckObjectPermissionsInSessionResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from checkObjectPermissionsInSession operation
     */
    public void receiveErrorcheckObjectPermissionsInSession(
        java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for isDataModelDeveloper method
     * override this method for handling normal response from isDataModelDeveloper operation
     */
    public void receiveResultisDataModelDeveloper(
        com.cloud.connector.stub.security.SecurityServiceStub.IsDataModelDeveloperResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from isDataModelDeveloper operation
     */
    public void receiveErrorisDataModelDeveloper(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for login method
     * override this method for handling normal response from login operation
     */
    public void receiveResultlogin(
        com.cloud.connector.stub.security.SecurityServiceStub.LoginResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from login operation
     */
    public void receiveErrorlogin(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for removeRolesFromUser method
     * override this method for handling normal response from removeRolesFromUser operation
     */
    public void receiveResultremoveRolesFromUser(
        com.cloud.connector.stub.security.SecurityServiceStub.RemoveRolesFromUserResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from removeRolesFromUser operation
     */
    public void receiveErrorremoveRolesFromUser(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for checkObjectPermissions method
     * override this method for handling normal response from checkObjectPermissions operation
     */
    public void receiveResultcheckObjectPermissions(
        com.cloud.connector.stub.security.SecurityServiceStub.CheckObjectPermissionsResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from checkObjectPermissions operation
     */
    public void receiveErrorcheckObjectPermissions(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for createRole method
     * override this method for handling normal response from createRole operation
     */
    public void receiveResultcreateRole(
        com.cloud.connector.stub.security.SecurityServiceStub.CreateRoleResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from createRole operation
     */
    public void receiveErrorcreateRole(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for updateRole method
     * override this method for handling normal response from updateRole operation
     */
    public void receiveResultupdateRole(
        com.cloud.connector.stub.security.SecurityServiceStub.UpdateRoleResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from updateRole operation
     */
    public void receiveErrorupdateRole(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getObjectSecurityXML method
     * override this method for handling normal response from getObjectSecurityXML operation
     */
    public void receiveResultgetObjectSecurityXML(
        com.cloud.connector.stub.security.SecurityServiceStub.GetObjectSecurityXMLResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getObjectSecurityXML operation
     */
    public void receiveErrorgetObjectSecurityXML(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for notifyBIEEPreferencesUpdatedWithString method
     * override this method for handling normal response from notifyBIEEPreferencesUpdatedWithString operation
     */
    public void receiveResultnotifyBIEEPreferencesUpdatedWithString(
        com.cloud.connector.stub.security.SecurityServiceStub.NotifyBIEEPreferencesUpdatedWithStringResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from notifyBIEEPreferencesUpdatedWithString operation
     */
    public void receiveErrornotifyBIEEPreferencesUpdatedWithString(
        java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for validateLogin method
     * override this method for handling normal response from validateLogin operation
     */
    public void receiveResultvalidateLogin(
        com.cloud.connector.stub.security.SecurityServiceStub.ValidateLoginResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from validateLogin operation
     */
    public void receiveErrorvalidateLogin(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for isScheduler method
     * override this method for handling normal response from isScheduler operation
     */
    public void receiveResultisScheduler(
        com.cloud.connector.stub.security.SecurityServiceStub.IsSchedulerResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from isScheduler operation
     */
    public void receiveErrorisScheduler(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for isOnlineAnalyzer method
     * override this method for handling normal response from isOnlineAnalyzer operation
     */
    public void receiveResultisOnlineAnalyzer(
        com.cloud.connector.stub.security.SecurityServiceStub.IsOnlineAnalyzerResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from isOnlineAnalyzer operation
     */
    public void receiveErrorisOnlineAnalyzer(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for notifyBIEEPreferencesUpdated method
     * override this method for handling normal response from notifyBIEEPreferencesUpdated operation
     */
    public void receiveResultnotifyBIEEPreferencesUpdated(
        com.cloud.connector.stub.security.SecurityServiceStub.NotifyBIEEPreferencesUpdatedResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from notifyBIEEPreferencesUpdated operation
     */
    public void receiveErrornotifyBIEEPreferencesUpdated(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for assignRolesToUser method
     * override this method for handling normal response from assignRolesToUser operation
     */
    public void receiveResultassignRolesToUser(
        com.cloud.connector.stub.security.SecurityServiceStub.AssignRolesToUserResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from assignRolesToUser operation
     */
    public void receiveErrorassignRolesToUser(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for impersonate method
     * override this method for handling normal response from impersonate operation
     */
    public void receiveResultimpersonate(
        com.cloud.connector.stub.security.SecurityServiceStub.ImpersonateResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from impersonate operation
     */
    public void receiveErrorimpersonate(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for logout method
     * override this method for handling normal response from logout operation
     */
    public void receiveResultlogout(
        com.cloud.connector.stub.security.SecurityServiceStub.LogoutResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from logout operation
     */
    public void receiveErrorlogout(java.lang.Exception e) {
    }
}
