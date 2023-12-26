package com.cloud.connector.controller;

import com.cloud.connector.Validations.Validations;
import com.cloud.connector.exception.BadRequestException;
import com.cloud.connector.exception.ConvertRiteException;
import com.cloud.connector.exception.ValidationException;
import com.cloud.connector.model.CloudDataProcess;
import com.cloud.connector.model.CloudDataProcessView;
import com.cloud.connector.pojo.CloudDataPostRes;
import com.cloud.connector.pojo.CloudDataProcessPagReqPo;
import com.cloud.connector.pojo.CloudDataProcessingReqPo;
import com.cloud.connector.pojo.DeleteRes;
import com.cloud.connector.service.CloudService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/cloudconnector")
public class CloudController {

    @Autowired
    CloudService cloudService;
    @ApiOperation(value = "This Api Insert Query into cloud data process table ")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successful Response"),
            @ApiResponse(code = 500, message = "Server Side Error") })
    @PostMapping("/dataprocessing")
    public ResponseEntity<?> cloudDataProcessingRequests(
            @RequestBody CloudDataProcessingReqPo cloudDataProcessingReqPo) throws Exception {
        log.info("Start of cloudDataProcessingRequests in controller ###");
        CloudDataPostRes cloudDataProcess = new CloudDataPostRes();
        log.info( cloudDataProcessingReqPo.getSqlQuery()+cloudDataProcessingReqPo.getPodId()
                +cloudDataProcessingReqPo.getLookUpFlag());
        try {
            if ( Validations.isNullOrEmpty(cloudDataProcessingReqPo.getLookUpFlag())
                    || cloudDataProcessingReqPo.getPodId() == null )
                throw new Exception("podId and LookupFlag are mandatory fields");
            cloudDataProcess =  cloudService.cloudDataProcessingRequests(cloudDataProcessingReqPo);
        }
        catch (BadRequestException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(),  HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CloudDataPostRes>(cloudDataProcess, new HttpHeaders(), HttpStatus.OK);
    }
    @ApiOperation(value = "Deleting the Adhoc data by id wise ")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successful Response"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Server Side Error") })
    @DeleteMapping("/deleteadhocdata/{id}")
    public ResponseEntity<DeleteRes> deleteAdhocData(@PathVariable("id") Integer id) {
        DeleteRes deleteRes=new DeleteRes();
        log.info("start of  deleteAdhocData method##");
        try {
            if (Validations.isNullOrEmpty(String.valueOf(id))) {
                throw new BadRequestException("requestId is mandatory field");
            } else {
                cloudService.deleteAdhocData(id);
                deleteRes.setMessage("Deleting data  By given requestid succesfully");
            }
        } catch (BadRequestException e) {
            log.error(e.getMessage());
            deleteRes.setErrorMessage(e.getMessage());
            return new ResponseEntity<>(deleteRes, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error(e.getMessage());
            deleteRes.setErrorMessage(e.getMessage());
            return new ResponseEntity<>(deleteRes, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(deleteRes, HttpStatus.OK);
    }
    //    @ApiOperation(value = "This Api return all cloud data process requests")
//    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successful Response"),
//            @ApiResponse(code = 500, message = "Server Side Error") })
//    @GetMapping("/getallclouddatarequest")
//    public ResponseEntity<?> getAllCloudDataRequests(
//            CloudDataProcessPagReqPo cloudDataProcessPagReqPo) throws ConvertRiteException {
//        log.info("Start of getAllCloudDataRequests in controller ###");
//        List<CloudDataProcessView> cloudDataProcessViews = new ArrayList<>();
//        HttpHeaders httpHeaders = new HttpHeaders();
//        try {
//            cloudDataProcessViews = cloudService.getAllCloudDataRequests(cloudDataProcessPagReqPo,
//                    httpHeaders);
//        } catch (Exception e) {
//            log.error(e.getMessage());
//            return new ResponseEntity<>(e.getCause(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        return new ResponseEntity<List<CloudDataProcessView>>(cloudDataProcessViews, httpHeaders, HttpStatus.OK);
//    }
    @ApiOperation(value = "Get status of single request")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successful Response"),
            @ApiResponse(code = 500, message = "Server Side Error") })
    @GetMapping("/getstatus")
    public ResponseEntity<?> getStatusOfAdhoc(@RequestParam("id") Long id,
                                              @RequestParam("statusid") Integer statusId)
            throws Exception {
        log.info("Start of getStatusOfAdhoc Method :::");
        CloudDataProcessView cloudDataProcessView = new CloudDataProcessView();
        try {
            cloudDataProcessView = cloudService.getStatus(id,statusId);

        }
        catch (ValidationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getCause(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CloudDataProcessView>(cloudDataProcessView, HttpStatus.OK);
    }

    @ApiOperation(value = "This Api return all cloud data process requests")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successful Response"),
            @ApiResponse(code = 500, message = "Server Side Error") })
    @GetMapping("/getallclouddatabypodid")
    public ResponseEntity<?> getAllCloudDataByPodId(
            CloudDataProcessPagReqPo cloudDataProcessPagReqPo) throws Exception {
        log.info("Start of getAllCloudDataRequests in controller ###");
        List<CloudDataProcessView> cloudDataProcessViews = new ArrayList<>();
        HttpHeaders httpHeaders = new HttpHeaders();
        try {
            cloudDataProcessViews = cloudService.getAllCloudDataByPodId(cloudDataProcessPagReqPo,
                    httpHeaders);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getCause(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<List<CloudDataProcessView>>(cloudDataProcessViews, httpHeaders, HttpStatus.OK);
    }

    @ApiOperation(value = "This Api return all cloud data process requests")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successful Response"),
            @ApiResponse(code = 500, message = "Server Side Error") })
    @GetMapping("/getclouddatabypodid")
    public ResponseEntity<?> getCloudDataByPodId(
            CloudDataProcessPagReqPo cloudDataProcessPagReqPo) throws Exception {
        log.info("Start of getCloudDataRequests in controller ###");
        List<CloudDataProcess> cloudDataProcess = new ArrayList<>();
        HttpHeaders httpHeaders = new HttpHeaders();
        try {
            cloudDataProcess = cloudService.getCloudDataByPodId(cloudDataProcessPagReqPo,
                    httpHeaders);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getCause(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<List<CloudDataProcess>>(cloudDataProcess, httpHeaders, HttpStatus.OK);
    }

    @ApiOperation(value = "Get all status of process with given id")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successful Response"),
            @ApiResponse(code = 500, message = "Server Side Error") })
    @GetMapping("/getallstatus")
    public ResponseEntity<?> getAllStatusOfProcess(@RequestParam("id") Long id)
            throws Exception {
        log.info("Start of getAllStatusOfProcess Method :::");
        List<CloudDataProcessView> cloudDataProcessView = new ArrayList<>();
        try {
            cloudDataProcessView = cloudService.getAllStatus(id);

        }
        catch (ValidationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getCause(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<List<CloudDataProcessView>>(cloudDataProcessView, HttpStatus.OK);
    }
}
