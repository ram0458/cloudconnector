package com.cloud.connector.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name = "cr_cloud_data_process_view", schema = "public")
public class CloudDataProcessView {

    @Id
    private Long id;

    @Column(name = "sqlquery")
    private String sqlQuery;

    @Column(name = "destination_type")
    private String destinationType;

    @Column(name = "attribute1")
    private String attribute1;

    @Column(name = "attribute2")
    private String attribute2;

    @Column(name = "attribute3")
    private String attribute3;

    @Column(name = "attribute4")
    private String attribute4;

    @Column(name = "attribute5")
    private String attribute5;

    @Column(name = "last_updated_by")
    private String lastUpdatedBy;

    @Column(name = "last_update_date")
    private Date lastUpdateDate;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "lookup_flag")
    private String lookupFlag;

    @Column(name = "scheduled_job_call")
    private String scheduledJobCall;

    @Column(name = "pod_id")
    private Long podId;

    @Column(name = "table_name")
    private String tableName;

    @Column(name = "meta_data")
    private String metaData;

    @Column(name = "batchsize")
    private Integer batchSize;

    @Column(name = "status_id")
    private Integer statusId;

    @Column(name="status")
    private String status;
}
