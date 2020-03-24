package com.ccfco.wgyweb.controller;

import com.ccfco.wgyweb.domain.Tuanxue;
import com.ccfco.wgyweb.domain.User;
import com.ccfco.wgyweb.service.TuanxueService;
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
@RequestMapping("/tuanxue")

public class TuanxueController {
    private static final Logger logger=LoggerFactory.getLogger(TuanxueController.class);

    @Autowired
    private TuanxueService tuanxueService;

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @GetMapping("/list/{type}")
    public ModelAndView list(@PathVariable("type") String type,
                             @RequestParam(value="async",required=false) boolean async,
                             @RequestParam(value="pageIndex",required=false,defaultValue="0") int pageIndex,
                             @RequestParam(value="pageSize",required=false,defaultValue="10") int pageSize,
                             @RequestParam(value="title",required=false,defaultValue="") String title,
                             Model model) {

        Pageable pageable = new PageRequest(pageIndex, pageSize);
        Page<Tuanxue> page = tuanxueService.listTuanxuesByTypeAndTitleLike(type,title,pageable);
        List<Tuanxue> list = page.getContent();	// 当前所在页面数据列表

        model.addAttribute("page", page);
        model.addAttribute("tuanxueList", list);
        model.addAttribute("title","团学工作 》"+Menu.getMenu().get(type));
        model.addAttribute("type",type);
        return new ModelAndView(async==true?"admin/tuanxue/list :: #mainContainerRepleace":"admin/tuanxue/list", "tuanxueModel", model);
    }
    /**
     * 获取 form 表单页面
     * @param model
     * @return
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @GetMapping("/add/{type}")
    public ModelAndView createForm(@PathVariable("type") String type,Model model) {
        model.addAttribute("tuanxue", new Tuanxue());
        model.addAttribute("type", type);
        return new ModelAndView("/admin/tuanxue/edit", "tuanxueModel", model);
    }

    /**
     * 新建和更新团学工作
     * @param tuanxue


     * @return
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @PostMapping("/update/{type}")
    public ResponseEntity<Response> create(@PathVariable("type") String type, Tuanxue tuanxue) {


        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

           //新建团学工作
            if(tuanxue.getId()==null){
                tuanxue.setHit(0);
                tuanxue.setPublisher(user);
                tuanxue.setPublishTime(new Date());
                tuanxue.setUpdateTime(new Date());
                tuanxue.setUpdateUser(user);

            }else{
                //更新团学工作,对于表单中没有出现的字段要把原来的值付给它们
                Tuanxue originalTuanxue=tuanxueService.getTuanxueById(tuanxue.getId());
                tuanxue.setHit(originalTuanxue.getHit());
                tuanxue.setPublisher(originalTuanxue.getPublisher());
                tuanxue.setPublishTime(originalTuanxue.getPublishTime());
                tuanxue.setUpdateTime(new Date());
                tuanxue.setUpdateUser(user);

            }

            tuanxueService.saveTuanxue(tuanxue);
        } catch (ConstraintViolationException e)  {
            return ResponseEntity.ok().body(new Response(false, ConstraintViolationExceptionHandler.getMessage(e)));
        } catch (Exception e) {
            return ResponseEntity.ok().body(new Response(false, e.getMessage()));
        }
        return ResponseEntity.ok().body(new Response(true, "处理成功", null));
    }

    /**
     * 删除团学工作
     * @param id
     * @return
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Response> delete(@PathVariable("id") Long id) {
        try {
            tuanxueService.removeTuanxue(id);
        } catch (Exception e) {
            return  ResponseEntity.ok().body( new Response(false, e.getMessage()));
        }
        return  ResponseEntity.ok().body( new Response(true, "处理成功"));
    }

    /**
     * 获取修、添加改团学工作的界面及数据
     * @param id,model
     * @return
     */
    @GetMapping(value = "edit/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    public ModelAndView modifyForm(@PathVariable("id") Long id, Model model) {
        Tuanxue tuanxue = tuanxueService.getTuanxueById(id);
        model.addAttribute("tuanxue", tuanxue);

        return new ModelAndView("admin/tuanxue/edit", "tuanxueModel", model);
    }

    @GetMapping(value = "homeList/{type}")
    public ModelAndView homeList(@PathVariable("type") String type, Model model) {
        Pageable pageable = new PageRequest(0, 20);
        Page<Tuanxue> page = tuanxueService.listTuanxuesByTypeAndTitleLike(type, "", pageable);
        List<Tuanxue> list = page.getContent();    // 当前所在页面数据列表

        model.addAttribute("tuanxueList",list);
        model.addAttribute("type",type);
        model.addAttribute("title","团学工作 》"+Menu.getMenu().get(type));
        return new ModelAndView("tuanxueList", "tuanxueModel", model);
    }
    @GetMapping(value = "view/{id}/{type}")
    public ModelAndView homeView(@PathVariable("id") Long id,@PathVariable("type") String type, Model model) {
        Tuanxue tuanxue = tuanxueService.getTuanxueById(id);
        tuanxue.setHit(tuanxue.getHit()+1);
        tuanxueService.saveTuanxue(tuanxue);
        model.addAttribute("tuanxue", tuanxue);
        model.addAttribute("title","团学工作 》"+Menu.getMenu().get(type));
        return new ModelAndView("tuanxueView", "tuanxueModel", model);
    }
}
