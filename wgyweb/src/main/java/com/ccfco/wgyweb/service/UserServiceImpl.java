/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccfco.wgyweb.service;

import com.ccfco.wgyweb.dao.UserDao;
import com.ccfco.wgyweb.domain.User;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
/**
 * 
 * @author ccfco
 */
@Service
public class UserServiceImpl implements UserService,UserDetailsService {
    @Autowired
    private UserDao userDao;


    @Transactional
    @Override
    public User saveUser(User user) {
        return userDao.save(user);
    }

    @Transactional
    @Override
    public void removeUser(Long id) {
        userDao.delete(id);
    }

    @Transactional
    @Override
    public void removeUsersInBatch(List<User> users) {
        userDao.deleteInBatch(users);
    }

    @Transactional
    @Override
    public User updateUser(User user) {
        return userDao.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getOne(id);
    }

    @Override
    public List<User> listUsers() {
        return userDao.findAll();
    }

    @Override
    public Page<User> listUsersByNameLike(String name, Pageable pageable) {
        // 模糊查询
        name = "%" + name + "%";
        Page<User> users = userDao.findByNameLike(name, pageable);
        return users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.findByUsername(username);
    }
}
