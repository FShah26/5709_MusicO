//Author: Utkarsh Patel
// Created on: 17th July,2021
package com.musico.Controllers;

import com.musico.Models.FriendsPageDao;
import com.musico.Models.PlaylistSongDao;
import com.musico.Requests.AddFriendRequest;
import com.musico.Requests.FriendsPageRequest;
import com.musico.Requests.Playlist;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FriendsController {

    @CrossOrigin(origins = "*")
    @GetMapping(path="/friendspage")
    public List<FriendsPageRequest> getAllFriends(@RequestParam(name="user_id") String user_id) throws SQLException {

        FriendsPageDao friendsPageDao = new FriendsPageDao();
        return friendsPageDao.getFriends(user_id);
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/removeFriend")
    public String removeFriend(@RequestParam(name="user_id") String user_id, @RequestParam(name="friend_id") String friend_id) throws SQLException {
        FriendsPageDao friendsPageDao = new FriendsPageDao() ;
        return friendsPageDao.removeFriend(user_id,friend_id);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/addFriend")
    public boolean addFriend(@RequestBody AddFriendRequest addFriendRequest) throws SQLException {
        FriendsPageDao friendsPageDao = new FriendsPageDao() ;
        return friendsPageDao.addFriend(addFriendRequest.getUserId(),addFriendRequest.getFriendId());
    }
}
