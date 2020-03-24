/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccfco.wgyweb.service;

import com.ccfco.wgyweb.domain.User;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author ccfco
 */
public interface UserService {
    /**
     * 保存用户
     * @param user
     * @return 
     */
    User saveUser(User user);
    
    /**
     * 删除用户
     * @param id 
     */
    void removeUser(Long id);
    
    /**
     * 删除列表里的用户
     * @param users 
     */
    void removeUsersInBatch(List<User> users);
    
    /**
     * 更新用户
     * @param user
     * @return 
     */
    User updateUser(User user);
    
    /**
     * 根据id获取用户
     * @param id
     * @return 
     */
    User getUserById(Long id);
    
    /**
     * 获取用户列表
     * @return 
     */
    List<User> listUsers();
    
    /**
     * 根据用户名进行分页模糊查询
     * @param name
     * @param pageable
     * @return 
     */
    Page<User> listUsersByNameLike(String name, Pageable pageable);
}
