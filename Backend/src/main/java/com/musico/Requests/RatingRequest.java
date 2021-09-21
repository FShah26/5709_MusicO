//Author:Venkata Kanakayya Prashant Vadlamani
//Created On: 15 July 2021
package com.musico.Requests;

public class RatingRequest {
    private int rating;
    private String album_name;
    private String userid;

    public String getUserid() {
        return userid;
    }

    public void setUser_id(String userid) {
        this.userid = userid;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public void setAlbum_name(String album_name) {
        this.album_name = album_name;
    }

}
