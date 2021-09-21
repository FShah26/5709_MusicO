//Author: Fenil Shah
// Created on: 16th July,2021
package com.musico.Controllers;

import com.musico.Requests.AddToHistoryRequest;
import com.musico.Requests.UserPlayingHistoryRequest;
import com.musico.Responses.AddToHistoryResponse;
import com.musico.Responses.UserPlayingHistoryResponse;
import com.musico.Services.UserPlayingHistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(origins = "http://localhost:3000/", maxAge = 3600) //"https://group1-musico.herokuapp.com/"
@Controller
public class UserPlayingHistoryController {

    @PostMapping(path="/addToHistory")
    public ResponseEntity<Object> addToHistory(@RequestBody AddToHistoryRequest addToHistoryRequest){
        UserPlayingHistoryService userPlayingHistoryService = new UserPlayingHistoryService();
        AddToHistoryResponse response =userPlayingHistoryService.AddTrackToHistory(addToHistoryRequest);
        if (response.getStatusCode() == 200) {
            return ResponseEntity.ok(response);
        } else if (response.getStatusCode() == 400) {
            return new ResponseEntity<>(response.getMessage(), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(response.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(path = "/getPlayingHistory")
    public ResponseEntity<Object> getUserPlayingHistory(@RequestParam String userId) {
        UserPlayingHistoryService userPlayingHistoryService = new UserPlayingHistoryService();
        UserPlayingHistoryResponse response = userPlayingHistoryService.getPlayingHistory(userId);
//        return ResponseEntity.ok(userPlayingHistoryService.getPlayingHistory(userPlayingHistoryRequest));
        if(response.getStatusCode() == 200)
        {
            return ResponseEntity.ok(response);
        }
        else if(response.getStatusCode() == 400)
        {
            return new ResponseEntity<>(response.getMessage(), HttpStatus.BAD_REQUEST);
        }
        else
        {
            return new ResponseEntity<>(response.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
