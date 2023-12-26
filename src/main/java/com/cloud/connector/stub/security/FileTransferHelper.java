package com.cloud.connector.stub.security;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

@Component
public class FileTransferHelper {
    @Autowired
    Utils utils;

    static final Logger log = Logger.getLogger(FileTransferHelper.class);

    public static void fileTransfer(String jsonPrettyPrintString, String requestId, String dynamicQuery)
            throws Exception {
        log.info("Start of fileTransfer ######");
        ChannelSftp channelSftp = null;
        Session jschSession = null;
        try {
            Properties props = LoginCreateDataModelHelper.loadProperties();

            /*
             * Path tempDirWithPrefix = Files.createTempDirectory("");
             * log.info("tempDirWithPrefix:::" + tempDirWithPrefix);
             */
            String jsonFileName = requestId;
            String path = jsonFileName + ".json";
            log.info("path:::" + path);

            // Moving file remote location
            JSch jsch = new JSch();
            jschSession = jsch.getSession(props.getProperty("sftp-client-username"),
                    props.getProperty("sftp-client-host"));
            jschSession.setPassword(props.getProperty("sftp-client-password"));
            // jschSession.setPort(port);
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            jschSession.setConfig(config);
            jschSession.setTimeout(60000);
            jschSession.connect();

            channelSftp = (ChannelSftp) jschSession.openChannel("sftp");
            channelSftp.connect();
            channelSftp.cd(props.getProperty("file-upload-dir"));

            try (FileWriter file = new FileWriter(path)) {
                JSONObject output = new JSONObject(jsonPrettyPrintString);
                JSONObject dataDsObj = output.optJSONObject("ROWSET");
                if (dataDsObj != null)
                    file.write(jsonPrettyPrintString);
                else {
                    String s = Utils.modifyQuery(dynamicQuery);
                    String[] columnNames = s.split(",");
                    JSONObject jsonObj = new JSONObject();
                    JSONObject arrObj = new JSONObject();
                    JSONObject inerObj = new JSONObject();
                    JSONArray jsonArr = new JSONArray();
                    for (String str : columnNames) {
                        if (str.contains(".")) {
                            String[] columnNameArr = str.split("\\.");
                            str = columnNameArr[1];
                        }
                        arrObj.put(str, "");
                    }
                    jsonArr.put(arrObj);
                    inerObj.put("ROW", jsonArr);
                    jsonObj.put("ROWSET", inerObj);
                    System.out.println("JSON:::::::" + jsonObj);

                    file.write(jsonObj.toString());

                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new Exception();
            }

            JSONObject output = new JSONObject(jsonPrettyPrintString);
            JSONArray arr = null;
            JSONObject g1Obj = null;
            String csv = "";
            JSONObject dataDsObj = output.optJSONObject("ROWSET");
            if (dataDsObj != null)
                arr = dataDsObj.optJSONArray("ROW");
            if (dataDsObj != null)
                g1Obj = dataDsObj.optJSONObject("ROW");
            System.out.println(arr + "arr::::::::");
            System.out.println(g1Obj + "g1Obj:::::::::;");
            File file = new File(requestId + ".csv");
            if (arr != null) {
                csv = CDL.toString(arr);
            } else if (g1Obj != null) {
                JSONArray jsonArr = new JSONArray();
                jsonArr.put(g1Obj);
                // System.out.println("enetringesifffffff::::::");
                // csv=g1Obj.toString();
                csv = CDL.toString(jsonArr);
                // System.out.println(csv+":::::::::csv");
            } else {
                // System.out.println("enetringesleeeeeeee:::::::");
                csv = Utils.modifyQuery(dynamicQuery);
                System.out.println(csv + ":::::::::csv");
                if (csv.contains(".")) {
                    String[] columnNames = csv.split(",");
                    StringBuffer sb = new StringBuffer();
                    for (int i = 0; i < columnNames.length; i++) {
                        if (columnNames[i].contains(".")) {
                            String[] columnNameArr = columnNames[i].split("\\.");
                            columnNames[i] = columnNameArr[1];
                            sb.append(columnNames[i]);
                            if (i != columnNames.length - 1)
                                sb.append(",");
                        }
                    }
                    csv = sb.toString();
                }
            }

            // System.out.println(csv+"::::::::::csv");
            FileUtils.writeStringToFile(file, csv);

            File f = new File(path);
            channelSftp.put(new FileInputStream(f), f.getName());
            channelSftp.put(new FileInputStream(file), file.getName());

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        } finally {
            channelSftp.exit();
            channelSftp.disconnect();
            jschSession.disconnect();
        }
    }

    public  FileTransferResponsePo fileTransferHelper(String jsonPrettyPrintString, String requestId, int mapSetId, String extractionFlag) throws Exception {
        log.debug("Start of fileTransfer ######");
        FileTransferResponsePo fileTransferResponsePo = new FileTransferResponsePo();
        ChannelSftp channelSftp = null;
        Session jschSession = null;
        Path filePath = null;
        String ftpFileName = requestId + ".csv";
        FileInputStream fileInputStream = null;

        try {
            Properties props = LoginCreateDataModelHelper.loadProperties();
            channelSftp = utils.setupJsch();
            channelSftp.cd(props.getProperty("file-upload-dir"));
            // Coverting Json String to csv
            int rowCount = 0;
            JSONObject output = new JSONObject(jsonPrettyPrintString);
            JSONArray arr = null;
            JSONObject g1Obj = null;
            String csv = "";
            JSONObject dataDsObj = output.optJSONObject("ROWSET");
            if (dataDsObj != null)
                arr = dataDsObj.optJSONArray("ROW");

            if (dataDsObj != null)
                g1Obj = dataDsObj.optJSONObject("ROW");
            log.debug(arr + "arr::::::::");
            log.debug(g1Obj + "g1Obj:::::::::;");
            File file = File.createTempFile(requestId, ".csv");
            // File file = new File(tempDirWithPrefix + File.separator + requestId +
            // ".csv");
            filePath = Paths.get(file.getPath());
            log.debug("TempFilePath::::::" + filePath);
            if (arr != null) {
                rowCount = arr.length();
                csv = CDL.toString(arr);
            } else if (g1Obj != null) {
                JSONArray jsonArr = new JSONArray();
                jsonArr.put(g1Obj);
                rowCount = jsonArr.length();
                csv = CDL.toString(jsonArr);
            }

            if (rowCount !=0) {
                FileUtils.writeStringToFile(file, csv);
                fileInputStream = new FileInputStream(file);
                channelSftp.put(new FileInputStream(file), ftpFileName);
                fileTransferResponsePo = Utils.metaDataFormation(fileInputStream, extractionFlag, mapSetId);

                // Keeping file in FTP Location

                log.info("FTP File Name:::" + ftpFileName);
            }
            fileTransferResponsePo.setFileName(ftpFileName);
            fileTransferResponsePo.setRowCount((long)rowCount);
            return fileTransferResponsePo;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            throw new Exception();
        } finally {
            if (fileInputStream != null)
                fileInputStream.close();
            try {
                if (filePath != null)
                    filePath.toFile().deleteOnExit();
            } catch (Exception e) {
                log.error(e.getMessage());
                e.printStackTrace();
            }
            if (channelSftp != null) {
                channelSftp.exit();
                channelSftp.disconnect();
            }
        }
    }
}
