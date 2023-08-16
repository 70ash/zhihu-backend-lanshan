package com.forzlp.zhihubackend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zlp
 * @date 2023/8/16 14:51
 */
@Controller
public class loginController {
    @RequestMapping("/")
    public String toLogin() {
        return "login";
    }
}
