package com.ccfco.wgyweb.service;

import com.ccfco.wgyweb.domain.Gaikuang;
import com.ccfco.wgyweb.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GaikuangService {
    Gaikuang saveGaikuang(Gaikuang gaikuang);


    void removeGaikuang(Long id);


    void removeGaikuangsInBatch(List<Gaikuang> gaikuangs);

    Gaikuang updateGaikuang(Gaikuang gaikuang);

    Gaikuang getGaikuangById(Long id);


    List<Gaikuang> listGaikuangs();


    Page<Gaikuang> listGaikuangsByTypeAndTitleLike(String type,String title, Pageable pageable);
}
