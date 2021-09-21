//Author: Utkarsh Patel
// Created on: 17th July,2021
package com.musico.Requests;

public class FriendsPageRequest {
    String friend_id;
    String friend_name;
    String friend_email;
    String friend_bio;
    String friend_photo;
    String user_id;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getFriend_id() {
        return friend_id;
    }

    public void setFriend_id(String friend_id) {
        this.friend_id = friend_id;
    }

    public String getFriend_name() {
        return friend_name;
    }

    public void setFriend_name(String friend_name) {
        this.friend_name = friend_name;
    }

    public String getFriend_email() {
        return friend_email;
    }

    public void setFriend_email(String friend_email) {
        this.friend_email = friend_email;
    }

    public String getFriend_bio() {
        return friend_bio;
    }

    public void setFriend_bio(String friend_bio) {
        this.friend_bio = friend_bio;
    }

    public String getFriend_photo() {
        return friend_photo;
    }

    public void setFriend_photo(String friend_photo) {
        this.friend_photo = friend_photo;
    }
}
