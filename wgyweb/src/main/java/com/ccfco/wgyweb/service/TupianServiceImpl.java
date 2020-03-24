/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccfco.wgyweb.service;

import com.ccfco.wgyweb.dao.TupianDao;
import com.ccfco.wgyweb.domain.Tupian;
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
public class TupianServiceImpl implements TupianService{

    @Autowired
    private TupianDao tupianDao;

    @Override
    public Tupian saveTupian(Tupian tupian) {
       return tupianDao.save(tupian);
    }

    @Override
    public void removeTupian(Long id) {
        tupianDao.delete(id);
    }

    @Override
    public void removeTupiansInBatch(List<Tupian> tupians) {
        tupianDao.deleteInBatch(tupians);
    }

    @Override
    public Tupian updateTupian(Tupian tupian) {
        return tupianDao.save(tupian);
    }

    @Override
    public Tupian getTupianById(Long id) {
        return tupianDao.getOne(id);
    }

    @Override
    public List<Tupian> listTupians() {
        return tupianDao.findAll();
    }

    @Override
    public Page<Tupian> listTupiansByTitleLike(String title, Pageable pageable) {
        title="%"+title+"%";
        Page<Tupian> tupians=tupianDao.findByTitleLikeOrderByPublishTimeDesc(title, pageable);
        return tupians;
    }
    
    
}
