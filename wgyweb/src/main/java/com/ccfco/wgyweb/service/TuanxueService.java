package com.ccfco.wgyweb.service;

import com.ccfco.wgyweb.domain.Tuanxue;
import com.ccfco.wgyweb.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TuanxueService {
    Tuanxue saveTuanxue(Tuanxue tuanxue);


    void removeTuanxue(Long id);


    void removeTuanxuesInBatch(List<Tuanxue> tuanxues);

    Tuanxue updateTuanxue(Tuanxue tuanxue);

    Tuanxue getTuanxueById(Long id);


    List<Tuanxue> listTuanxues();


    Page<Tuanxue> listTuanxuesByTypeAndTitleLike(String type ,String title, Pageable pageable);
}
