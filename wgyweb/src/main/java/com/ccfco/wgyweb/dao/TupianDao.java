/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccfco.wgyweb.dao;

import com.ccfco.wgyweb.domain.Tuanxue;
import com.ccfco.wgyweb.domain.Tupian;
import java.io.Serializable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author ccfco
 */
public interface TupianDao extends JpaRepository<Tupian, Long>{

    public Page<Tupian> findByTitleLikeOrderByPublishTimeDesc(String title, Pageable pageable);
    
}
