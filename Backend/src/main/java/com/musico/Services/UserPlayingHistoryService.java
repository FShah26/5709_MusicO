//Author: Fenil Shah
// Created on: 14th July,2021

package com.musico.Services;

import com.musico.Models.PlayingHistoryDao;
import com.musico.Models.TrackHistoryDetails;
import com.musico.Requests.AddToHistoryRequest;
import com.musico.Requests.UserPlayingHistoryRequest;
import com.musico.Responses.AddToHistoryResponse;
import com.musico.Responses.UserPlayingHistoryResponse;

import java.util.Collections;
import java.util.List;

public class UserPlayingHistoryService {

    PlayingHistoryDao historyDao = new PlayingHistoryDao();

    public boolean validateGetPlayingHistoryRequest(String userId) {
        boolean status;
        try {
//            status = historyDao.getPlayingHistoryRequestParams(userPlayingHistoryRequest);
            status = (userId != null && userId != "");
        } catch (Exception ex) {
            System.out.println(ex);
            ex.printStackTrace();
            status = false;
        }
        return status;
    }

    public boolean validateAddToHistoryRequest(AddToHistoryRequest addToHistoryRequest) {
        boolean status;
        try {
            status = historyDao.getAddToHistoryRequestParams(addToHistoryRequest);
        } catch (Exception ex) {
            System.out.println(ex);
            ex.printStackTrace();
            status = false;
        }
        return status;
    }

    public UserPlayingHistoryResponse getPlayingHistory(String userId) {
        UserPlayingHistoryResponse response = new UserPlayingHistoryResponse();
        historyDao.setUser_id(userId);
        try {
            System.out.println("Servicing the request...");
            if (validateGetPlayingHistoryRequest(userId)) {
                List<TrackHistoryDetails> history = historyDao.getPlayingHistory();
                if (history != null) {
                    response.setData(history);
                    response.setMessage("Success");
                    response.setStatusCode(200);
                } else if (history != null && history.isEmpty() ) {
                    response.setData(Collections.emptyList());
                    response.setMessage("Success");
                    response.setStatusCode(200);
                } else {
                    System.out.println("Error from Database");
                    response.setData(Collections.emptyList());
                    response.setMessage("Internal server error");
                    response.setStatusCode(500);
                }
            } else {
                response.setData(Collections.emptyList());
                response.setMessage("Bad request");
                response.setStatusCode(400);
            }
        } catch (Exception ex) {
            System.out.println(ex);
            ex.printStackTrace();
            response.setData(Collections.emptyList());
            response.setMessage("Internal server error");
            response.setStatusCode(500);
        }
        return response;
    }

    public AddToHistoryResponse AddTrackToHistory(AddToHistoryRequest addToHistoryRequest) {
        AddToHistoryResponse response = new AddToHistoryResponse();
        boolean status = false;
        try {
//            System.out.println("Servicing the request...");
            if (validateAddToHistoryRequest(addToHistoryRequest)) {
                status = historyDao.addToHistory();
                if (status) {
                    response.setMessage("Success");
                    response.setStatusCode(200);
                } else {
                    response.setMessage("Failed");
                    response.setStatusCode(200);
                }
            } else {
                response.setMessage("Bad request");
                response.setStatusCode(400);
            }
        } catch (Exception ex) {
            System.out.println(ex);
            ex.printStackTrace();
            response.setMessage("Internal server error");
            response.setStatusCode(500);
        }
        return response;
    }


}
