/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccfco.wgyweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author ccfco
 */
@Controller
public class AdminController {
    @GetMapping("/admin")
    public String listUsers(Model model){
        model.addAttribute("title","首页");
        return "admin/index";
    }
}
