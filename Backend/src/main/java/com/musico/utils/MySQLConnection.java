//Author: Simar Saggu
// Created on: 14th July,2021
package com.musico.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {

    private final String DATABASE_URL;
    private final String DATABASE_USERNAME;
    private final String DATABASE_PASSWORD;
    private final String DRIVER_NAME;
    private final Integer PORT;
    private Connection connection = null;
    private final String DATABASE_NAME;
    private static MySQLConnection databaseInstance;

    private MySQLConnection()
    {
        ApplicationConfiguration applicationConfiguration = ApplicationConfiguration.instance();
        this.DATABASE_URL = applicationConfiguration.getDATABASE_URL();
        this.DATABASE_USERNAME = applicationConfiguration.getDATABASE_USERNAME();
        this.DATABASE_PASSWORD = applicationConfiguration.getDATABASE_PASSWORD();
        this.DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
        this.PORT = applicationConfiguration.getPORT();
        this.DATABASE_NAME= "musico";
    }

    public static MySQLConnection getInstance(){
        if(databaseInstance == null)
        {
            databaseInstance = new MySQLConnection();
        }
        return databaseInstance;
    }
    public Connection getConnection()
    {
        try
        {
            Class.forName(this.DRIVER_NAME);
            connection = DriverManager.getConnection("jdbc:mysql://"+this.DATABASE_URL+":"+this.PORT+"/"+this.DATABASE_NAME+"?user="+this.DATABASE_USERNAME+"&password="+this.DATABASE_PASSWORD);
            System.out.println("connection created");
        }
        catch (ClassNotFoundException | SQLException classNotFoundException)
        {
            System.out.println(classNotFoundException);
        }
        return connection;
    }

    public void closeConnection() throws SQLException {
        if (connection != null)
        {
            connection.close();
            System.out.println("connection closed");
        }
    }
}
