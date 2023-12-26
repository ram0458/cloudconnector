/**
 * OperationFailedException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.9  Built on : Nov 16, 2018 (12:05:37 GMT)
 */
package com.cloud.connector.stub.report;

public class OperationFailedException extends Exception {
    private static final long serialVersionUID = 1599046214191L;
    private com.cloud.connector.stub.report.ReportServiceStub.Fault1 faultMessage;

    public OperationFailedException() {
        super("OperationFailedException");
    }

    public OperationFailedException(String s) {
        super(s);
    }

    public OperationFailedException(String s, Throwable ex) {
        super(s, ex);
    }

    public OperationFailedException(Throwable cause) {
        super(cause);
    }

    public void setFaultMessage(
    		com.cloud.connector.stub.report.ReportServiceStub.Fault1 msg) {
        faultMessage = msg;
    }

    public com.cloud.connector.stub.report.ReportServiceStub.Fault1 getFaultMessage() {
        return faultMessage;
    }
}
