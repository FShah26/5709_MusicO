//Author: Fenil Shah
// Created on: 16th July,2021
package com.musico.Responses;

public class AddToHistoryResponse {


    private String message;
    private Integer statusCode;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
