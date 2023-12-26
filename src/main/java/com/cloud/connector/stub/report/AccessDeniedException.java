/**
 * AccessDeniedException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.9  Built on : Nov 16, 2018 (12:05:37 GMT)
 */
package com.cloud.connector.stub.report;

public class AccessDeniedException extends Exception {
    private static final long serialVersionUID = 1599046214184L;
    private com.cloud.connector.stub.report.ReportServiceStub.Fault faultMessage;

    public AccessDeniedException() {
        super("AccessDeniedException");
    }

    public AccessDeniedException(String s) {
        super(s);
    }

    public AccessDeniedException(String s, Throwable ex) {
        super(s, ex);
    }

    public AccessDeniedException(Throwable cause) {
        super(cause);
    }

    public void setFaultMessage(
    		com.cloud.connector.stub.report.ReportServiceStub.Fault msg) {
        faultMessage = msg;
    }

    public com.cloud.connector.stub.report.ReportServiceStub.Fault getFaultMessage() {
        return faultMessage;
    }
}
