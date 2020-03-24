/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccfco.wgyweb.service;

import com.ccfco.wgyweb.dao.BiyeshengDao;
import com.ccfco.wgyweb.domain.Biyesheng;
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
public class BiyeshengServiceImpl implements BiyeshengService{
    @Autowired
    private BiyeshengDao biyeshengDao;
    
    @Transactional
    @Override
    public Biyesheng saveBiyesheng(Biyesheng biyesheng) {
        return biyeshengDao.save(biyesheng);
    }

    @Override
    public void removeBiyesheng(Long id) {
        biyeshengDao.delete(id);
    }

    @Override
    public void removeBiyeshengsInBatch(List<Biyesheng> biyeshengs) {
        biyeshengDao.deleteInBatch(biyeshengs);
    }

    @Override
    public Biyesheng updateBiyesheng(Biyesheng biyesheng) {
        return biyeshengDao.save(biyesheng);
    }

    @Override
    public Biyesheng getBiyeshengById(Long id) {
        return biyeshengDao.getOne(id);
    }

    @Override
    public List<Biyesheng> listBiyeshengs() {
        return biyeshengDao.findAll();
    }

    @Override
    public Page<Biyesheng> listBiyeshengsByTitleLike(String title, Pageable pageable) {
        title="%"+title+"%";
        Page<Biyesheng> biyeshengs=biyeshengDao.findByTitleLikeOrderByPublishTimeDesc(title,pageable);
        return biyeshengs;
    }

    
}
