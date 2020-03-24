/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccfco.wgyweb.service;

import com.ccfco.wgyweb.dao.TongzhiDao;
import com.ccfco.wgyweb.domain.Tongzhi;
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
public class TongzhiServiceImpl implements TongzhiService{

    @Autowired
    private TongzhiDao tongzhiDao;

    @Override
    public Tongzhi saveTongzhi(Tongzhi tongzhi) {
       return tongzhiDao.save(tongzhi);
    }

    @Override
    public void removeTongzhi(Long id) {
        tongzhiDao.delete(id);
    }

    @Override
    public void removeTongzhisInBatch(List<Tongzhi> tongzhis) {
        tongzhiDao.deleteInBatch(tongzhis);
    }

    @Override
    public Tongzhi updateTongzhi(Tongzhi tongzhi) {
        return tongzhiDao.save(tongzhi);
    }

    @Override
    public Tongzhi getTongzhiById(Long id) {
        return tongzhiDao.getOne(id);
    }

    @Override
    public List<Tongzhi> listTongzhis() {
        return tongzhiDao.findAll();
    }

    @Override
    public Page<Tongzhi> listTongzhisByTitleLike(String title, Pageable pageable) {
        title="%"+title+"%";
        Page<Tongzhi> tongzhis=tongzhiDao.findByTitleLikeOrderByPublishTimeDesc(title, pageable);
        return tongzhis;
    }
    
    
}
