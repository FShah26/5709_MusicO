//Author: Neehar Parupalli Ramakrishna
//Created On: 15th July 2021
package com.musico.Models;

import com.musico.Responses.ArtistResponse;
import com.musico.utils.MySQLConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ArtistDao {


    public ArtistResponse getArtistDetails(String artistId) {
        ArtistResponse artistResponse = new ArtistResponse();
        MySQLConnection mySQLConnection = MySQLConnection.getInstance();
        Connection connection;
        try {
            connection = mySQLConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM artists WHERE artist_id="+artistId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                artistResponse.setArtistName(resultSet.getString("artist_name"));
                artistResponse.setArtistImage(resultSet.getString("artist_image"));
                artistResponse.setArtistId(resultSet.getInt("artist_id"));
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                mySQLConnection.closeConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return artistResponse;
    }
}
