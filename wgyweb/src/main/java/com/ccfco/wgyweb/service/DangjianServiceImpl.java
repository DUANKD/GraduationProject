/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccfco.wgyweb.service;

import com.ccfco.wgyweb.dao.DangjianDao;
import com.ccfco.wgyweb.domain.Dangjian;
import com.ccfco.wgyweb.domain.User;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author ccfco
 */
@Service
@Transactional
public class DangjianServiceImpl implements DangjianService{

    @Autowired
    private DangjianDao dangjianDao;

    @Override
    public Dangjian saveDangjian(Dangjian dangjian) {
       return dangjianDao.save(dangjian);
    }

    @Override
    public void removeDangjian(Long id) {
        dangjianDao.delete(id);
    }

    @Override
    public void removeDangjiansInBatch(List<Dangjian> dangjians) {
        dangjianDao.deleteInBatch(dangjians);
    }

    @Override
    public Dangjian updateDangjian(Dangjian dangjian) {
        return dangjianDao.save(dangjian);
    }

    @Override
    public Dangjian getDangjianById(Long id) {
        return dangjianDao.getOne(id);
    }

    @Override
    public List<Dangjian> listDangjians() {
        return dangjianDao.findAll();
    }

    @Override
    public Page<Dangjian> listDangjiansByTitleLike(String title, Pageable pageable) {
        title="%"+title+"%";
        Page<Dangjian> dangjians=dangjianDao.findByTitleLikeOrderByPublishTimeDesc(title, pageable);
        return dangjians;
    }
    
    
}
