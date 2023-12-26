package com.cloud.connector.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ClientConnection {

    public static Connection getConnection(String clientConnection,String clientUser,String clientPassword) throws SQLException {
        return  DriverManager.getConnection(clientConnection, clientUser, clientPassword);
    }
}
