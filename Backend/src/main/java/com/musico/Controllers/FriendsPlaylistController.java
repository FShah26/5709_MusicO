//Author: Utkarsh Patel
// Created on: 20th July,2021
package com.musico.Controllers;


import com.musico.Models.FriendsPlaylistDao;
import com.musico.Models.PlaylistSongDao;
import com.musico.Requests.FriendsPlaylistRequest;
import com.musico.Requests.FriendsPlaylistSongsRequest;
import com.musico.Requests.Playlist;
import com.musico.Requests.PlaylistSongs;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FriendsPlaylistController {

    @CrossOrigin(origins = "*")
    @GetMapping("/friendsplaylist")
    public HashMap<String, List<FriendsPlaylistSongsRequest>> getAllPlaylist(@RequestParam(name="friend_id") String friend_id) throws SQLException {

        FriendsPlaylistDao friendsPlaylistDao = new FriendsPlaylistDao();
        return friendsPlaylistDao.getPlaylists(friend_id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/importplaylist")
    public String importPlaylist(@RequestParam(name="user_id") String user_id, @RequestParam(name="friend_id") String friend_id, @RequestParam(name="playlist_id") String playlist_id) throws SQLException {
        FriendsPlaylistDao friendsPlaylistDao = new FriendsPlaylistDao();
        return friendsPlaylistDao.importPlaylist(user_id,friend_id,playlist_id);
    }
}
