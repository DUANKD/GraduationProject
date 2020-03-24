package com.ccfco.wgyweb.service;

import com.ccfco.wgyweb.domain.Dangjian;
import com.ccfco.wgyweb.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DangjianService {
    Dangjian saveDangjian(Dangjian dangjian);


    void removeDangjian(Long id);


    void removeDangjiansInBatch(List<Dangjian> dangjians);

    Dangjian updateDangjian(Dangjian dangjian);

    Dangjian getDangjianById(Long id);


    List<Dangjian> listDangjians();


    Page<Dangjian> listDangjiansByTitleLike(String title, Pageable pageable);
}
