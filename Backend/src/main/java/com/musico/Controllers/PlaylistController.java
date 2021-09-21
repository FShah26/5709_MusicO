//Author: Anuj Upadhyay
// Created on: 16th July,2021
package com.musico.Controllers;
import com.musico.Models.PlaylistSongDao;
import com.musico.Requests.Playlist;
import com.musico.Requests.PlaylistSongs;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PlaylistController {


    @CrossOrigin(origins = "*")
    @GetMapping("/playlist")
    public List<Playlist> getAllPlaylist(@RequestParam(name="user_id") String user_id) throws SQLException {

        PlaylistSongDao playlistNameDao = new PlaylistSongDao();
        return playlistNameDao.getPlaylists(user_id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/playlistsongs")
    public List<PlaylistSongs> getPlaylistSongs(@RequestParam(name="user_id") String user_id, @RequestParam(name="playlist_name") String playlist_name) throws SQLException {
        PlaylistSongDao playlistSongDao = new PlaylistSongDao();
        return playlistSongDao.getAllSong(user_id,playlist_name);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/deletesongs")
    public String delPlaylistSong(@RequestParam(name="user_id") String user_id, @RequestParam(name="playlist_name") String playlist_name, @RequestParam(name="song_id") int song_id) throws SQLException {
        PlaylistSongDao deleteDao = new PlaylistSongDao();
        return deleteDao.delSong(user_id,playlist_name,song_id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/addplaylist")
    public String addNewPlaylist(@RequestParam(name="user_id") String user_id, @RequestParam(name="playlist_name") String playlist_name) throws SQLException {
        PlaylistSongDao addNewDao = new PlaylistSongDao() ;
        return addNewDao.addPlaylist(user_id,playlist_name);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/addsong")
    public String addNewSong(@RequestParam(name="user_id") String user_id,@RequestParam(name="playlist_id") int playlist_id, @RequestParam(name="playlist_name") String playlist_name,@RequestParam(name="song_id") int song_id) throws SQLException {
        PlaylistSongDao addNewDao = new PlaylistSongDao() ;
        return addNewDao.addSong(user_id,playlist_id,playlist_name,song_id);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/likesong")
    public String likeSong(@RequestParam(name="user_id") String user_id, @RequestParam(name="track_id") Integer track_id) throws SQLException {
        PlaylistSongDao addNewPlaylistDao = new PlaylistSongDao() ;
        return addNewPlaylistDao.likeSong(user_id,track_id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/deleteplaylist")
    public String deletePlaylist(@RequestParam(name="user_id") String user_id, @RequestParam(name="playlist_name") String playlist_name) throws SQLException {
        PlaylistSongDao addNewPlaylistDao = new PlaylistSongDao() ;
        return addNewPlaylistDao.delPlaylist(user_id,playlist_name);
    }
}
