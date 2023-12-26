package com.cloud.connector.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cr_cloud_status_information", schema = "public")
@Data
public class CrCloudStatusInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="status_id",columnDefinition = "serial")
    private Integer statusId;
    @Column(name = "request_id", length = 100)
    private String requestId;
    @Column(name = "entity_id")
    private Integer entityId;

    @Column(name = "status", length = 20)
    private String status;

    @Column(name = "attribute1", length = 150)
    private String attribute1;

    @Column(name = "attribute2", length = 150)
    private String attribute2;

    @Column(name = "last_updated_by", length = 50)
    private String lastUpdatedBy;

    @Column(name = "last_update_date")
    private Date lastUpdateDate;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "created_by", length = 50)
    private String createdBy;

}
