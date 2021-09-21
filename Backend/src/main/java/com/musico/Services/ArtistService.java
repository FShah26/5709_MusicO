//Author: Neehar Parupalli Ramakrishna
//Created On: 15th July 2021
package com.musico.Services;

import com.musico.Models.AlbumDao;
import com.musico.Models.ArtistDao;
import com.musico.Responses.ArtistResponse;

public class ArtistService {

    ArtistDao artistDao = new ArtistDao();
    AlbumDao albumDao = new AlbumDao();

    public ArtistResponse getArtistDetails (String artistId) {
        ArtistResponse artistResponse = null;
        try {
            artistResponse = artistDao.getArtistDetails(artistId);
            artistResponse.setAlbums(albumDao.getAlbumList(artistId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return artistResponse;
    }
}
