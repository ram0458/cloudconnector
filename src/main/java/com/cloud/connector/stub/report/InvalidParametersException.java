/**
 * InvalidParametersException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.9  Built on : Nov 16, 2018 (12:05:37 GMT)
 */
package com.cloud.connector.stub.report;

public class InvalidParametersException extends Exception {
    private static final long serialVersionUID = 1599046214198L;
    private com.cloud.connector.stub.report.ReportServiceStub.Fault2 faultMessage;

    public InvalidParametersException() {
        super("InvalidParametersException");
    }

    public InvalidParametersException(String s) {
        super(s);
    }

    public InvalidParametersException(String s, Throwable ex) {
        super(s, ex);
    }

    public InvalidParametersException(Throwable cause) {
        super(cause);
    }

    public void setFaultMessage(
    		com.cloud.connector.stub.report.ReportServiceStub.Fault2 msg) {
        faultMessage = msg;
    }

    public com.cloud.connector.stub.report.ReportServiceStub.Fault2 getFaultMessage() {
        return faultMessage;
    }
}
