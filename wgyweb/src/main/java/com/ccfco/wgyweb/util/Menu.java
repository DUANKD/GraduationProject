package com.ccfco.wgyweb.util;

import java.util.HashMap;
import java.util.Map;

public class Menu {
    private static final Map<String,String> menus=new HashMap<>();
    public static Map<String,String> getMenu(){

        menus.put("gaikuang","系部概况");
        menus.put("jianjie","学院简介");
        menus.put("lingdao","领导分工");
        menus.put("jigou","组织机构");
        menus.put("dangjianDao","党建工作");
        menus.put("jiaoyan","教研工作");
        menus.put("jiaoxue","教学活动");
        menus.put("keyan","科研情况");
        menus.put("tuanxue","团学工作");
        menus.put("tuanhuo","团学活动");
        menus.put("xuezi","优秀学子");
        menus.put("jiuye","就业情况");
        menus.put("shixun","校内实训");
        menus.put("shixi","定岗实习");
        menus.put("jiuxin","就业信息");
        menus.put("hezuo","校企合作");
        menus.put("qiye","企业情况");
        menus.put("hedong","合作动态");
        menus.put("xiazai","资料下载");
        menus.put("tongzhi","通知通告");
        menus.put("dongtai","学院动态");
        menus.put("tupian","图片新闻");
        menus.put("biyesheng","优秀毕业生");

     return menus;

    }

    private Menu() {
    }
}
