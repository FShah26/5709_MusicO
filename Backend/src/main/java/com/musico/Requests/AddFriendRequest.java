//Author: Fenil Shah
// Created on: 27th July,2021
package com.musico.Requests;
public class AddFriendRequest {
    private String userId;
    private String friendId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }
}
