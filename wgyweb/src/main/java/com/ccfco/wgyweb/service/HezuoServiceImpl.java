package com.ccfco.wgyweb.service;

import com.ccfco.wgyweb.dao.HezuoDao;
import com.ccfco.wgyweb.domain.Hezuo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class HezuoServiceImpl implements HezuoService {

    @Autowired
    private HezuoDao hezuoDao;
    @Override
    public Hezuo saveHezuo(Hezuo hezuo) {
        return hezuoDao.save(hezuo);
    }

    @Override
    public void removeHezuo(Long id) {
        hezuoDao.delete(id);
    }

    @Override
    public void removeHezuosInBatch(List<Hezuo> hezuos) {
        hezuoDao.deleteInBatch(hezuos);
    }

    @Override
    public Hezuo updateHezuo(Hezuo hezuo) {
        return hezuoDao.save(hezuo);
    }

    @Override
    public Hezuo getHezuoById(Long id) {
        return hezuoDao.getOne(id);
    }

    @Override
    public List<Hezuo> listHezuos() {
        return hezuoDao.findAll();
    }

    @Override
    public Page<Hezuo> listHezuosByTypeAndTitleLike(String type ,String title, Pageable pageable){
    //public Page<Hezuo> listHezuosByNameLike(String name, Pageable pageable) {

        title = "%" + title + "%";
        Page<Hezuo> hezuos = hezuoDao.findByTypeAndTitleLikeOrderByPublishTimeDesc(type, title, pageable);
        return hezuos;
    }
}
