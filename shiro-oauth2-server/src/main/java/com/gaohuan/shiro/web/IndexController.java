package com.gaohuan.shiro.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by gh on 2015/12/8.
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/")
    public String index(Model model) {
        return "index";
    }


    @RequestMapping("/favicon.ico")
    @ResponseBody
    public void favicon() {
    }
}
