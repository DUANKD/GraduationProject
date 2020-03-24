package com.ccfco.wgyweb.service;

import com.ccfco.wgyweb.domain.Search;
import com.ccfco.wgyweb.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SearchService {
    Search saveSearch(Search search);


    void removeSearch(Long id);


    void removeSearchsInBatch(List<Search> searchs);

    Search updateSearch(Search search);

    Search getSearchById(Long id);


    List<Search> listSearchs();


    Page<Search> listSearchsByTitleLike(String title, Pageable pageable);
}
