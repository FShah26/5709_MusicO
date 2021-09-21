//Author: Neehar Parupalli Ramakrishna
//Created On: 15th July 2021
package com.musico.Controllers;

import com.musico.Responses.ArtistResponse;
import com.musico.Services.ArtistService;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ArtistController {

    @GetMapping(path = "/artist/{id}")
    public ResponseEntity<Object> getArtistDetails(@PathVariable("id") String artistId) {
        JSONObject response = new JSONObject();
        ArtistService artistService = new ArtistService();
        ArtistResponse artistResponse = artistService.getArtistDetails(artistId);
        if (artistResponse == null || artistResponse.getArtistName() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        response.put("message","Success");
        response.put("data",artistResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
