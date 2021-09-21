//Author: Fenil Shah
// Created on: 16th July,2021

package com.musico.Models;


import com.musico.Requests.AddToHistoryRequest;
import com.musico.Requests.UserPlayingHistoryRequest;
import com.musico.utils.MySQLConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class PlayingHistoryDao {

    MySQLConnection mySQLConnection = MySQLConnection.getInstance();

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    private String user_id;
    private Integer track_id;

    public Boolean getAddToHistoryRequestParams(AddToHistoryRequest addToHistoryRequest)
    {
        try{
            this.user_id = addToHistoryRequest.getUserId();
            this.track_id = Integer.parseInt(addToHistoryRequest.getTrackId());
            return true;
        }
        catch(Exception ex)
        {
            System.out.println(ex);
            ex.printStackTrace();
            return false;
        }
    }

    public Boolean getPlayingHistoryRequestParams(UserPlayingHistoryRequest userPlayingHistoryRequest)
    {
        try{
            this.user_id = userPlayingHistoryRequest.getUserId();
            return true;
        }
        catch(Exception ex)
        {
            System.out.println(ex);
            ex.printStackTrace();
            return false;
        }
    }

    public Boolean addToHistory() throws SQLException
    {
        boolean status = false;
        UUID uuid = UUID.randomUUID();
        int seq_no=1;
        String newHistId = "hist_"+uuid.toString().replace("-","");
        Connection connection;
        try{
            connection = mySQLConnection.getConnection();
            String selectQuery ="SELECT seq_no FROM playingHistory WHERE user_id='"+this.user_id+"' order by seq_no desc";
            Statement statement =  connection.createStatement();
            ResultSet resultSet= statement.executeQuery(selectQuery);

            if(resultSet.next()) {
                seq_no = resultSet.getInt("seq_no");
                seq_no += 1;
            }

            String checkQuery ="SELECT * FROM playingHistory where user_id=? and track_id=?";
            PreparedStatement st = connection.prepareStatement(checkQuery);
            st.setString(1,this.user_id);
            st.setInt(2,this.track_id);
            ResultSet rs = st.executeQuery();
            if(rs.next())
            {
                String insertQuery ="UPDATE playingHistory SET seq_no=? where user_id=? and track_id=?";
                PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
                insertStatement.setInt(1,seq_no);
                insertStatement.setString(2,this.user_id);
                insertStatement.setInt(3,this.track_id);
                insertStatement.executeUpdate();
            }
            else
            {
                String insertQuery ="Insert into playingHistory(history_id,user_id,track_id,seq_no) values(?,?,?,?);";
                PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
                insertStatement.setString(1,newHistId);
                insertStatement.setString(2,this.user_id);
                insertStatement.setInt(3,this.track_id);
                insertStatement.setInt(4,seq_no);
                insertStatement.executeUpdate();
            }
            status = true;
        }
        catch(SQLException ex){
            System.out.println(ex);
            ex.printStackTrace();
            status = false;
        }
        finally{
            mySQLConnection.closeConnection();
            return status;
        }

    }

    public List<TrackHistoryDetails> getPlayingHistory() throws SQLException
    {
        Connection connection;
        ArrayList<TrackHistoryDetails> history = new ArrayList<>();
        try{
            connection = mySQLConnection.getConnection();
            String selectQuery ="SELECT * FROM vw_UserPlayingHistory WHERE user_id='"+this.user_id+"';";
            Statement statement =  connection.createStatement();
            ResultSet resultSet= statement.executeQuery(selectQuery);
            System.out.println("Data retrieved:" );

            if(resultSet.next()) {
                do{
                    TrackHistoryDetails trackDetails = new TrackHistoryDetails();
                    trackDetails.setHistory_id(resultSet.getString("history_id"));
                    trackDetails.setTrack_id(resultSet.getInt("track_id"));
                    trackDetails.setUser_id(resultSet.getString("user_id"));
                    trackDetails.setSeq_no(resultSet.getInt("seq_no"));
                    trackDetails.setTrack_name(resultSet.getString("track_name"));
                    trackDetails.setTrack_source(resultSet.getString("track_source"));
                    trackDetails.setArtist_id(resultSet.getInt("artist_id"));
                    trackDetails.setArtist_name(resultSet.getString("artist_name"));
                    trackDetails.setAlbum_id(resultSet.getInt("album_id"));
                    trackDetails.setAlbum_name(resultSet.getString("album_name"));
                    int liked = 0;
                    liked = resultSet.getInt("isLiked");
                    trackDetails.setIsLiked(!resultSet.wasNull());
//                    System.out.println(trackDetails.getIsLiked());
                    history.add(trackDetails);
//                    System.out.println("Adding record:" + resultSet.getString("history_id"));
                } while (resultSet.next());
            }
        }
        catch(SQLException ex){
            System.out.println(ex);
            ex.printStackTrace();
            history = null;
        }
        finally{
            mySQLConnection.closeConnection();
            return history;
        }

    }

}
