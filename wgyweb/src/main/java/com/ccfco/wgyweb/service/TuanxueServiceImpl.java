/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccfco.wgyweb.service;

import com.ccfco.wgyweb.domain.Tuanxue;
import com.ccfco.wgyweb.dao.TuanxueDao;
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
public class TuanxueServiceImpl implements TuanxueService {

    @Autowired
    private TuanxueDao tuanxueDao;

    @Override
    public Tuanxue saveTuanxue(Tuanxue tuanxue) {
        return tuanxueDao.save(tuanxue);
    }

    @Override
    public void removeTuanxue(Long id) {
        tuanxueDao.delete(id);
    }

    @Override
    public void removeTuanxuesInBatch(List<Tuanxue> tuanxues) {
        tuanxueDao.deleteInBatch(tuanxues);
    }

    @Override
    public Tuanxue updateTuanxue(Tuanxue tuanxue) {
        return tuanxueDao.save(tuanxue);
    }

    @Override
    public Tuanxue getTuanxueById(Long id) {
        return tuanxueDao.getOne(id);
    }

    @Override
    public List<Tuanxue> listTuanxues() {
        return tuanxueDao.findAll();
    }


    @Override
    public Page<Tuanxue> listTuanxuesByTypeAndTitleLike(String type, String title, Pageable pageable) {
        title = "%" + title + "%";
        Page<Tuanxue> tuanxues = tuanxueDao.findByTypeAndTitleLikeOrderByPublishTimeDesc(type, title, pageable);
        return tuanxues;
    }
}
