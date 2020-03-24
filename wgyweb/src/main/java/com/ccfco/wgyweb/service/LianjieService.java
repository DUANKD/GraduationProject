package com.ccfco.wgyweb.service;

import com.ccfco.wgyweb.domain.Lianjie;
import com.ccfco.wgyweb.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LianjieService {
    Lianjie saveLianjie(Lianjie lianjie);


    void removeLianjie(Long id);


    void removeLianjiesInBatch(List<Lianjie> lianjies);

    Lianjie updateLianjie(Lianjie lianjie);

    Lianjie getLianjieById(Long id);


    List<Lianjie> listLianjies();


    Page<Lianjie> listLianjiesByTitleLike(String title, Pageable pageable);
}
