/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Connector;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ndfmac
 */
public class JDBCConnector {
     //Local connection
//    private static final String JDBC_LOADER = "com.mysql.jdbc.Driver";
//    private static final String URL = "jdbc:mysql://localhost:3306/ticketdb"; //New Web
//    private static final String LOGIN = "root";
//    private static final String PASSWORD = "root";

    //For Remote Connnection   === Set For Remote Connection in FilePathManager
    private static final String JDBC_LOADER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/thewealt_wealthmarketdb";
    private static final String LOGIN = "thewealt_WMUser1";
    private static final String PASSWORD = "@thewealthmarket123";
    
    
    private Connection connection;

    public JDBCConnector() throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
        Class.forName(JDBC_LOADER);
        connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
    }

    public Connection getConnection() throws SQLException {
        return connection;
    }
    
}
