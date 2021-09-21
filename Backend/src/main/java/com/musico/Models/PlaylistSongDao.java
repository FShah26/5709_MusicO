//Author: Anuj Upadhyay
// Created on: 16th July,2021
package com.musico.Models;

import com.musico.Requests.Playlist;
import com.musico.Requests.PlaylistSongs;
import com.musico.utils.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PlaylistSongDao {

    MySQLConnection mySQLConnection = MySQLConnection.getInstance();

    public List<PlaylistSongs> getAllSong(String user_id, String playlist_name) throws SQLException {

        String playlistQuery ="select ROW_NUMBER() OVER() as row_num,p.track_id,t.track_name,(select album_cover from albums where album_id=t.album_id) as album_cover, (select album_name from albums where album_id=t.album_id) as album_name,t.track_source,(select artist_name from artists ar inner join albums al on ar.artist_id = al.artist_id where album_name in(select album_name from albums where album_id=t.album_id)) as artists_name from playlist p inner join tracks t on p.track_id = t.track_id where user_id=? and playlist_name=?";

        String likedQuery ="select ROW_NUMBER() OVER() as row_num,p.track_id,t.track_name,(select album_cover from albums where album_id=t.album_id) as album_cover,(select album_name from albums where album_id=t.album_id) as album_name,t.track_source,(select artist_name from artists ar inner join albums al on ar.artist_id = al.artist_id where album_name in(select album_name from albums where album_id=t.album_id)) as artists_name  from likedsongs p inner join tracks t on p.track_id = t.track_id where user_id=?";

        Connection con = mySQLConnection.getConnection();
        PreparedStatement st;
        if(playlist_name.equals("Liked Songs")){
             st = con.prepareStatement(likedQuery);
            st.setString(1, user_id);
        }
        else {
            st = con.prepareStatement(playlistQuery);
            st.setString(1, user_id);
            st.setString(2, playlist_name);
        }
        ResultSet rs = st.executeQuery();

        List<PlaylistSongs> song= new ArrayList<>();
           while(rs.next()){
               PlaylistSongs playlistSongs = new PlaylistSongs();
               playlistSongs.setSong_id(rs.getInt("track_id"));
               playlistSongs.setSongs(rs.getString("track_name"));
               playlistSongs.setAlbum(rs.getString("album_name"));
               playlistSongs.setRow_num(rs.getInt("row_num"));
               playlistSongs.setMp3(rs.getString("track_source"));
               playlistSongs.setArtist(rs.getString("artists_name"));
               playlistSongs.setAlbum_cover(rs.getString("album_cover"));

               song.add(playlistSongs);
           }
        st.close();
        con.close();

        return song;
    }

    public String delSong(String user_id,String playlist_name,int song_id) throws SQLException {

        String queryPlaylist ="delete from playlist where user_id =? and playlist_name=? and track_id=?";

        String queryLiked = "delete from likedsongs where user_id=? and track_id=?";

        Connection con = mySQLConnection.getConnection();
        PreparedStatement st;
        if(playlist_name.equals("Liked Songs")){
            st = con.prepareStatement(queryLiked);
            st.setString(1,user_id);
            st.setInt(2,song_id);
        }
        else{
            st = con.prepareStatement(queryPlaylist);
            st.setString(1,user_id);
            st.setString(2,playlist_name);
            st.setInt(3,song_id);
        }
        st.executeUpdate();

        String message ="Song removed successfully from playlist.";

        st.close();
        con.close();

        return message;

    }

    public String likeSong(String user_id,int song_id) throws SQLException {

        String query ="Insert into likedsongs(user_id,track_id) VALUES(?,?)";

        Connection con = mySQLConnection.getConnection();
        PreparedStatement st = con.prepareStatement(query);
        st.setString(1,user_id);
        st.setInt(2,song_id);
        st.executeUpdate();

        String message ="Liked the song.";

        st.close();
        con.close();

        return message;
    }


    public String delPlaylist(String user_id,String playlist_name) throws SQLException {

        String query ="delete from playlist where user_id =? and playlist_name=?";

        Connection con = mySQLConnection.getConnection();
        PreparedStatement st = con.prepareStatement(query);
        st.setString(1,user_id);
        st.setString(2,playlist_name);
        st.executeUpdate();

        String message ="Playlist Removed Successfully.";

        st.close();
        con.close();

        return message;

    }

    public List<Playlist> getPlaylists(String user_id) throws SQLException {

        String query ="select distinct playlist_id, playlist_name from playlist where user_id=?";

        Connection con = mySQLConnection.getConnection();
        PreparedStatement st = con.prepareStatement(query);
        st.setString(1,user_id);
        ResultSet rs = st.executeQuery();

        System.out.println(rs);

        List<Playlist> playlists= new ArrayList<>();
        while(rs.next()){
            Playlist playlist = new Playlist();
            playlist.setPlaylist_id(rs.getInt("playlist_id"));
            playlist.setPlaylist_name(rs.getString("playlist_name"));
            playlists.add(playlist);
        }
        st.close();
        con.close();

        return playlists;

    }

    public String addPlaylist(String user_id,String playlist_name) throws SQLException {

        String query ="Insert into playlist(user_id,playlist_id,playlist_name,track_id) value(?,(select * from (select IFNULL(max(playlist_id),0)+1 from playlist where user_id=?) temp),?,null);";

        Connection con = mySQLConnection.getConnection();
        PreparedStatement st = con.prepareStatement(query);
        st.setString(1,user_id);
        st.setString(2,user_id);
        st.setString(3,playlist_name);
        st.executeUpdate();

        String message ="Successfully added new playlist!!";

        st.close();
        con.close();

        return message;
    }

    public String addSong(String user_id,int playlist_id,String playlist_name,int song_id) throws SQLException {

        String query ="Insert into playlist(user_id,playlist_id,playlist_name,track_id)values(?,?,?,?);";
        Connection con = mySQLConnection.getConnection();
        PreparedStatement st = con.prepareStatement(query);
        st.setString(1,user_id);
        st.setInt(2,playlist_id);
        st.setString(3,playlist_name);
        st.setInt(4,song_id);
        st.executeUpdate();

        String message ="Successfully added new song to playlist!!";

        st.close();
        con.close();

        return message;
    }
}
