package com.cloud.connector.pojo;

import com.cloud.connector.model.CloudDataProcess;
import com.cloud.connector.model.CrCloudStatusInformation;
import lombok.Data;

@Data
public class CloudDataPostRes {
    private CloudDataProcess cloudDataProcess;
    private CrCloudStatusInformation crCloudStatusInformation;
    private String message;
}
