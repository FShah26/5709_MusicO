//Author: Simar Saggu
// Created on: 16th July,2021
package com.musico.Models;

import com.musico.Requests.LoginRequest;
import com.musico.utils.MySQLConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class UserLoginDao {

    MySQLConnection mySQLConnection = MySQLConnection.getInstance();
    private String registeredName;
    private String password;


    public void convertLoginRequest(LoginRequest loginRequest){
        this.registeredName= loginRequest.getRegisteredName();
        this.password= loginRequest.getPassword();
    }


    public Map<String,String> getUserNumber() throws SQLException {
        Map<String,String> status = new HashMap<>();
        Connection connection;
        try{
            String sql ="select user_id,password from user where ph_number='"+this.registeredName+"';";
            connection = mySQLConnection.getConnection();
            Statement statement =  connection.createStatement();
            ResultSet resultSet= statement.executeQuery(sql);
            if (resultSet.next()){
                status.put("status","true");
                status.put("userId",resultSet.getString(1));
                status.put("password",resultSet.getString(2));
            }
            else{
                status.put("status","false");
            }
        }
        catch (SQLException sqlException) {
            status.put("status","false");
            sqlException.printStackTrace();

        }
        finally {
            mySQLConnection.closeConnection();
        }
        return status;
    }

    public Map<String,String> getUserEmail() throws SQLException {
        Map<String,String> status = new HashMap<>();
        Connection connection;
        try{
            String sql ="select user_id,password from user where email_id='"+this.registeredName+"';";
            connection = mySQLConnection.getConnection();
            Statement statement =  connection.createStatement();
            ResultSet resultSet= statement.executeQuery(sql);
            if (resultSet.next()){
                status.put("status","true");
                status.put("userId",resultSet.getString(1));
                status.put("password",resultSet.getString(2));
            }
            else{
                status.put("status","false");
            }
        }
        catch (SQLException sqlException) {
            status.put("status","false");
            sqlException.printStackTrace();

        }
        finally {
            mySQLConnection.closeConnection();
        }
        return status;
    }

    public Map<String,String> getUser(String emailId) throws SQLException {
        Map<String,String> status = new HashMap<>();
        Connection connection;
        try{
            String sql ="select password from user where email_id='"+emailId+"';";
            connection = mySQLConnection.getConnection();
            Statement statement =  connection.createStatement();
            ResultSet resultSet= statement.executeQuery(sql);
            if (resultSet.next()){
                status.put("status","true");
                status.put("password",resultSet.getString(1));
            }
            else{
                status.put("status","false");
            }
        }
        catch (SQLException sqlException) {
            status.put("status","false");
            sqlException.printStackTrace();

        }
        finally {
            mySQLConnection.closeConnection();
        }
        return status;
    }
}
