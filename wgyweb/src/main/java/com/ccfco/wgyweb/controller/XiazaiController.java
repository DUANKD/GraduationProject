/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccfco.wgyweb.controller;

import com.ccfco.wgyweb.domain.Xiazai;
import com.ccfco.wgyweb.domain.User;
import com.ccfco.wgyweb.service.XiazaiService;
import com.ccfco.wgyweb.util.ConstraintViolationExceptionHandler;
import com.ccfco.wgyweb.vo.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/xiazai")

public class XiazaiController {
    private static final Logger logger=LoggerFactory.getLogger(XiazaiController.class);

    @Autowired
    private XiazaiService xiazaiService;

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @GetMapping("/list")
    public ModelAndView list(
                             @RequestParam(value="async",required=false) boolean async,
                             @RequestParam(value="pageIndex",required=false,defaultValue="0") int pageIndex,
                             @RequestParam(value="pageSize",required=false,defaultValue="10") int pageSize,
                             @RequestParam(value="title",required=false,defaultValue="") String title,
                             Model model) {

        Pageable pageable = new PageRequest(pageIndex, pageSize);
        Page<Xiazai> page = xiazaiService.listXiazaisByTitleLike(title,pageable);
        List<Xiazai> list = page.getContent();	// 当前所在页面数据列表

        model.addAttribute("page", page);
        model.addAttribute("xiazaiList", list);
        model.addAttribute("title","资料下载");
        return new ModelAndView(async==true?"admin/xiazai/list :: #mainContainerRepleace":"admin/xiazai/list", "xiazaiModel", model);
    }
    /**
     * 获取 form 表单页面
     * @param model
     * @return
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @GetMapping("/add")
    public ModelAndView createForm(Model model) {
        model.addAttribute("xiazai", new Xiazai());
        return new ModelAndView("/admin/xiazai/edit", "xiazaiModel", model);
    }

    /**
     * 新建和更新资料下载
     * @param xiazai


     * @return
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @PostMapping("/update")
    public ResponseEntity<Response> create( Xiazai xiazai) {


        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

           //新建资料下载
            if(xiazai.getId()==null){
                //xiazai.setHit(0);
                xiazai.setPublisher(user);
                xiazai.setPublishTime(new Date());
                xiazai.setUpdateTime(new Date());
                xiazai.setUpdateUser(user);

            }else{
                //更新资料下载,对于表单中没有出现的字段要把原来的值付给它们
                Xiazai originalXiazai=xiazaiService.getXiazaiById(xiazai.getId());
                //xiazai.setHit(originalXiazai.getHit());
                xiazai.setPublisher(originalXiazai.getPublisher());
                xiazai.setPublishTime(originalXiazai.getPublishTime());
                xiazai.setUpdateTime(new Date());
                xiazai.setUpdateUser(user);

            }

            xiazaiService.saveXiazai(xiazai);
        } catch (ConstraintViolationException e)  {
            return ResponseEntity.ok().body(new Response(false, ConstraintViolationExceptionHandler.getMessage(e)));
        } catch (Exception e) {
            return ResponseEntity.ok().body(new Response(false, e.getMessage()));
        }
        return ResponseEntity.ok().body(new Response(true, "处理成功", null));
    }

    /**
     * 删除资料下载
     * @param id
     * @return
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Response> delete(@PathVariable("id") Long id) {
        try {
            xiazaiService.removeXiazai(id);
        } catch (Exception e) {
            return  ResponseEntity.ok().body( new Response(false, e.getMessage()));
        }
        return  ResponseEntity.ok().body( new Response(true, "处理成功"));
    }

    /**
     * 获取修、添加改资料下载的界面及数据
     * @param id,model
     * @return
     */
    @GetMapping(value = "edit/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    public ModelAndView modifyForm(@PathVariable("id") Long id, Model model) {
        Xiazai xiazai = xiazaiService.getXiazaiById(id);
        model.addAttribute("xiazai", xiazai);

        return new ModelAndView("admin/xiazai/edit", "xiazaiModel", model);
    }

    @GetMapping(value = "homeList")
    public ModelAndView homeList(Model model) {
        Pageable pageable = new PageRequest(0, 20);
        Page<Xiazai> page = xiazaiService.listXiazaisByTitleLike("", pageable);
        List<Xiazai> list = page.getContent();    // 当前所在页面数据列表

        model.addAttribute("xiazaiList",list);
        model.addAttribute("title","资料下载");
        return new ModelAndView("xiazaiList", "xiazaiModel", model);
    }
    @GetMapping(value = "view/{id}")
    public ModelAndView homeView(@PathVariable("id") Long id, Model model) {
        Xiazai xiazai = xiazaiService.getXiazaiById(id);
       //xiazai.setHit(xiazai.getHit()+1);
        xiazaiService.saveXiazai(xiazai);
        model.addAttribute("xiazai", xiazai);
        model.addAttribute("title","资料下载");
        return new ModelAndView("xiazaiView", "xiazaiModel", model);
    }
}
