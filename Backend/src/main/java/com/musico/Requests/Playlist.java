//Author: Anuj Upadhyay
// Created on: 16th July,2021
package com.musico.Requests;

public class Playlist {
    int playlist_id;
    String playlist_name;

    public String getPlaylist_name() {
        return playlist_name;
    }

    public void setPlaylist_name(String playlist_name) {
        this.playlist_name = playlist_name;
    }

    public int getPlaylist_id() {
        return playlist_id;
    }

    public void setPlaylist_id(int playlist_id) {
        this.playlist_id = playlist_id;
    }
}
