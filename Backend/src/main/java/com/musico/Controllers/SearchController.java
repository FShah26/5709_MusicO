//Author: Neehar Parupalli Ramakrishna
//Created On: 15th July 2021
package com.musico.Controllers;

import com.musico.Responses.SearchResponse;
import com.musico.Services.SearchService;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SearchController {

    SearchService searchService = new SearchService();

    @GetMapping("/search/{text}")
    public ResponseEntity<Object> search(@PathVariable("text") String searchText) {
        JSONObject response = new JSONObject();
        SearchResponse searchResponse = searchService.search(searchText);
        if (searchResponse.getArtists() == null && searchResponse.getAlbums() == null && searchResponse.getTracks() == null && searchResponse.getUsers() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        response.put("message","Success");
        response.put("data",searchResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
