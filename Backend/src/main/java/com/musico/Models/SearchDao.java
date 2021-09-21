//Author: Neehar Parupalli Ramakrishna
//Created On: 15th July 2021
package com.musico.Models;

import com.musico.Responses.*;
import com.musico.utils.MySQLConnection;
import org.apache.tomcat.util.codec.binary.Base64;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SearchDao {

    public SearchResponse search(String searchText) {
        Connection connection;
        MySQLConnection mySQLConnection = MySQLConnection.getInstance();
        SearchResponse searchResponse = new SearchResponse();
        try {
            connection = mySQLConnection.getConnection();
            //Artist Search
            String artistQuery = "SELECT * FROM artists WHERE artist_name LIKE '%"+searchText+"%'";
            PreparedStatement artistStatement = connection.prepareStatement(artistQuery);
            ResultSet artistResult = artistStatement.executeQuery();
            List<ArtistResponse> artistResponseList = new ArrayList<>();
            while (artistResult.next()) {
                ArtistResponse artistResponse = new ArtistResponse();
                artistResponse.setArtistName(artistResult.getString("artist_name"));
                artistResponse.setArtistImage(artistResult.getString("artist_image"));
                artistResponse.setArtistId(artistResult.getInt("artist_id"));
                artistResponseList.add(artistResponse);
            }
            if (!artistResponseList.isEmpty()) {
                searchResponse.setArtists(artistResponseList);
            }
            artistResult.close();

            //Album Search
            String albumQuery = "SELECT * FROM albums WHERE album_name LIKE '%"+searchText+"%'";
            PreparedStatement albumStatement = connection.prepareStatement(albumQuery);
            ResultSet albumResult = albumStatement.executeQuery();
            List<AlbumResponse> albumResponseList = new ArrayList<>();
            while (albumResult.next()) {
                AlbumResponse albumResponse = new AlbumResponse();
                albumResponse.setAlbumName(albumResult.getString("album_name"));
                albumResponse.setAlbumId(albumResult.getInt("album_id"));
                albumResponse.setAlbumCover(albumResult.getString("album_cover"));
                albumResponseList.add(albumResponse);
            }
            if (!albumResponseList.isEmpty()) {
                searchResponse.setAlbums(albumResponseList);
            }
            albumResult.close();

            //Track search
            String trackQuery = "SELECT * FROM tracks WHERE track_name LIKE '%"+searchText+"%'";
            PreparedStatement trackStatement = connection.prepareStatement(trackQuery);
            ResultSet trackResult = trackStatement.executeQuery();
            List<TrackSearchResponse> trackSearchResponses = new ArrayList<>();
            while (trackResult.next()) {
                TrackSearchResponse trackSearchResponse = new TrackSearchResponse();
                trackSearchResponse.setTrackName(trackResult.getString("track_name"));
                trackSearchResponse.setTrackSource(trackResult.getString("track_source"));
                trackSearchResponse.setTrackId(trackResult.getInt("track_id"));
                Integer album = trackResult.getInt("album_id");
                String trackAlbumQuery = "SELECT * FROM artists WHERE artist_id in (SELECT artist_id FROM albums WHERE album_id = "+album+")";
                PreparedStatement trackArtistStatement = connection.prepareStatement(trackAlbumQuery);
                ResultSet trackArtistResult = trackArtistStatement.executeQuery();
                if (trackArtistResult.next()) {
                    trackSearchResponse.setArtistName(trackArtistResult.getString("artist_name"));
                }
                trackSearchResponses.add(trackSearchResponse);
                trackArtistResult.close();
            }
            if (!trackSearchResponses.isEmpty()) {
                searchResponse.setTracks(trackSearchResponses);
            }
            trackResult.close();

            //User search
            String userQuery = "SELECT * FROM user WHERE user_name LIKE '%"+searchText+"%'";
            PreparedStatement userStatement = connection.prepareStatement(userQuery);
            ResultSet userResult = userStatement.executeQuery();
            List<UserSearchResponse> userSearchResponseList = new ArrayList<>();
            while (userResult.next()) {
                UserSearchResponse userSearchResponse = new UserSearchResponse();
                userSearchResponse.setUserId(userResult.getString("user_id"));
                userSearchResponse.setUsername(userResult.getString("user_name"));
                userSearchResponse.setUserImage(Base64.encodeBase64String(userResult.getBytes("profile_img")));
                userSearchResponseList.add(userSearchResponse);
            }
            if (!userSearchResponseList.isEmpty()) {
                searchResponse.setUsers(userSearchResponseList);
            }
            userResult.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                mySQLConnection.closeConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return searchResponse;
    }
}
