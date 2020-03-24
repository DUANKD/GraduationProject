/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccfco.wgyweb.service;

import com.ccfco.wgyweb.dao.GaikuangDao;
import com.ccfco.wgyweb.domain.Gaikuang;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ccfco
 */
@Service
@Transactional
public class GaikuangServiceImpl implements GaikuangService{

    @Autowired
    private GaikuangDao gaikuangDao;
    
    @Override
    public Gaikuang saveGaikuang(Gaikuang gaikuang) {
        return gaikuangDao.save(gaikuang);
    }

    @Override
    public void removeGaikuang(Long id) {
        gaikuangDao.delete(id);
    }

    @Override
    public void removeGaikuangsInBatch(List<Gaikuang> gaikuangs) {
        gaikuangDao.deleteInBatch(gaikuangs);
    }

    @Override
    public Gaikuang updateGaikuang(Gaikuang gaikuang) {
        return gaikuangDao.save(gaikuang);
    }

    @Override
    public Gaikuang getGaikuangById(Long id) {
        return gaikuangDao.getOne(id);
    }

    @Override
    public List<Gaikuang> listGaikuangs() {
        return gaikuangDao.findAll();
    }

    @Override
    public Page<Gaikuang> listGaikuangsByTypeAndTitleLike(String type, String title, Pageable pageable) {
        title = "%" + title + "%";
        Page<Gaikuang> gaikuangs = gaikuangDao.findByTypeAndTitleLikeOrderByPublishTimeDesc(type, title, pageable);
        return gaikuangs;
    }
    
}
