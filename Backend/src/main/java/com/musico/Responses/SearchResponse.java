//Author: Neehar Parupalli Ramakrishna
//Created On: 15th July 2021
package com.musico.Responses;

import java.util.List;

public class SearchResponse {

    private List<ArtistResponse> artists;

    private List<AlbumResponse> albums;

    private List<TrackSearchResponse> tracks;

    private List<UserSearchResponse> users;

    public List<ArtistResponse> getArtists() {
        return artists;
    }

    public void setArtists(List<ArtistResponse> artists) {
        this.artists = artists;
    }

    public List<AlbumResponse> getAlbums() {
        return albums;
    }

    public void setAlbums(List<AlbumResponse> albums) {
        this.albums = albums;
    }

    public List<TrackSearchResponse> getTracks() {
        return tracks;
    }

    public void setTracks(List<TrackSearchResponse> tracks) {
        this.tracks = tracks;
    }

    public List<UserSearchResponse> getUsers() {
        return users;
    }

    public void setUsers(List<UserSearchResponse> users) {
        this.users = users;
    }
}
