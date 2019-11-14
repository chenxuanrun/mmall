package com.mmall.controller.templates;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: mmall
 * @description:
 * @author: cxr
 * @create: 2019-11-14 14:56
 **/
@Controller
public class TestView {
    @RequestMapping("index.html")
    public String index(){
        System.out.println("进入了");
        return "index";
    }
}
