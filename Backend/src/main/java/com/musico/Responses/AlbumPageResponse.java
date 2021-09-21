//Author:Venkata Kanakayya Prashant Vadlamani
//Created On: 15 July 2021
package com.musico.Responses;

import com.musico.Models.Tracks;
import java.util.List;

public class AlbumPageResponse {
    private String artistName;
    private String albumName;
    private String albumImage;

    private List<Tracks> songs;

    public String getArtistName() {
        return artistName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumImage() {
        return albumImage;
    }

    public void setAlbumImage(String albumImage) {
        this.albumImage = albumImage;
    }

    public List<Tracks> getSongs() {
        return songs;
    }

    public void setSongs(List<Tracks> songs) {
        this.songs = songs;
    }
}
