//Author: Simar Saggu
// Created on: 16th July,2020
//Modified on: 17th July,2020
package com.musico.Controllers;

import com.musico.Requests.LoginRequest;
import com.musico.Responses.Response;
import com.musico.Services.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@CrossOrigin(origins = "https://group1-musico.herokuapp.com/", maxAge = 3600)
@Controller
public class UserLoginController {

    @PostMapping(path="/login", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Response> loginUser(@RequestBody LoginRequest loginRequest) throws SQLException {
        LoginService loginService = new LoginService();
        return ResponseEntity.ok(loginService.loginValidate(loginRequest));
    }

    @GetMapping(path = "/forget/password/{emailId}")
    public ResponseEntity<Response> recoverPassword(@PathVariable("emailId") String emailId ) throws SQLException {
        LoginService loginService = new LoginService();
        return ResponseEntity.ok(loginService.recoverPassword(emailId));
    }
}
