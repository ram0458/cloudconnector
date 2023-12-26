/**
 * InvalidParametersException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.9  Built on : Nov 16, 2018 (12:05:37 GMT)
 */
package com.cloud.connector.stub.catalog;

public class InvalidParametersException extends java.lang.Exception {
    private static final long serialVersionUID = 1599046036150L;
    private com.cloud.connector.stub.catalog.CatalogServiceStub.Fault1 faultMessage;

    public InvalidParametersException() {
        super("InvalidParametersException");
    }

    public InvalidParametersException(java.lang.String s) {
        super(s);
    }

    public InvalidParametersException(java.lang.String s, java.lang.Throwable ex) {
        super(s, ex);
    }

    public InvalidParametersException(java.lang.Throwable cause) {
        super(cause);
    }

    public void setFaultMessage(
        com.cloud.connector.stub.catalog.CatalogServiceStub.Fault1 msg) {
        faultMessage = msg;
    }

    public com.cloud.connector.stub.catalog.CatalogServiceStub.Fault1 getFaultMessage() {
        return faultMessage;
    }
}
