//Author:Venkata Kanakayya Prashant Vadlamani
//Created On: 15 July 2021
package com.musico.Controllers;


import com.musico.Requests.RatingRequest;
import com.musico.Responses.AlbumPageResponse;
import com.musico.Services.AlbumService;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AlbumController {
    @GetMapping(path = "/album/{id}")
    public ResponseEntity<Object> getAlbumDetails(@PathVariable("id") String albumId) {
        JSONObject response = new JSONObject();
        AlbumService albumService = new AlbumService();
        AlbumPageResponse albumResponse = albumService.getAlbumDetails(albumId);
        if (albumResponse == null || albumResponse.getArtistName() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        response.put("message","Success");
        response.put("data",albumResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "/album/{id}",produces = "application/json", consumes = "application/json")
    public void getRatingDetails(@RequestBody RatingRequest ratingRequest, @PathVariable String id) {
        int rating = ratingRequest.getRating();
        String album_name = ratingRequest.getAlbum_name();
        String user_name = ratingRequest.getUserid();
        AlbumService albumService = new AlbumService();
        albumService.addRating(rating,album_name,user_name);
    }
}
