//Author: Simar Saggu
// Created on: 16th July,2021
package com.musico.Controllers;

import com.musico.Requests.UpdateRequest;
import com.musico.Responses.Response;
import com.musico.Responses.ViewProfileResponse;
import com.musico.Services.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@CrossOrigin(origins = "https://group1-musico.herokuapp.com/", maxAge = 3600)
@Controller
public class ProfileController {

    @GetMapping(path = "/profile/view/{userId}")
    public ResponseEntity<ViewProfileResponse> viewUserProfile(@PathVariable("userId") String userId ) throws SQLException {
        ProfileService profileService = new ProfileService();
        return ResponseEntity.ok(profileService.getUser(userId));
    }

    @PutMapping(path = "/profile/edit",produces = "application/json", consumes = "application/json" )
    public ResponseEntity<Response> editUserProfile(@RequestBody UpdateRequest updateRequest) throws SQLException {
        ProfileService profileService = new ProfileService();
        return ResponseEntity.ok(profileService.validateUserUpdate(updateRequest));
    }

    @DeleteMapping(path = "/profile/delete/{userId}")
    public ResponseEntity<Response> deleteUserProfile(@PathVariable("userId") String userId ) throws SQLException {
        ProfileService profileService = new ProfileService();
        return ResponseEntity.ok(profileService.deleteUser(userId));
    }




}
