//Author: Simar Saggu
// Created on: 14th July,2021
package com.musico.Services;

import com.musico.Models.UserRegistrationDao;
import com.musico.Requests.RegistrationRequest;
import com.musico.Responses.Response;
import java.sql.SQLException;
import java.util.Map;

public class RegistrationService {
    UserRegistrationDao userDao = new UserRegistrationDao();
    Response registrationResponse = new Response();


    public Boolean validateUser(RegistrationRequest registrationRequest) throws SQLException {
        userDao.convertRegistrationRequest(registrationRequest);
        Boolean userExists= userDao.getUser();
        return userExists;
    }

    public Response registerUser(RegistrationRequest registrationRequest) throws SQLException {
        Boolean userExists = validateUser(registrationRequest);
        if (userExists){
            registrationResponse.setUserId("");
            registrationResponse.setMessage("Failed");
            registrationResponse.setStatusCode(403);
        }
        else{
            Map<String,String> status = userDao.saveUser();
            if (status.get("status").equals("true")){
                registrationResponse.setUserId(status.get("userId"));
                registrationResponse.setMessage("Success");
                registrationResponse.setStatusCode(200);
            }
            if (status.get("status").equals("false")){
                registrationResponse.setUserId("");
                registrationResponse.setMessage("Failed");
                registrationResponse.setStatusCode(500);
            }
        }

        return registrationResponse;
    }
}
