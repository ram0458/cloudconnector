package com.cloud.connector.stub.security;

import lombok.Data;

@Data
public class FileTransferResponsePo {
    private Long rowCount;
    private String fileName;
    private String columnNames;
    private String metadata;
}

