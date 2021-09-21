//Author: Fenil Shah
// Created on: 16th July,2021
package com.musico.Responses;

import com.musico.Models.TrackHistoryDetails;

import java.util.HashMap;
import java.util.List;

public class UserPlayingHistoryResponse {
    private List<TrackHistoryDetails> data;
    private String message;
    private Integer statusCode;

    public List<TrackHistoryDetails> getData() {
        return data;
    }

    public void setData(List<TrackHistoryDetails> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
}
