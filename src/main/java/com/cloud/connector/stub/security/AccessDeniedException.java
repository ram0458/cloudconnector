/**
 * AccessDeniedException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.9  Built on : Nov 16, 2018 (12:05:37 GMT)
 */
package com.cloud.connector.stub.security;

public class AccessDeniedException extends java.lang.Exception {
    private static final long serialVersionUID = 1599021875601L;
    private com.cloud.connector.stub.security.SecurityServiceStub.Fault1 faultMessage;

    public AccessDeniedException() {
        super("AccessDeniedException");
    }

    public AccessDeniedException(java.lang.String s) {
        super(s);
    }

    public AccessDeniedException(java.lang.String s, java.lang.Throwable ex) {
        super(s, ex);
    }

    public AccessDeniedException(java.lang.Throwable cause) {
        super(cause);
    }

    public void setFaultMessage(
        com.cloud.connector.stub.security.SecurityServiceStub.Fault1 msg) {
        faultMessage = msg;
    }

    public com.cloud.connector.stub.security.SecurityServiceStub.Fault1 getFaultMessage() {
        return faultMessage;
    }
}
