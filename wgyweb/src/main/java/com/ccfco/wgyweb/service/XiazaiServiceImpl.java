/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccfco.wgyweb.service;

import com.ccfco.wgyweb.dao.XiazaiDao;
import com.ccfco.wgyweb.domain.Xiazai;
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
public class XiazaiServiceImpl implements XiazaiService{

    @Autowired
    private XiazaiDao xiazaiDao;

    @Override
    public Xiazai saveXiazai(Xiazai xiazai) {
       return xiazaiDao.save(xiazai);
    }

    @Override
    public void removeXiazai(Long id) {
        xiazaiDao.delete(id);
    }

    @Override
    public void removeXiazaisInBatch(List<Xiazai> xiazais) {
        xiazaiDao.deleteInBatch(xiazais);
    }

    @Override
    public Xiazai updateXiazai(Xiazai xiazai) {
        return xiazaiDao.save(xiazai);
    }

    @Override
    public Xiazai getXiazaiById(Long id) {
        return xiazaiDao.getOne(id);
    }

    @Override
    public List<Xiazai> listXiazais() {
        return xiazaiDao.findAll();
    }

    @Override
    public Page<Xiazai> listXiazaisByTitleLike(String title, Pageable pageable) {
        title="%"+title+"%";
        Page<Xiazai> xiazais=xiazaiDao.findByTitleLikeOrderByPublishTimeDesc(title, pageable);
        return xiazais;
    }
    
    
}
