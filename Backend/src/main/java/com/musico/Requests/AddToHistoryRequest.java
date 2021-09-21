//Author: Fenil Shah
// Created on: 16th July,2021
package com.musico.Requests;

public class AddToHistoryRequest {

    private String userId;
    private String trackId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }
}
