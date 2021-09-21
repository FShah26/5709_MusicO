//Author: Utkarsh Patel
// Created on: 20th July,2021
package com.musico.Models;

import com.musico.Requests.FriendsPlaylistRequest;
import com.musico.Requests.FriendsPlaylistSongsRequest;
import com.musico.Requests.Playlist;
import com.musico.utils.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FriendsPlaylistDao {
    MySQLConnection mySQLConnection = MySQLConnection.getInstance();

    public HashMap<String,List<FriendsPlaylistSongsRequest>> getPlaylists(String friend_id) throws SQLException {

        String query = "select distinct playlist_id, playlist_name from playlist where user_id=?";

        Connection con = mySQLConnection.getConnection();
        PreparedStatement st = con.prepareStatement(query);
        st.setString(1, friend_id);
        ResultSet rs = st.executeQuery();

        System.out.println(rs);

        HashMap<String,List<FriendsPlaylistSongsRequest>> playlists= new HashMap<>();
        List<FriendsPlaylistSongsRequest> songs;
        while (rs.next()) {

            String playlistQuery = "select ROW_NUMBER() OVER() as row_num,p.track_id,t.track_name,(select album_name from albums where album_id=t.album_id) as album_name  from playlist p inner join tracks t on p.track_id = t.track_id where user_id=? and playlist_name=?";
            Connection connection = mySQLConnection.getConnection();
            PreparedStatement statement;
            statement = connection.prepareStatement(playlistQuery);
            statement.setString(1, friend_id);
            statement.setString(2, rs.getString("playlist_name"));

            ResultSet resultSet = statement.executeQuery();
            songs = new ArrayList<>();

            if(resultSet.first()) {
                resultSet.beforeFirst();
                while (resultSet.next()) {
                    FriendsPlaylistSongsRequest friendsPlaylistSongsRequest = new FriendsPlaylistSongsRequest();
                    friendsPlaylistSongsRequest.setSong_id(resultSet.getInt("track_id"));
                    friendsPlaylistSongsRequest.setSongs(resultSet.getString("track_name"));
                    friendsPlaylistSongsRequest.setAlbum(resultSet.getString("album_name"));
                    friendsPlaylistSongsRequest.setRow_num(resultSet.getInt("row_num"));
                    friendsPlaylistSongsRequest.setPlaylist_id(rs.getInt("playlist_id"));
                    songs.add(friendsPlaylistSongsRequest);
                }
            }
            else{
                FriendsPlaylistSongsRequest friendsPlaylistSongsRequest = new FriendsPlaylistSongsRequest();
                friendsPlaylistSongsRequest.setPlaylist_id(rs.getInt("playlist_id"));
                songs.add(friendsPlaylistSongsRequest);
            }
            playlists.put(rs.getString("playlist_name"),songs);
            statement.close();
            connection.close();
        }
        st.close();
        con.close();

        return playlists;
    }
    public String importPlaylist(String user_id,String friend_id, String playlist_id) throws SQLException{

     String sql = "select * from playlist where user_id=? and playlist_id=?";

     Connection con = mySQLConnection.getConnection();
     Connection con2 = mySQLConnection.getConnection();
     Connection con3 = mySQLConnection.getConnection();
     Connection con4 = mySQLConnection.getConnection();

     String message;
     String sql4 = "select distinct playlist_name from playlist where user_id = ? and playlist_name = (select  distinct playlist_name from playlist where user_id = ? and playlist_id = ?)";
     PreparedStatement st4 = con.prepareStatement(sql4);
        st4.setString(1, user_id);
        st4.setString(2,friend_id);
        st4.setString(3,playlist_id);
        ResultSet rs4 = st4.executeQuery();
     int exist = 0;
     while(rs4.next()){
         exist++;
     }

     if(exist > 0){
         message ="Cannot import playlist already exist!!";

         con.close();
         con2.close();
         con3.close();
         con4.close();
     }
        else
     {
         PreparedStatement st = con.prepareStatement(sql);
         st.setString(1, friend_id);
         st.setString(2,playlist_id);
         ResultSet rs = st.executeQuery();
         String sql2 = "select MAX(playlist_id) from playlist where user_id=?";
         PreparedStatement st2 = con2.prepareStatement(sql2);
         st2.setString(1,user_id);
         ResultSet rs2 = st2.executeQuery();
         int max = 0;
         while(rs2.next()){
             max = rs2.getInt(1);
         }
         max = max + 1;
         while(rs.next()){
             String sql3 = "insert into playlist(user_id,playlist_id,playlist_name,track_id) values (?,?,?,?)";
             PreparedStatement st3 = con3.prepareStatement(sql3);
             st3.setString(1,user_id);
             st3.setInt(2,max);
             st3.setString(3,rs.getString("playlist_name"));
             st3.setString(4,rs.getString("track_id"));
             st3.executeUpdate();
         }
         message ="Successfully imported Playlist!!";

         st.close();
         st2.close();
         con.close();
         con2.close();
         con3.close();
         con4.close();
     }
        return message;
    }
}