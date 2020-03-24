package com.ccfco.wgyweb.controller;

import com.ccfco.wgyweb.domain.Hezuo;
import com.ccfco.wgyweb.domain.User;
import com.ccfco.wgyweb.service.HezuoService;
import com.ccfco.wgyweb.util.ConstraintViolationExceptionHandler;
import com.ccfco.wgyweb.util.Menu;
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
@RequestMapping("/hezuo")

public class HezuoController {
    private static final Logger logger=LoggerFactory.getLogger(HezuoController.class);

    @Autowired
    private HezuoService hezuoService;

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @GetMapping("/list/{type}")
    public ModelAndView list(@PathVariable("type") String type,
                             @RequestParam(value="async",required=false) boolean async,
                             @RequestParam(value="pageIndex",required=false,defaultValue="0") int pageIndex,
                             @RequestParam(value="pageSize",required=false,defaultValue="10") int pageSize,
                             @RequestParam(value="title",required=false,defaultValue="") String title,
                             Model model) {

        Pageable pageable = new PageRequest(pageIndex, pageSize);
        Page<Hezuo> page = hezuoService.listHezuosByTypeAndTitleLike(type,title,pageable);
        List<Hezuo> list = page.getContent();	// 当前所在页面数据列表

        model.addAttribute("page", page);
        model.addAttribute("hezuoList", list);
        model.addAttribute("title","校企合作 》"+Menu.getMenu().get(type));
        model.addAttribute("type",type);
        return new ModelAndView(async==true?"admin/hezuo/list :: #mainContainerRepleace":"admin/hezuo/list", "hezuoModel", model);
    }
    /**
     * 获取 form 表单页面
     * @param model
     * @return
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @GetMapping("/add/{type}")
    public ModelAndView createForm(@PathVariable("type") String type,Model model) {
        model.addAttribute("hezuo", new Hezuo());
        model.addAttribute("type", type);
        return new ModelAndView("/admin/hezuo/edit", "hezuoModel", model);
    }

    /**
     * 新建和更新校企合作
     * @param hezuo


     * @return
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @PostMapping("/update/{type}")
    public ResponseEntity<Response> create(@PathVariable("type") String type, Hezuo hezuo) {


        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

           //新建校企合作
            if(hezuo.getId()==null){
                hezuo.setHit(0);
                hezuo.setPublisher(user);
                hezuo.setPublishTime(new Date());
                hezuo.setUpdateTime(new Date());
                hezuo.setUpdateUser(user);

            }else{
                //更新校企合作,对于表单中没有出现的字段要把原来的值付给它们
                Hezuo originalHezuo=hezuoService.getHezuoById(hezuo.getId());
                hezuo.setHit(originalHezuo.getHit());
                hezuo.setPublisher(originalHezuo.getPublisher());
                hezuo.setPublishTime(originalHezuo.getPublishTime());
                hezuo.setUpdateTime(new Date());
                hezuo.setUpdateUser(user);

            }

            hezuoService.saveHezuo(hezuo);
        } catch (ConstraintViolationException e)  {
            return ResponseEntity.ok().body(new Response(false, ConstraintViolationExceptionHandler.getMessage(e)));
        } catch (Exception e) {
            return ResponseEntity.ok().body(new Response(false, e.getMessage()));
        }
        return ResponseEntity.ok().body(new Response(true, "处理成功", null));
    }

    /**
     * 删除校企合作
     * @param id
     * @return
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Response> delete(@PathVariable("id") Long id) {
        try {
            hezuoService.removeHezuo(id);
        } catch (Exception e) {
            return  ResponseEntity.ok().body( new Response(false, e.getMessage()));
        }
        return  ResponseEntity.ok().body( new Response(true, "处理成功"));
    }

    /**
     * 获取修、添加改校企合作的界面及数据
     * @param id,model
     * @return
     */
    @GetMapping(value = "edit/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    public ModelAndView modifyForm(@PathVariable("id") Long id, Model model) {
        Hezuo hezuo = hezuoService.getHezuoById(id);
        model.addAttribute("hezuo", hezuo);

        return new ModelAndView("admin/hezuo/edit", "hezuoModel", model);
    }

    @GetMapping(value = "homeList/{type}")
    public ModelAndView homeList(@PathVariable("type") String type, Model model) {
        Pageable pageable = new PageRequest(0, 20);
        Page<Hezuo> page = hezuoService.listHezuosByTypeAndTitleLike(type,"", pageable);
        List<Hezuo> list = page.getContent();    // 当前所在页面数据列表

        model.addAttribute("hezuoList",list);
        model.addAttribute("type",type);
        model.addAttribute("title","校企合作 》"+Menu.getMenu().get(type));
        return new ModelAndView("hezuoList", "hezuoModel", model);
    }
    @GetMapping(value = "view/{id}/{type}")
    public ModelAndView homeView(@PathVariable("id") Long id,@PathVariable("type") String type, Model model) {
        Hezuo hezuo = hezuoService.getHezuoById(id);
        hezuo.setHit(hezuo.getHit()+1);
        hezuoService.saveHezuo(hezuo);
        model.addAttribute("hezuo", hezuo);
        model.addAttribute("title","校企合作 》"+Menu.getMenu().get(type));
        return new ModelAndView("hezuoView", "hezuoModel", model);
    }
}
