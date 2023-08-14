package com.forzlp.zhihubackend.controller;

import com.forzlp.zhihubackend.pojo.Follow;
import com.forzlp.zhihubackend.pojo.User;
import com.forzlp.zhihubackend.service.FollowService;
import com.forzlp.zhihubackend.service.UserService;
import com.forzlp.zhihubackend.utils.MD5;
import com.forzlp.zhihubackend.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zlp
 * @date 2023/8/6 22:27
 * 更新用户信息,关注
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    private UserService service;
    private FollowService followService;
    @Autowired
    public UserController(UserService service, FollowService followService) {
        this.service = service;
        this.followService = followService;
    }

    /**
     * 得到用户信息
     * @param id 用户id
     * @return 用户信息
     */
    @GetMapping("/getUser/{id}")
    public Result getUser(@PathVariable Long id) {
        User user = service.getById(id);
        if(user != null) {
            return Result.success();
        }else {
            return Result.fail();
        }
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @PostMapping("/updateUser")
    public Result updateUserById(@RequestBody User user) {
        // 使用MD5加密
        String encrypt = MD5.encrypt(user.getPassword());
        user.setPassword(encrypt);
        int i = service.updateUser(user);
        if(i == 1) {
            return Result.success();
        }else {
            return Result.fail();
        }
    }

    @PostMapping("/follow")
    public Result follow(int uId,int followedId) {
        Follow follow = new Follow();
        follow.setFollowId(uId);
        follow.setFollowedId(followedId);
        int i = followService.follow(follow);
        if(i == 1){
            return Result.success();
        }else {
            return Result.fail();
        }
    }

    @PutMapping("/member")
    public Result member(Long id) {
        int i = service.member(id);
        if(i == 1) {
            log.info("成为会员成功");
            return Result.success();
        }else {
            return Result.fail();
        }
    }
}
