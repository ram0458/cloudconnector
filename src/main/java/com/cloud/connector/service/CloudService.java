package com.cloud.connector.service;

import com.cloud.connector.exception.BadRequestException;
import com.cloud.connector.model.CloudDataProcess;
import com.cloud.connector.model.CloudDataProcessView;
import com.cloud.connector.model.CrCloudStatusInformation;
import com.cloud.connector.pojo.CloudDataPostRes;
import com.cloud.connector.pojo.CloudDataProcessPagReqPo;
import com.cloud.connector.pojo.CloudDataProcessingReqPo;
import com.cloud.connector.repository.CloudDataProcessViewRepository;
import com.cloud.connector.repository.CloudRepository;
import com.cloud.connector.repository.CrCloudStatusInformationRepository;
import com.cloud.connector.stub.security.Client;
import com.cloud.connector.stub.security.Utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CloudService {
    @Autowired
    Client client;
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    CrCloudStatusInformationRepository crCloudStatusInformationRepository;

    @Autowired
    CloudDataProcessViewRepository cloudDataProcessViewRepository;
    @Autowired
    CloudRepository cloudRepository;

    public CloudDataPostRes cloudDataProcessingRequests(CloudDataProcessingReqPo cloudDataProcessingReqPo) throws Exception {
        log.info("Start of cloudDataProcessingRequests Method::::::::");
        CloudDataProcess cloudDataProcess = new CloudDataProcess();
        CloudDataProcess cloudDataProcessRes = new CloudDataProcess();
        java.util.Date date = null;

        if (cloudDataProcessingReqPo.getId() == null) {
            if( cloudDataProcessingReqPo.getSqlQuery() == null) {
                cloudDataProcess.setSqlQuery("select * from " + Utils.validTableName(cloudDataProcessingReqPo.getTableName()));
            } else {
                cloudDataProcess.setSqlQuery(cloudDataProcessingReqPo.getSqlQuery());
            }
            cloudDataProcess.setPodId(cloudDataProcessingReqPo.getPodId());
            cloudDataProcess.setDestinationType(cloudDataProcessingReqPo.getDestinationType());
            date = new java.util.Date();
            cloudDataProcess.setCreationDate(date);
            cloudDataProcess.setCreatedBy(cloudDataProcessingReqPo.getCreatedBy());
            cloudDataProcess.setLookupFlag(cloudDataProcessingReqPo.getLookUpFlag());
            cloudDataProcess.setScheduledJobCall(cloudDataProcessingReqPo.getScheduledJobCall());

            if (("Table Sync").equalsIgnoreCase(cloudDataProcessingReqPo.getDestinationType())) {
                CloudDataProcess dataProcess = cloudRepository.findByTableNameAndPodId(cloudDataProcessingReqPo.getTableName(),cloudDataProcessingReqPo.getPodId());
                if (dataProcess != null) {
                    throw new BadRequestException("Table Name is already exist");
                } else {
                    cloudDataProcess.setTableName(cloudDataProcessingReqPo.getTableName());
                    cloudDataProcess.setMetaData(cloudDataProcessingReqPo.getMetaData());
                    cloudDataProcess.setBatchSize(cloudDataProcessingReqPo.getBatchSize());
                }
            } else {
                List<CloudDataProcess> dataProcess = cloudRepository.findAllBySqlQueryAndPodId(cloudDataProcessingReqPo.getSqlQuery(),cloudDataProcessingReqPo.getPodId());
                if (dataProcess != null && dataProcess.size() > 0) {
                    if(!cloudDataProcessingReqPo.getIsInternalServiceCall()) {
                        throw new BadRequestException("Lookup sql query is already exist");
                    } else {
                        cloudDataProcessRes = dataProcess.get(0);
                    }
                }
            }
        } else {
            Optional<CloudDataProcess> cloudDataProcessOptional = cloudRepository.findById(cloudDataProcessingReqPo.getId());
            if (cloudDataProcessOptional.isPresent()) {
                cloudDataProcess = cloudDataProcessOptional.get();
                if (("Table Sync").equalsIgnoreCase(cloudDataProcessingReqPo.getDestinationType())) {
                    CloudDataProcess dataProcess = cloudRepository.findByTableNameAndPodId(cloudDataProcessingReqPo.getTableName(),cloudDataProcessingReqPo.getPodId());
                    if (dataProcess != null && dataProcess.getId() != cloudDataProcessingReqPo.getId()) {
                        throw new BadRequestException("Table Name is already exist");
                    }
                } else {
                    List<CloudDataProcess> dataProcess = cloudRepository.findAllBySqlQueryAndPodId(cloudDataProcessingReqPo.getSqlQuery(),cloudDataProcessingReqPo.getPodId());
                    if (dataProcess != null && dataProcess.size() > 0) {
                        dataProcess.removeIf(dp -> dp.getId() == cloudDataProcessingReqPo.getId());
                        if (dataProcess.size() > 0) {
                            if(!cloudDataProcessingReqPo.getIsInternalServiceCall()) {
                                throw new BadRequestException("Lookup sql query is already exist");
                            } else {
                                cloudDataProcessRes = dataProcess.get(0);
                            }
                        }
                    }
                }
                if(cloudDataProcessingReqPo.getSqlQuery()== null) {
                    cloudDataProcess.setSqlQuery("select * from " + Utils.validTableName(cloudDataProcessingReqPo.getTableName()));
                } else {
                    cloudDataProcess.setSqlQuery(cloudDataProcessingReqPo.getSqlQuery());
                }
                cloudDataProcess.setDestinationType(cloudDataProcessingReqPo.getDestinationType());
                cloudDataProcess.setPodId(cloudDataProcessingReqPo.getPodId());
                date = new java.util.Date();
                cloudDataProcess.setLastUpdateDate(date);
                cloudDataProcess.setLastUpdatedBy(cloudDataProcessingReqPo.getUpdatedBy());
                cloudDataProcess.setMetaData(cloudDataProcessingReqPo.getMetaData());
                cloudDataProcess.setTableName(cloudDataProcessingReqPo.getTableName());
                cloudDataProcess.setBatchSize(cloudDataProcessingReqPo.getBatchSize());
            }
        }

        if(cloudDataProcessRes == null || cloudDataProcessRes.getId() == null) {
            cloudDataProcessRes = cloudRepository.save(cloudDataProcess);
        }

        CrCloudStatusInformation crCloudStatusInformation = new CrCloudStatusInformation();
        CrCloudStatusInformation crCloudStatusInformationRes = new CrCloudStatusInformation();
        date = new java.util.Date();
        crCloudStatusInformation.setStatus("Starting");
        crCloudStatusInformation.setEntityId(cloudDataProcessRes.getId());
        crCloudStatusInformation.setCreationDate(date);
        crCloudStatusInformation.setCreatedBy(cloudDataProcess.getCreatedBy());
        crCloudStatusInformation.setLastUpdateDate(date);
        crCloudStatusInformation.setLastUpdatedBy(cloudDataProcess.getLastUpdatedBy());

        crCloudStatusInformationRes = crCloudStatusInformationRepository.save(crCloudStatusInformation);
        //   Client.processRequests(jdbcTemplate, cloudDataProcessRes,crCloudStatusInformationRes);
        CloudDataPostRes cloudDataPostRes = new CloudDataPostRes();
        cloudDataPostRes.setCloudDataProcess(cloudDataProcessRes);
        cloudDataPostRes.setCrCloudStatusInformation(crCloudStatusInformationRes);
        cloudDataPostRes.setMessage("Cloud Data Successfully saved");
        client.processRequests(jdbcTemplate, cloudDataProcessRes, crCloudStatusInformationRes);
        return cloudDataPostRes;
    }


    public void deleteAdhocData(Integer requestId) {
        Optional<CloudDataProcess> desktopModelOptional = cloudRepository.findById(requestId);
        if (desktopModelOptional.isEmpty())
            throw new ValidationException("There is no data with this requestId");
        cloudRepository.deleteById(requestId);

    }

//    public List<CloudDataProcessView> getAllCloudDataRequests(CloudDataProcessPagReqPo cloudDataProcessPagReqPo, HttpHeaders httpHeaders) throws Exception {
//        log.info("Start of getAllCloudDataRequests Method::::::::");
//        List<CloudDataProcessView> xxrCloudDataProcess = new ArrayList<>();
//        Page<CloudDataProcessView> page = null;
//        try {
//            Pageable pageable = PageRequest.of(cloudDataProcessPagReqPo.getPageNo(),
//                    cloudDataProcessPagReqPo.getPageSize(),
//                    Sort.by(cloudDataProcessPagReqPo.getSortDirection(), cloudDataProcessPagReqPo.getSortBy()));
//            if (cloudDataProcessPagReqPo.getExtractionFlag() == null) {
//                page = cloudDataProcessViewRepository.findAll(pageable);
//            } else
//                page = cloudDataProcessViewRepository.findAllByDestinationType("Table Sync", pageable);
//
//            httpHeaders.set("pagecount", String.valueOf(page.getTotalPages()));
//            httpHeaders.set("totalcount", String.valueOf(page.getTotalElements()));
//            if (page.hasContent())
//                xxrCloudDataProcess = page.getContent();
//            // xxrCloudDataProcess = xxrCloudDataProcessRepository.findAll();
//        } catch (Exception e) {
//            throw new Exception(e.getMessage());
//        }
//        return xxrCloudDataProcess;
//    }


    public CloudDataProcessView getStatus(long requestId, Integer statusId) throws Exception {
        Optional<CloudDataProcessView> desktopModelOptional = cloudDataProcessViewRepository.findByIdAndStatusId(requestId, statusId);
        if (desktopModelOptional.isEmpty())
            throw new com.cloud.connector.exception.ValidationException( "There is no data with this requestId");
        return desktopModelOptional.get();
    }

    public List<CloudDataProcessView> getAllCloudDataByPodId(CloudDataProcessPagReqPo cloudDataProcessPagReqPo, HttpHeaders httpHeaders) throws Exception {
        log.info("Start of getAllCloudDataRequests Method::::::::");
        List<CloudDataProcessView> cloudDataProcessViews = new ArrayList<>();
        Page<CloudDataProcessView> page = null;
        try {
            Pageable pageable = PageRequest.of(cloudDataProcessPagReqPo.getPageNo(),
                    cloudDataProcessPagReqPo.getPageSize(),
                    Sort.by(cloudDataProcessPagReqPo.getSortDirection(), cloudDataProcessPagReqPo.getSortBy()));
            if (cloudDataProcessPagReqPo.getExtractionFlag() == null) {
                page = cloudDataProcessViewRepository.findAllByPodId(cloudDataProcessPagReqPo.getPodId(),pageable);
            } else
                page = cloudDataProcessViewRepository.findAllByDestinationTypeAndPodId("Table Sync", cloudDataProcessPagReqPo.getPodId(), pageable);

            httpHeaders.set("pagecount", String.valueOf(page.getTotalPages()));
            httpHeaders.set("totalcount", String.valueOf(page.getTotalElements()));
            if (page.hasContent())
                cloudDataProcessViews = page.getContent();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return cloudDataProcessViews;
    }

    public List<CloudDataProcess> getCloudDataByPodId(CloudDataProcessPagReqPo cloudDataProcessPagReqPo, HttpHeaders httpHeaders) throws Exception {
        log.info("Start of getAllCloudDataRequests Method::::::::");
        List<CloudDataProcess> cloudDataProcessViews = new ArrayList<>();
        Page<CloudDataProcess> page = null;
        try {
            Pageable pageable = PageRequest.of(cloudDataProcessPagReqPo.getPageNo(),
                    cloudDataProcessPagReqPo.getPageSize(),
                    Sort.by(cloudDataProcessPagReqPo.getSortDirection(), cloudDataProcessPagReqPo.getSortBy()));
            if (cloudDataProcessPagReqPo.getExtractionFlag() == null) {
                page = cloudRepository.findAllByPodId(cloudDataProcessPagReqPo.getPodId(),pageable);
            } else
                page = cloudRepository.findAllByDestinationTypeAndPodId("Table Sync", cloudDataProcessPagReqPo.getPodId(), pageable);

            httpHeaders.set("pagecount", String.valueOf(page.getTotalPages()));
            httpHeaders.set("totalcount", String.valueOf(page.getTotalElements()));
            if (page.hasContent())
                cloudDataProcessViews = page.getContent();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return cloudDataProcessViews;
    }

    public List<CloudDataProcessView> getAllStatus(long requestId) throws Exception {
        List<CloudDataProcessView> desktopModelOptional = cloudDataProcessViewRepository.findAllById(requestId);
        if (desktopModelOptional.isEmpty())
            throw new com.cloud.connector.exception.ValidationException( "There is no data with this requestId");
        return desktopModelOptional;
    }
}
