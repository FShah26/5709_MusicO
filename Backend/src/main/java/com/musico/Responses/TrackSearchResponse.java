//Author: Neehar Parupalli Ramakrishna
//Created On: 15th July 2021
package com.musico.Responses;

public class TrackSearchResponse {

    private String artistName;

    private String trackName;

    private String trackSource;

    private Integer trackId;

    public Integer getTrackId() {
        return trackId;
    }

    public void setTrackId(Integer trackId) {
        this.trackId = trackId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getTrackSource() {
        return trackSource;
    }

    public void setTrackSource(String trackSource) {
        this.trackSource = trackSource;
    }
}
