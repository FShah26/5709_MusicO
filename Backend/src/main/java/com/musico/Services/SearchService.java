//Author: Neehar Parupalli Ramakrishna
//Created On: 15th July 2021
package com.musico.Services;

import com.musico.Models.SearchDao;
import com.musico.Responses.SearchResponse;

public class SearchService {

    SearchDao searchDao = new SearchDao();

    public SearchResponse search(String searchText) {
        return searchDao.search(searchText);
    }
}
