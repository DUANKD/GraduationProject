package com.ccfco.wgyweb.service;

import com.ccfco.wgyweb.domain.Jiuye;
import com.ccfco.wgyweb.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface JiuyeService {
    Jiuye saveJiuye(Jiuye dongtai);


    void removeJiuye(Long id);


    void removeJiuyesInBatch(List<Jiuye> jiuyes);

    Jiuye updateJiuye(Jiuye jiuye);

    Jiuye getJiuyeById(Long id);


    List<Jiuye> listJiuyes();


    Page<Jiuye> listJiuyesByTypeAndTitleLike(String type ,String title, Pageable pageable);
}
