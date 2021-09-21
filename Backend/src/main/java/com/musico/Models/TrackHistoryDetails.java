//Author: Fenil Shah
// Created on: 16th July,2020
package com.musico.Models;

public class TrackHistoryDetails {
    private String user_id;
    private String history_id;
    private Integer seq_no;
    private String track_name;
    private String track_source;
    private Integer album_id;
    private String album_name;
    private Integer artist_id;
    private String artist_name;
    private boolean isLiked;

    public boolean getIsLiked() {
        return isLiked;
    }

    public void setIsLiked(boolean liked) {
        isLiked = liked;
    }



    public Integer getTrack_id() {
        return track_id;
    }

    public void setTrack_id(Integer track_id) {
        this.track_id = track_id;
    }

    private Integer track_id;

    public String getUser_id() {
        return user_id;
    }

    public String getHistory_id() {
        return history_id;
    }

    public Integer getSeq_no() {
        return seq_no;
    }

    public String getTrack_name() {
        return track_name;
    }

    public String getTrack_source() {
        return track_source;
    }

    public Integer getAlbum_id() {
        return album_id;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public Integer getArtist_id() {
        return artist_id;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setHistory_id(String history_id) {
        this.history_id = history_id;
    }

    public void setSeq_no(Integer seq_no) {
        this.seq_no = seq_no;
    }

    public void setTrack_name(String track_name) {
        this.track_name = track_name;
    }

    public void setTrack_source(String track_source) {
        this.track_source = track_source;
    }

    public void setAlbum_id(Integer album_id) {
        this.album_id = album_id;
    }

    public void setAlbum_name(String album_name) {
        this.album_name = album_name;
    }

    public void setArtist_id(Integer artist_id) {
        this.artist_id = artist_id;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }
}
