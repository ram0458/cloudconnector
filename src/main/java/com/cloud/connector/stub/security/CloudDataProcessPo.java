package com.cloud.connector.stub.security;

public class CloudDataProcessPo {

	private String requestId;
	private String lookUpFlag;
	private String sqlQuery;

	private String scheduledJobCall;
	private String userName;
	private String password;
	private String cloudUrl;
	private String metadata;
	private String tableName;
	private String extractionFlag;
	private int mapSetId;

	private String batchLimit;

	public String getBatchLimit() {
		return batchLimit;
	}

	public void setBatchLimit(String batchLimit) {
		this.batchLimit = batchLimit;
	}

	public int getMapSetId() {
		return mapSetId;
	}

	public void setMapSetId(int mapSetId) {
		this.mapSetId = mapSetId;
	}

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getExtractionFlag() {
		return extractionFlag;
	}

	public void setExtractionFlag(String extractionFlag) {
		this.extractionFlag = extractionFlag;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCloudUrl() {
		return cloudUrl;
	}

	public void setCloudUrl(String cloudUrl) {
		this.cloudUrl = cloudUrl;
	}

	public String getScheduledJobCall() {
		return scheduledJobCall;
	}

	public void setScheduledJobCall(String scheduledJobCall) {
		this.scheduledJobCall = scheduledJobCall;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getLookUpFlag() {
		return lookUpFlag;
	}

	public void setLookUpFlag(String lookUpFlag) {
		this.lookUpFlag = lookUpFlag;
	}

	public String getSqlQuery() {
		return sqlQuery;
	}

	public void setSqlQuery(String sqlQuery) {
		this.sqlQuery = sqlQuery;
	}

}
