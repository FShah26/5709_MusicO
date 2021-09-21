//Author: Simar Saggu
// Created on: 16th July,2021
package com.musico.Models;

import com.musico.Requests.UpdateRequest;
import com.musico.utils.MySQLConnection;
import org.apache.tomcat.util.codec.binary.Base64;

import java.sql.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class UserProfileDao {


    MySQLConnection mySQLConnection = MySQLConnection.getInstance();

    public String convertRequestQuery(UpdateRequest updateRequest){
        String query ="Update user set ";
        String name = updateRequest.getName();
        String password = updateRequest.getPassword();
        String country = updateRequest.getCountry();
        byte[] profileImage = updateRequest.getProfileImage();
        String bio = updateRequest.getBio();
        String userId = updateRequest.getUserId();
        if (!name.equals(""))
        {
            query += " user_name='"+name+"'";
        }
        if (!password.equals(""))
        {
            query += " ,password='"+password+"'";
        }
        if (!country.equals(""))
        {
            query += " ,country='"+country+"'";
        }
        if(!bio.equals("")){
            query += " ,bio='"+bio+"'";
        }

        query += " ,profile_img=?";



        query += " where user_id='"+userId+"';";
        return query;
    }

    public Map<String,String> fetchUser (String userId) throws SQLException {
        Map<String,String> data = new HashMap<>();
        Connection connection;

        try{
            connection = mySQLConnection.getConnection();
            String sql = " Select user_name, ph_number, email_id, country,bio, profile_img, followers, following, playlists " +
                    "from user where user_id='"+userId+"';";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                data.put("status","exists");
                data.put("name",resultSet.getString(1));
                data.put("phoneNumber",resultSet.getString(2));
                data.put("email",resultSet.getString(3));
                data.put("country",resultSet.getString(4));
                data.put("bio",resultSet.getString(5));
                data.put("profileImage", Base64.encodeBase64String(resultSet.getBytes(6)));
                data.put("followers",String.valueOf(resultSet.getInt(7)));
                data.put("following",String.valueOf(resultSet.getInt(8)));
                data.put("playlists",String.valueOf(resultSet.getInt(9)));
            }
            else{
                data.put("status","not_exists");
            }

        }
        catch (SQLException | NullPointerException sqlException){
            sqlException.printStackTrace();
            data.put("status","not_exists");

        } finally {
            mySQLConnection.closeConnection();
        }

        return data;
    }

    public Boolean updateUser(UpdateRequest updateRequest) throws SQLException {
        Boolean status;
        Connection connection;
        String updateQuery = convertRequestQuery(updateRequest);
        try {
            connection = mySQLConnection.getConnection();

            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setBytes(1,updateRequest.getProfileImage());
            statement.executeUpdate();

            status = true;
        }
        catch(SQLException sqlException){

            sqlException.printStackTrace();
            status = false;
        }
        finally {
            mySQLConnection.closeConnection();
        }
        return status;
    }



    public Boolean delete (String userId) throws SQLException {
        Boolean status;
        Connection connection;
        try {
            connection = mySQLConnection.getConnection();
            String deleteString = "delete from user where user_id='"+userId+"'; ";
            PreparedStatement statement = connection.prepareStatement(deleteString);
            statement.executeUpdate();
            status = true;
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
            status = false;
        }
        finally {
            mySQLConnection.closeConnection();
        }
        return status;
    }
}

