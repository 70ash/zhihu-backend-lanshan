package com.forzlp.zhihubackend.controller;

import com.forzlp.zhihubackend.pojo.User;
import com.forzlp.zhihubackend.service.UserService;
import com.forzlp.zhihubackend.utils.JwtHelper;
import com.forzlp.zhihubackend.utils.MD5;
import com.forzlp.zhihubackend.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @author zlp
 * @date 2023/8/4 17:41
 * 登录和注册
 */
@RestController
@Slf4j
@RequestMapping("/index")
public class IndexController {
    @Autowired
    private UserService service;
    // 登录
    @GetMapping("/login")
    public Result login(@RequestBody User user) {
        if(user == null) return Result.fail();
        String name = user.getName();
        String password = user.getPassword();
        String encrypt = MD5.encrypt(password);
        User myUser = service.getUserByNameAndPassword(name, encrypt);
        String token = JwtHelper.createToken(user.getUId(), user.getName());
        HashMap<String, Object> map = new HashMap<>();
        map.put("token",token);
        if(myUser != null) {
            log.info("登录成功");
            return Result.success(map);
        }else {
            return Result.fail();
        }
    }

    // 注册
    @PostMapping("/signIn")
    public Result<User> signIn(@RequestBody User user) {
        log.info("使用MD5加密");
        String encrypt = MD5.encrypt(user.getPassword());
        user.setPassword(encrypt);
        int i = service.saveUser(user);
        if(i == 1){
            log.info("注册成功");
            return Result.success(user);
        }else {
            return Result.fail();
        }
    }
}
