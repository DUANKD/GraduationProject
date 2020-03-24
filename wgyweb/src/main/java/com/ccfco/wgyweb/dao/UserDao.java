/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccfco.wgyweb.dao;

import com.ccfco.wgyweb.domain.User;

import java.io.Serializable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author ccfco
 */
public interface UserDao extends JpaRepository<User, Long>{

    public Page<User> findByNameLike(String name, Pageable pageable);

    public UserDetails findByUsername(String username);
    
}
