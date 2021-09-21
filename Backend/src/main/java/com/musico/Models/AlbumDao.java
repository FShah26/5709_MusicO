//Author: Neehar Parupalli Ramakrishna
//Created On: 15th July 2021
package com.musico.Models;

import com.musico.Responses.AlbumResponse;
import com.musico.utils.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AlbumDao {

    MySQLConnection mySQLConnection = MySQLConnection.getInstance();
    public List<AlbumResponse> getAlbumList(String artistId) {
        List<AlbumResponse> albumResponseList = new ArrayList<>();
        Connection connection;
        try {
            connection = mySQLConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM albums WHERE artist_id="+artistId+" ORDER BY rating DESC");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                AlbumResponse albumResponse = new AlbumResponse();
                albumResponse.setAlbumId(resultSet.getInt("album_id"));
                albumResponse.setAlbumName(resultSet.getString("album_name"));
                albumResponse.setRating(resultSet.getInt("rating"));
                albumResponse.setAlbumCover(resultSet.getString("album_cover"));
                albumResponseList.add(albumResponse);
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
        return albumResponseList;
    }
}
