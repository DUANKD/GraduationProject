package com.ccfco.wgyweb.service;

import com.ccfco.wgyweb.domain.Tupian;
import com.ccfco.wgyweb.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TupianService {
    Tupian saveTupian(Tupian tupian);


    void removeTupian(Long id);


    void removeTupiansInBatch(List<Tupian> tupians);

    Tupian updateTupian(Tupian tupian);

    Tupian getTupianById(Long id);


    List<Tupian> listTupians();


    Page<Tupian> listTupiansByTitleLike(String title, Pageable pageable);
}
