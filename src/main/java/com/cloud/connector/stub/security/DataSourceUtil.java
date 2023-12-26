//package com.cloud.connector.stub.security;
//
//import java.sql.Connection;
//import java.util.Properties;
//
//import org.apache.log4j.Logger;
//
//import oracle.jdbc.pool.OracleDataSource;
//import org.postgresql.ds.PGSimpleDataSource;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DataSourceUtil {
//	static final Logger log = Logger.getLogger(DataSourceUtil.class);
//	@Value("${datasource.hostname}")
//	private String dbHost;
//
////	@Value("${datasource.name}")
////	private String dbName;
//
//	@Value("${datasource.username}")
//	private String dbUsername;
//
//	@Value("${datasource.password}")
//	private String dbPassword;
//
//	@Value("${datasource.port}")
//	private int dbPort;
//	public  Connection createConnection() throws Exception {
//		log.info("Start of createConnection ##");
//		Connection con = null;
//		try {
//
//			PGSimpleDataSource dataSource = new PGSimpleDataSource();
//			dataSource.setServerName(dbHost);
//			dataSource.setDatabaseName(dbUsername);
//			dataSource.setUser(dbUsername);
//			dataSource.setPassword(dbPassword);
//			dataSource.setPortNumber(dbPort);
//
//			con = dataSource.getConnection();
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new Exception(e.getMessage());
//		}
//		return con;
//	}
//
//
//
//}
