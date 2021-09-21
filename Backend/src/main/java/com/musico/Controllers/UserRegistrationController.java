//Author: Simar Saggu
// Created on: 14th July,2021
package com.musico.Controllers;

import com.musico.Requests.RegistrationRequest;
import com.musico.Responses.Response;
import com.musico.Services.RegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.sql.SQLException;

@CrossOrigin(origins = "https://group1-musico.herokuapp.com/", maxAge = 3600)
@Controller
public class UserRegistrationController {


    @PostMapping(path="/register", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Response> registerUser(@RequestBody RegistrationRequest registrationRequest) throws SQLException {
        RegistrationService registrationService = new RegistrationService();
        return ResponseEntity.ok(registrationService.registerUser(registrationRequest));
    }
}
