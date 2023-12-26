package com.cloud.connector.pojo;

import lombok.Data;

@Data
public class CloudDataProcessingReqPo {
    private Integer id;
    private String sqlQuery;
    private String destinationType;
    private String lookUpFlag;
    private String scheduledJobCall;
    private Long podId;
    private String metaData;
    private String tableName;
    private Long batchSize;
    private String createdBy;
    private String updatedBy;
    private Boolean isInternalServiceCall;
}
