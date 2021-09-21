//Author: Simar Saggu
// Created on: 16th July,2021
package com.musico.Services;

import com.musico.Models.UserProfileDao;
import com.musico.Requests.UpdateRequest;
import com.musico.Responses.Response;
import com.musico.Responses.ViewProfileResponse;
import java.sql.SQLException;
import java.util.Map;
import java.util.regex.Pattern;

public class ProfileService {

    ViewProfileResponse viewProfileResponse = new ViewProfileResponse();

    public ViewProfileResponse getUser(String userId) throws SQLException
    {
        UserProfileDao userProfileDao = new UserProfileDao();
        Map<String,String> result= userProfileDao.fetchUser(userId);
        if (result.get("status").equals("exists")){
            viewProfileResponse.setMessage("exists");
            viewProfileResponse.setStatusCode(200);
            viewProfileResponse.setName(result.get("name"));
            viewProfileResponse.setPhoneNumber(result.get("phoneNumber"));
            viewProfileResponse.setEmail(result.get("email"));
            viewProfileResponse.setCountry(result.get("country"));
            viewProfileResponse.setBio(result.get("bio"));
            viewProfileResponse.setProfileImage(result.get("profileImage"));
            viewProfileResponse.setFollowers(Integer.parseInt(result.get("followers")));
            viewProfileResponse.setFollowing(Integer.parseInt(result.get("following")));
            viewProfileResponse.setPlaylists(Integer.parseInt(result.get("playlists")));
        }
        else{
            viewProfileResponse.setMessage("internal server error");
            viewProfileResponse.setStatusCode(500);
        }
        return viewProfileResponse;
    }
    public Response validateUserUpdate(UpdateRequest updateRequest) throws SQLException {
        Response updateResponse = new Response();
        String name = updateRequest.getName();
        String password = updateRequest.getPassword();
        String confirmPassword = updateRequest.getCpassword();
        UserProfileDao userProfileDao = new UserProfileDao();
        if(name.equals("") && !Pattern.matches("[a-zA-Z]+\\s+[a-zA-Z]+$",name ) ){
            updateResponse.setMessage("name_invalid");
            updateResponse.setStatusCode(422);
            updateResponse.setUserId(updateRequest.getUserId());
        }
        else if (!password.equals(confirmPassword))
        {
            updateResponse.setMessage("pass_not_match");
            updateResponse.setStatusCode(422);
            updateResponse.setUserId(updateRequest.getUserId());
        }

        else{
            Boolean status = userProfileDao.updateUser(updateRequest);
            if (status){
                updateResponse.setMessage("Updated");
                updateResponse.setStatusCode(200);
                updateResponse.setUserId(updateRequest.getUserId());
            }
            else{
                updateResponse.setMessage("Failed");
                updateResponse.setStatusCode(500);
                updateResponse.setUserId(updateRequest.getUserId());
            }
        }
        return  updateResponse;
    }

    public Response deleteUser(String userId) throws SQLException {
        UserProfileDao userProfileDao = new UserProfileDao();
        Response deleteResponse = new Response();
        Map<String,String> result= userProfileDao.fetchUser(userId);
        if (result.get("status").equals("exists")){
            Boolean deleteCheck = userProfileDao.delete(userId);
            if(deleteCheck)
            {
                deleteResponse.setStatusCode(200);
                deleteResponse.setMessage("deleted");
            }
            else{
                deleteResponse.setStatusCode(500);
                deleteResponse.setMessage("internal_server_error");
            }
        }
        else{
            deleteResponse.setStatusCode(404);
            deleteResponse.setMessage("not_exists");
        }
        return deleteResponse;
    }
}
