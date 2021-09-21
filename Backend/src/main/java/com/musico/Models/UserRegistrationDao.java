//Author: Simar Saggu
// Created on: 14th July,2021
package com.musico.Models;

import com.musico.Requests.RegistrationRequest;
import com.musico.utils.MySQLConnection;

import java.sql.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserRegistrationDao {
    MySQLConnection mySQLConnection = MySQLConnection.getInstance();

    private String email;
    private String password;
    private String country;
    private String phoneNumber;
    private String name;
    private Integer followers;
    private Integer following;
    private Integer playlist;
    private byte[] profileImage;
    private String bio;

    public Boolean convertRegistrationRequest(RegistrationRequest registrationRequest) {
        this.email = registrationRequest.getEmail();
        this.password = registrationRequest.getPassword();
        this.country = registrationRequest.getCountry();
        this.phoneNumber = registrationRequest.getPhoneNumber();
        this.profileImage = registrationRequest.getProfileImage();
        this.name = registrationRequest.getName();
        this.followers=0;
        this.following=0;
        this.playlist=0;
        this.bio="";
        return true;
    }

    public  Boolean getUser() throws SQLException {

        Connection connection;
        try{
            String sql ="select email_id,ph_number from user where email_id='"+this.email+"' or ph_number='"+this.phoneNumber+"';";
            connection = mySQLConnection.getConnection();
            Statement statement =  connection.createStatement();
            ResultSet resultSet= statement.executeQuery(sql);
            if (resultSet.next()){
                return true;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        finally {
            mySQLConnection.closeConnection();
        }
        return false;
    }

    public Map<String,String> saveUser() throws SQLException {
        Map<String,String> status = new HashMap<>();
        UUID uuid= UUID.randomUUID();
        String newUserId = "usr_"+uuid.toString().replace("-","");
        Connection connection;
        try {
            connection = mySQLConnection.getConnection();
            String insertQ ="Insert into user(user_id,user_name,ph_number,email_id,country,password,followers,following,playlists,profile_img,bio) values(?,?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement statement = connection.prepareStatement(insertQ);
            statement.setString(1,newUserId);
            statement.setString(2,this.name);
            statement.setString(3,this.phoneNumber);
            statement.setString(4,this.email);
            statement.setString(5,this.country);
            statement.setString(6,this.password);
            statement.setInt(7,this.followers);
            statement.setInt(8,this.following);
            statement.setInt(9,this.playlist);
            statement.setBytes(10,this.profileImage);
            statement.setString(11,this.bio);
            statement.executeUpdate();
            status.put("status","true");
            status.put("userId",newUserId);
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
            status.put("status","false");
        }
        finally {
            mySQLConnection.closeConnection();
        }
        return status;
    }
}
