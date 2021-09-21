//Author: Utkarsh Patel
// Created on: 17th July,2021
package com.musico.Models;

import com.musico.Requests.FriendsPageRequest;
import com.musico.Requests.LoginRequest;
import com.musico.Requests.Playlist;
import com.musico.utils.MySQLConnection;
import org.apache.tomcat.util.codec.binary.Base64;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FriendsPageDao {
    MySQLConnection mySQLConnection = MySQLConnection.getInstance();

    public List<FriendsPageRequest> getFriends(String user_id) throws SQLException{

        String sql = "select * from user where user_id IN (select friend_id from friends where user_id=?)";
        Connection con = mySQLConnection.getConnection();
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1,user_id);
        ResultSet rs = st.executeQuery();

        List<FriendsPageRequest> friendsPageRequests = new ArrayList<>();
        while(rs.next()){
            FriendsPageRequest friendsPageRequest = new FriendsPageRequest();
            friendsPageRequest.setFriend_id(rs.getString("user_id"));
            friendsPageRequest.setFriend_name(rs.getString("user_name"));
            friendsPageRequest.setFriend_email(rs.getString("email_id"));
            friendsPageRequest.setFriend_bio(rs.getString("bio"));
            friendsPageRequest.setFriend_photo(Base64.encodeBase64String(rs.getBytes("profile_img")));
            friendsPageRequests.add(friendsPageRequest);
        }
        st.close();
        con.close();

        return friendsPageRequests;

    }
    public String removeFriend(String user_id,String friend_id) throws SQLException {

        String query ="delete from friends where user_id =? and friend_id=?";
        String query1 = "select followers from user where user_id=?";

        Connection con = mySQLConnection.getConnection();
        PreparedStatement st = con.prepareStatement(query);
        st.setString(1,user_id);
        st.setString(2,friend_id);
        st.executeUpdate();

        PreparedStatement st1 = con.prepareStatement(query1);
        st1.setString(1,friend_id);
        ResultSet rs1 = st1.executeQuery();
        int followers = 0;
        if(rs1.next()) {
            followers = rs1.getInt("followers");
            followers = followers-1;
        }

        String query2 = "update user set followers =? where user_id=?";
        PreparedStatement st2 = con.prepareStatement(query2);
        st2.setInt(1,followers);
        st2.setString(2,friend_id);
        st2.executeUpdate();

        String query3 = "select following from user where user_id=?";
        PreparedStatement st3 = con.prepareStatement(query3);
        st3.setString(1,user_id);
        ResultSet rs3 = st3.executeQuery();
        int following = 0;
        if(rs3.next()) {
            following = rs3.getInt("following");
            following = following-1;
        }
        String query4 = "update user set following =? where user_id=?";
        PreparedStatement st4 = con.prepareStatement(query4);
        st4.setInt(1,following);
        st4.setString(2,user_id);
        st4.executeUpdate();


        String message ="Friend Removed Successfully.";

        st.close();
        con.close();

        return message;

    }

    public Boolean addFriend(String user_id,String friend_id) throws SQLException
    {
        boolean status = false;
        Connection connection = null;
        try{
            connection = mySQLConnection.getConnection();
            connection.setAutoCommit(false);

            String addQuery = "INSERT INTO friends VALUES(?,?);";
            PreparedStatement insertStatement = connection.prepareStatement(addQuery);
            insertStatement.setString(1,user_id);
            insertStatement.setString(2,friend_id);
            insertStatement.executeUpdate();
            System.out.println("Entry added to friends table");

            String updatefollowers = "Update user SET followers = followers + 1 where user_id=?";
            PreparedStatement updatefollowersStmt = connection.prepareStatement(updatefollowers);
            updatefollowersStmt.setString(1,friend_id);
            updatefollowersStmt.executeUpdate();

            System.out.println("Follower increased");

            String updatefollowing = "Update user SET following = following + 1 where user_id=?";
            PreparedStatement updatefollowingStmt = connection.prepareStatement(updatefollowing);
            updatefollowingStmt.setString(1,user_id);
            updatefollowingStmt.executeUpdate();

            System.out.println("Following increased");

            connection.commit();

            System.out.println("AddFriend Transaction Committed");
            status=true;
        }
        catch (SQLException ex)
        {
            System.out.println(ex);
            ex.printStackTrace();
            if(connection != null)
            {
                connection.rollback();
                System.out.println("AddFriend Transaction Rollbacked");
            }
            status = false;
        }
        finally{
            mySQLConnection.closeConnection();
            return status;
        }


    }

}
