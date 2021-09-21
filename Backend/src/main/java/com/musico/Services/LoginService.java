//Author: Simar Saggu
// Created on: 16th July,2021
package com.musico.Services;

import com.musico.Models.UserLoginDao;
import com.musico.Requests.LoginRequest;
import com.musico.Responses.Response;
import com.musico.utils.EmailNotification;

import java.sql.SQLException;
import java.util.Map;
import java.util.regex.Pattern;

public class LoginService {
    UserLoginDao userLoginDao = new UserLoginDao();

    public Map<String,String> fetchUser(LoginRequest loginRequest) throws SQLException {
        Map<String, String> status;
        String registeredName = loginRequest.getRegisteredName();
        userLoginDao.convertLoginRequest(loginRequest);
        if (Pattern.matches("^[0-9]+", registeredName)){
            status = userLoginDao.getUserNumber();
        }
        else{
            status = userLoginDao.getUserEmail();
        }

        return status;
    }

    public Response loginValidate(LoginRequest loginRequest) throws SQLException {

        Map<String, String> status = fetchUser(loginRequest);
        Response loginResponse = new Response();
        String password = loginRequest.getPassword();
        if(status.get("status").equals("true")){
            if(status.get("password").equals(password)) {
                loginResponse.setStatusCode(200);
                loginResponse.setUserId(status.get("userId"));
                loginResponse.setMessage("valid");
            }
            else{
                loginResponse.setStatusCode(401);
                loginResponse.setMessage("invalid");
            }
        }
        else{
            loginResponse.setStatusCode(404);
            loginResponse.setMessage("invalid");
        }
        return loginResponse;
    }

    public Response recoverPassword(String emailId) throws SQLException {

        Map<String, String> status = userLoginDao.getUser(emailId);
        Response loginResponse = new Response();
        EmailNotification emailNotification = new EmailNotification();
        if(status.get("status").equals("true")){
            String password = status.get("password");
            Boolean sent = emailNotification.sendForgetPasswordEmail(emailId,password);
            System.out.println(sent);
            if(sent) {
                loginResponse.setStatusCode(200);
                loginResponse.setMessage("sent");
            }
            else {
                loginResponse.setStatusCode(500);
                loginResponse.setMessage("internal_server_error");
            }
        }
        else{
            loginResponse.setStatusCode(404);
            loginResponse.setMessage("user_not_found");
        }

        return loginResponse;
    }

}
