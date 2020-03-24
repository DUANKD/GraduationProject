/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccfco.wgyweb.service;

import com.ccfco.wgyweb.dao.AuthorityDao;
import com.ccfco.wgyweb.domain.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 
 * @author ccfco
 */
@Service
public class AuthorityServiceImpl implements AuthorityService{
    @Autowired
    private AuthorityDao authorityDao;


    public Authority getAuthorityById(Long id) {
        return authorityDao.getOne(id);
    }
}
