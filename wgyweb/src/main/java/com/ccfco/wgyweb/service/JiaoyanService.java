package com.ccfco.wgyweb.service;

import com.ccfco.wgyweb.domain.Jiaoyan;
import com.ccfco.wgyweb.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface JiaoyanService {
    Jiaoyan saveJiaoyan(Jiaoyan jiaoyan);


    void removeJiaoyan(Long id);


    void removeJiaoyansInBatch(List<Jiaoyan> jiaoyans);

    Jiaoyan updateJiaoyan(Jiaoyan jiaoyan);

    Jiaoyan getJiaoyanById(Long id);


    List<Jiaoyan> listJiaoyans();


    Page<Jiaoyan> listJiaoyansByTypeAndTitleLike(String type,String title, Pageable pageable);
}
