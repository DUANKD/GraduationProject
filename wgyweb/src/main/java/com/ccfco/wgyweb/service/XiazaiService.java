package com.ccfco.wgyweb.service;

import com.ccfco.wgyweb.domain.User;
import com.ccfco.wgyweb.domain.Xiazai;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface XiazaiService {
    Xiazai saveXiazai(Xiazai xiazai);


    void removeXiazai(Long id);


    void removeXiazaisInBatch(List<Xiazai> xiazais);

    Xiazai updateXiazai(Xiazai xiazai);

    Xiazai getXiazaiById(Long id);


    List<Xiazai> listXiazais();


    Page<Xiazai> listXiazaisByTitleLike(String title, Pageable pageable);
}
