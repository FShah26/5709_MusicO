//Author:Venkata Kanakayya Prashant Vadlamani
//Created On: 15 July 2021
package com.musico.Services;

import com.musico.Models.Tracks;
import com.musico.Responses.AlbumPageResponse;
import com.musico.utils.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AlbumService {
    public AlbumPageResponse getAlbumDetails(String id){
        MySQLConnection mySQLConnection = MySQLConnection.getInstance();
        Connection connection = mySQLConnection.getConnection();
        String album_name = null,album_cover = null,artist_name = null;
        int artist_id = 0;
        List<Tracks> tracksList = new ArrayList<>();
        AlbumPageResponse albumResponse = new AlbumPageResponse();
        if (connection != null) {
            try {
                String query = "select album_name,artist_id,album_cover from albums where album_id="+id+";";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while(resultSet.next()){
                    album_name = resultSet.getString(1);
                    artist_id = resultSet.getInt(2);
                    album_cover = resultSet.getString(3);
                }
                String artist_query = "select artist_name from artists where artist_id="+artist_id+";";
                ResultSet rs = statement.executeQuery(artist_query);
                while(rs.next()){
                    artist_name = rs.getString(1);
                }
                String tracksquery = "select track_no,track_name,track_source from tracks where album_id="+id+";";
                ResultSet resultSetTracks = statement.executeQuery(tracksquery);
                while(resultSetTracks.next()){
                    Tracks tracks = new Tracks();
                    tracks.setTrack_no(resultSetTracks.getInt(1));
                    tracks.setTrack_name(resultSetTracks.getString(2));
                    tracks.setTrack_source(resultSetTracks.getString(3));
                    tracksList.add(tracks);
                }
                albumResponse.setAlbumName(album_name);
                albumResponse.setAlbumImage(album_cover);
                albumResponse.setArtistName(artist_name);
                albumResponse.setSongs(tracksList);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    mySQLConnection.closeConnection();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return albumResponse;
    }

    public void addRating(int rating, String album_name, String user_id){
        System.out.println(rating+album_name+user_id);
        MySQLConnection mySQLConnection = MySQLConnection.getInstance();
        Connection connection = mySQLConnection.getConnection();
        int album_id = -1;
        if (connection != null) {
            try {
                String albumquery = "select album_id from albums where album_name='"+album_name+"';";
                Statement albumstatement = connection.createStatement();
                ResultSet resultSetAlbum = albumstatement.executeQuery(albumquery);
                while(resultSetAlbum.next()){
                    album_id = resultSetAlbum.getInt(1);
                }
                String ratingQuery = "select rating from ratings where album_id ="+album_id+" and user_id='"+user_id+"';";
                Statement ratingstatement = connection.createStatement();
                ResultSet resultSetRating = ratingstatement.executeQuery(ratingQuery);
                if(!resultSetRating.next()){
                    String insertRating = "insert into ratings values(?,?,?)";
                    PreparedStatement insertStatement = connection.prepareStatement(insertRating);
                    insertStatement.setInt(1,album_id);
                    insertStatement.setString(2,user_id);
                    insertStatement.setInt(3,rating);
                    insertStatement.executeUpdate();
                }
                else{
                    String updateRating = "update ratings set rating="+rating+" where album_id="+album_id+" and user_id='"+user_id+"';";
                    Statement updateStatement = connection.createStatement();
                    updateStatement.executeUpdate(updateRating);
                }
                int avgRating = 0;
                String avgQuery = "select avg(rating) from ratings where album_id="+album_id+";";
                Statement avgstatement = connection.createStatement();
                ResultSet resultSetAvg = avgstatement.executeQuery(avgQuery);
                while (resultSetAvg.next()){
                    avgRating = resultSetAvg.getInt(1);
                }
                String updateRatingAlbum = "update albums set rating="+avgRating+" where album_id="+album_id+";";
                Statement updateStatementAlbum = connection.createStatement();
                updateStatementAlbum.executeUpdate(updateRatingAlbum);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    mySQLConnection.closeConnection();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
