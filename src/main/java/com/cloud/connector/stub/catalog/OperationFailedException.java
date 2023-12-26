/**
 * OperationFailedException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.9  Built on : Nov 16, 2018 (12:05:37 GMT)
 */
package com.cloud.connector.stub.catalog;

public class OperationFailedException extends java.lang.Exception {
    private static final long serialVersionUID = 1599046036141L;
    private com.cloud.connector.stub.catalog.CatalogServiceStub.Fault2 faultMessage;

    public OperationFailedException() {
        super("OperationFailedException");
    }

    public OperationFailedException(java.lang.String s) {
        super(s);
    }

    public OperationFailedException(java.lang.String s, java.lang.Throwable ex) {
        super(s, ex);
    }

    public OperationFailedException(java.lang.Throwable cause) {
        super(cause);
    }

    public void setFaultMessage(
        com.cloud.connector.stub.catalog.CatalogServiceStub.Fault2 msg) {
        faultMessage = msg;
    }

    public com.cloud.connector.stub.catalog.CatalogServiceStub.Fault2 getFaultMessage() {
        return faultMessage;
    }
}
