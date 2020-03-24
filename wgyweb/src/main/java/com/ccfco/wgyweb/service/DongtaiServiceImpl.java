/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccfco.wgyweb.service;

import com.ccfco.wgyweb.dao.DongtaiDao;
import com.ccfco.wgyweb.domain.Dongtai;
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
public class DongtaiServiceImpl implements DongtaiService{

    @Autowired
    private DongtaiDao dongtaiDao;

    @Override
    public Dongtai saveDongtai(Dongtai dongtai) {
       return dongtaiDao.save(dongtai);
    }

    @Override
    public void removeDongtai(Long id) {
        dongtaiDao.delete(id);
    }

    @Override
    public void removeDongtaisInBatch(List<Dongtai> dongtais) {
        dongtaiDao.deleteInBatch(dongtais);
    }

    @Override
    public Dongtai updateDongtai(Dongtai dongtai) {
        return dongtaiDao.save(dongtai);
    }

    @Override
    public Dongtai getDongtaiById(Long id) {
        return dongtaiDao.getOne(id);
    }

    @Override
    public List<Dongtai> listDongtais() {
        return dongtaiDao.findAll();
    }

    @Override
    public Page<Dongtai> listDongtaisByTitleLike(String title, Pageable pageable) {
        title="%"+title+"%";
        Page<Dongtai> dongtais=dongtaiDao.findByTitleLikeOrderByPublishTimeDesc(title, pageable);
        return dongtais;
    }
    
    
}
