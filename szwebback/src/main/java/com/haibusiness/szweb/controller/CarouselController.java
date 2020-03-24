package com.haibusiness.szweb.controller;

import com.haibusiness.szweb.entity.Carousel;
import com.haibusiness.szweb.entity.User;
import com.haibusiness.szweb.service.CarouselService;
import com.haibusiness.szweb.service.MenuService;
import com.haibusiness.szweb.vo.Response;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
public class CarouselController {

    private CarouselService carouselService;
    private MenuService menuService;

    @GetMapping("/public/carousel")
    public ResponseEntity list(
            @RequestParam(value = "pageIndex", required = false, defaultValue = "0") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam(value = "title", required = false, defaultValue = "") String title
    ) {

        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<Carousel> page = carouselService.listCarouselsByTitleLike(title, pageable);
        Map model = new HashMap<>();
        model.put("page", page);
        model.put("name", menuService.getMenus().get("carousel"));
        return ResponseEntity.ok().body(model);
    }


    @GetMapping(value = "/public/carousel/{id}")
    public ResponseEntity getCarousel(@PathVariable Long id) {
        Carousel carousel = null;
        try {
            carousel = carouselService.getCarouselById(id);
        } catch (Exception e) {
            return ResponseEntity.ok().body(new Response(false, "获取数据失败"));
        }
        return ResponseEntity.ok().body(carousel);
    }

    /**
     * 新建
     *
     * @param carousel
     * @return ResponseEntity
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @PostMapping(value = "/carousel")
    public ResponseEntity<Response> createDaogou(@RequestBody Carousel carousel) {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            carousel.setUpdateUser(user);
            carouselService.saveCarousel(carousel);
        } catch (Exception e) {
            return ResponseEntity.ok().body(new Response(false, e.getMessage()));
        }
        return ResponseEntity.ok().body(new Response(true, "处理成功", null));
    }

    /**
     * 更新
     *
     * @param carousel
     * @return ResponseEntity
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @PutMapping(value = "/carousel")
    public ResponseEntity<Response> updateCarousel(@RequestBody Carousel carousel) {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            carousel.setUpdateUser(user);
            //更新,对于表单中没有出现的字段要把原来的值付给它们
            Carousel originalCollaboration = carouselService.getCarouselById(carousel.getId());
            carousel.setHit(originalCollaboration.getHit());
            carouselService.saveCarousel(carousel);
        } catch (Exception e) {
            return ResponseEntity.ok().body(new Response(false, "更新失败"));
        }
        return ResponseEntity.ok().body(new Response(true, "处理成功"));
    }

    /**
     * 删除
     *
     * @param id
     * @return ResponseEntity
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @DeleteMapping(value = "/carousel/{id}")
    public ResponseEntity<Response> deleteCarousel(@PathVariable Long id) {
        try {
            carouselService.removeCarousel(id);
        } catch (Exception e) {
            return ResponseEntity.ok().body(new Response(false, "删除失败"));
        }
        return ResponseEntity.ok().body(new Response(true, "处理成功"));
    }

}
