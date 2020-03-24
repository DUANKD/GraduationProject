/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccfco.wgyweb.service;

import com.ccfco.wgyweb.dao.LianjieDao;
import com.ccfco.wgyweb.domain.Lianjie;
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
public class LianjieServiceImpl implements LianjieService{

    @Autowired
    private LianjieDao lianjieDao;

    @Override
    public Lianjie saveLianjie(Lianjie lianjie) {
       return lianjieDao.save(lianjie);
    }

    @Override
    public void removeLianjie(Long id) {
        lianjieDao.delete(id);
    }

    @Override
    public void removeLianjiesInBatch(List<Lianjie> lianjies) {
        lianjieDao.deleteInBatch(lianjies);
    }

    @Override
    public Lianjie updateLianjie(Lianjie lianjie) {
        return lianjieDao.save(lianjie);
    }

    @Override
    public Lianjie getLianjieById(Long id) {
        return lianjieDao.getOne(id);
    }

    @Override
    public List<Lianjie> listLianjies() {
        return lianjieDao.findAll();
    }

    @Override
    public Page<Lianjie> listLianjiesByTitleLike(String title, Pageable pageable) {
        title="%"+title+"%";
        Page<Lianjie> lianjies=lianjieDao.findByTitleLikeOrderByPublishTimeDesc(title, pageable);
        return lianjies;
    }
    
    
}
