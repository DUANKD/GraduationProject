/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccfco.wgyweb.controller;

import com.ccfco.wgyweb.domain.Authority;
import com.ccfco.wgyweb.domain.Biyesheng;
import com.ccfco.wgyweb.domain.Dangjian;
import com.ccfco.wgyweb.domain.Dongtai;
import com.ccfco.wgyweb.domain.User;
import com.ccfco.wgyweb.domain.Gaikuang;
import com.ccfco.wgyweb.service.AuthorityService;
import com.ccfco.wgyweb.service.JiaoyanService;
import com.ccfco.wgyweb.service.UserService;
import com.ccfco.wgyweb.domain.Jiaoyan;
import com.ccfco.wgyweb.domain.Tongzhi;
import com.ccfco.wgyweb.domain.Tuanxue;
import com.ccfco.wgyweb.domain.Tupian;
import com.ccfco.wgyweb.service.BiyeshengService;
import com.ccfco.wgyweb.service.DangjianService;
import com.ccfco.wgyweb.service.DongtaiService;
import com.ccfco.wgyweb.service.GaikuangService;
import com.ccfco.wgyweb.service.TongzhiService;
import com.ccfco.wgyweb.service.TuanxueService;
import com.ccfco.wgyweb.service.TupianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class MainController {
    private static final Long ROLE_USER_AUTHORITY_ID = 3L;
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private UserService userService;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private JiaoyanService jiaoyanService;
    
    @Autowired
    private DongtaiService dongtaiService;
    
    @Autowired
    private BiyeshengService biyeshengService;
    
    @Autowired
    private GaikuangService gaikuangService;
    
    @Autowired
    private DangjianService dangjianService;
    
    @Autowired
    private TongzhiService tongzhiService;
    
    @Autowired
    private TupianService tupianService;
    
    @Autowired
    private TuanxueService tuanxueService;

    @GetMapping("/")
    public String root() {
        return "redirect:/index";
    }


    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("hidenLogin","false");
        model.addAttribute("jiaoxueList", this.getJiaoxueList());
        model.addAttribute("dongtaiList", this.getDongtaiList());
        model.addAttribute("biyeshengList", this.getBiyeshengList());
        model.addAttribute("keyanList", this.getKeyanList());
        model.addAttribute("tupianList", this.getTupianList());
        model.addAttribute("tongzhiList", this.getTongzhiList());
         model.addAttribute("tuanhuoList", this.getTuanhuoList());
        model.addAttribute("xueziList", this.getXueziList());
        model.addAttribute("jianjieList", this.getJianjieList());
         model.addAttribute("lingdaoList", this.getLingdaoList());
        model.addAttribute("jigou", this.getJigouList());
        
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("hidenLogin", "false");

        return "index";
    }

    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        model.addAttribute("errorMsg", "登陆失败，账号或者密码错误！");
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("hidenRegister", "false");
        return "index";
    }

    @GetMapping("/error")
    public String error(Model model) {
        model.addAttribute("errorMsg", "出错了！");
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(User user) {
        List<Authority> authorities = new ArrayList<>();
        authorities.add(authorityService.getAuthorityById(ROLE_USER_AUTHORITY_ID));
        user.setAuthorities(authorities);
        user.setEncodePassword(user.getPassword());
        userService.saveUser(user);
        return "redirect:/login";
    }

    @GetMapping("/logout/page")
    public String logoutPage() {

        return "redirect:/login";
    }



    private List<Jiaoyan> getJiaoxueList() {
        Pageable pageable = new PageRequest(0, 12);
        Page<Jiaoyan> page = jiaoyanService.listJiaoyansByTypeAndTitleLike("jiaoxue", "", pageable);
        List<Jiaoyan> list = page.getContent();    // 当前所在页面数据列表

        for (Jiaoyan jiaoxue : list) {
            entityManager.detach(jiaoxue);
            jiaoxue.setTitle("<i class=\"fa fa-chevron-circle-right fa-fw\"></i>" + jiaoxue.getTitle());
        }
        return list;
    }
    private List<Jiaoyan> getKeyanList() {
        Pageable pageable = new PageRequest(0, 12);
        Page<Jiaoyan> page = jiaoyanService.listJiaoyansByTypeAndTitleLike("keyan", "", pageable);
        List<Jiaoyan> list = page.getContent();    // 当前所在页面数据列表

        for (Jiaoyan keyan : list) {
            entityManager.detach(keyan);
            keyan.setTitle("<i class=\"fa fa-chevron-circle-right fa-fw\"></i>" + keyan.getTitle());
        }
        return list;
    }
    
    private List<Tuanxue> getTuanhuoList() {
        Pageable pageable = new PageRequest(0, 12);
        Page<Tuanxue> page = tuanxueService.listTuanxuesByTypeAndTitleLike("tuanhuo", "", pageable);
        List<Tuanxue> list = page.getContent();    // 当前所在页面数据列表

        for (Tuanxue tuanxue : list) {
            entityManager.detach(tuanxue);
            tuanxue.setTitle("<i class=\"fa fa-chevron-circle-right fa-fw\"></i>" + tuanxue.getTitle());
        }
        return list;
    }
    private List<Tuanxue> getXueziList() {
        Pageable pageable = new PageRequest(0, 12);
        Page<Tuanxue> page = tuanxueService.listTuanxuesByTypeAndTitleLike("xuezi", "", pageable);
        List<Tuanxue> list = page.getContent();    // 当前所在页面数据列表

        for (Tuanxue tuanxue : list) {
            entityManager.detach(tuanxue);
            tuanxue.setTitle("<i class=\"fa fa-chevron-circle-right fa-fw\"></i>" + tuanxue.getTitle());
        }
        return list;
    }
    
    private List<Dongtai> getDongtaiList() {
        Pageable pageable = new PageRequest(0, 12);
        Page<Dongtai> page = dongtaiService.listDongtaisByTitleLike("", pageable);
        List<Dongtai> list = page.getContent();    // 当前所在页面数据列表
        for (Dongtai dongtai : list) {
            entityManager.detach(dongtai);
            dongtai.setTitle("<i class=\"fa fa-chevron-circle-right fa-fw\"></i>" + dongtai.getTitle());
        }
        return list;
    }   
    
    private List<Tongzhi> getTongzhiList() {
        Pageable pageable = new PageRequest(0, 12);
        Page<Tongzhi> page = tongzhiService.listTongzhisByTitleLike("", pageable);
        List<Tongzhi> list = page.getContent();    // 当前所在页面数据列表
        for (Tongzhi tongzhi : list) {
            entityManager.detach(tongzhi);
            tongzhi.setTitle("<i class=\"fa fa-chevron-circle-right fa-fw\"></i>" + tongzhi.getTitle());
        }
        return list;
    }
    
    private List<Tupian> getTupianList() {
        Pageable pageable = new PageRequest(0, 12);
        Page<Tupian> page = tupianService.listTupiansByTitleLike("", pageable);
        List<Tupian> list = page.getContent();    // 当前所在页面数据列表
        for (Tupian tupian : list) {
            entityManager.detach(tupian);
            tupian.setTitle("<i class=\"fa fa-chevron-circle-right fa-fw\"></i>" + tupian.getTitle());
        }
        return list;
    }
    
    
    private List<Dangjian> getDangjianList() {
        Pageable pageable = new PageRequest(0, 12);
        Page<Dangjian> page = dangjianService.listDangjiansByTitleLike("", pageable);
        List<Dangjian> list = page.getContent();    // 当前所在页面数据列表
        for (Dangjian dangjian : list) {
            entityManager.detach(dangjian);
            dangjian.setTitle("<i class=\"fa fa-chevron-circle-right fa-fw\"></i>" + dangjian.getTitle());
        }
        return list;
    }
 
    private List<Biyesheng> getBiyeshengList() {
        Pageable pageable = new PageRequest(0, 12);
        Page<Biyesheng> page = biyeshengService.listBiyeshengsByTitleLike("", pageable);
        List<Biyesheng> list = page.getContent();    // 当前所在页面数据列表

        return list;
    }
    
    private List<Gaikuang> getJianjieList() {
        Pageable pageable = new PageRequest(0, 12);
        Page<Gaikuang> page = gaikuangService.listGaikuangsByTypeAndTitleLike("jianjie", "", pageable);
        List<Gaikuang> list = page.getContent();    // 当前所在页面数据列表

        for (Gaikuang jianjie : list) {
            entityManager.detach(jianjie);
            jianjie.setTitle("<i class=\"fa fa-chevron-circle-right fa-fw\"></i>" + jianjie.getTitle());
        }
        return list;
    }
    
     private List<Gaikuang> getLingdaoList() {
        Pageable pageable = new PageRequest(0, 12);
        Page<Gaikuang> page = gaikuangService.listGaikuangsByTypeAndTitleLike("lingdao", "", pageable);
        List<Gaikuang> list = page.getContent();    // 当前所在页面数据列表

        for (Gaikuang lingdao : list) {
            entityManager.detach(lingdao);
            lingdao.setTitle("<i class=\"fa fa-chevron-circle-right fa-fw\"></i>" + lingdao.getTitle());
        }
        return list;
    }
     
      private List<Gaikuang> getJigouList() {
        Pageable pageable = new PageRequest(0, 12);
        Page<Gaikuang> page = gaikuangService.listGaikuangsByTypeAndTitleLike("jigou", "", pageable);
        List<Gaikuang> list = page.getContent();    // 当前所在页面数据列表

        for (Gaikuang jigou : list) {
            entityManager.detach(jigou);
            jigou.setTitle("<i class=\"fa fa-chevron-circle-right fa-fw\"></i>" + jigou.getTitle());
        }
        return list;
    }
     
}
