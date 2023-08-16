package com.forzlp.zhihubackend.controller;

import com.forzlp.zhihubackend.pojo.*;
import com.forzlp.zhihubackend.service.ArticleService;
import com.forzlp.zhihubackend.service.FollowService;
import com.forzlp.zhihubackend.service.ProblemService;
import com.forzlp.zhihubackend.service.UserService;
import com.forzlp.zhihubackend.utils.DateFormat;
import com.forzlp.zhihubackend.utils.MD5;
import com.forzlp.zhihubackend.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    private ArticleService articleService;
    private ProblemService problemService;
    @Autowired
    public UserController(UserService service, FollowService followService,ArticleService articleService,ProblemService problemService) {
        this.service = service;
        this.followService = followService;
        this.articleService = articleService;
        this.problemService = problemService;
    }

    /**
     * 得到用户信息
     * @param id 用户id
     * @return 用户信息
     */
    @GetMapping("/getUser")
    public Result getUser(Long id) {
        User user = service.getById(id);
        if(user != null) {
            user.setCTime(DateFormat.formatDate(user.getCreateTime()));
            return Result.success(user);
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
        User user1 = service.getById(user.getId());
        user.setCreateTime(user1.getCreateTime());
        // 使用MD5加密
        String encrypt = MD5.encrypt(user.getPassword());
        user.setPassword(encrypt);
        int i = service.updateUser(user);
        System.out.println(user);
        if(i == 1) {
            user.setCTime(DateFormat.formatDate(user.getCreateTime()));
            return Result.success(user);
        }else {
            return Result.fail();
        }
    }

    @PostMapping("/follow")
    public Result follow(Long uId,Long followedId) {
        List<Long> followedIds = followService.getById(uId);
        for (Long aLong :followedIds) {
            if(aLong == followedId) return Result.fail("你已经关注过这个用户了");
        }
        Follow follow = new Follow();
        follow.setFollowId(uId);
        follow.setFollowedId(followedId);
        int i = followService.follow(follow);
        if(i == 1){
            return Result.success("关注成功");
        }else {
            return Result.fail();
        }
    }
    @DeleteMapping("cancelFollow")
    public Result cancelFollow(Long uId,Long followedId) {
        int i = followService.cancelFollow(uId,followedId);
        if(i == 1) {
            return Result.success("取消关注成功");
        }else {
            return Result.fail("取消关注失败");
        }
    }
    @PutMapping("/member")
    public Result member(Long id) {
        User user = service.getById(id);
        if (user.getIsMember() == 1){
            return Result.fail("你已经成为会员了");
        }
        int i = service.member(id);
        if(i == 1) {
            log.info("成为会员成功");
            return Result.success("成为会员成功");
        }else {
            return Result.fail();
        }
    }
    @GetMapping("/findArtHistory")
    public Result findArtHistory(Long uId) {
        List<BroHistory> histories = service.listHistory(uId);
        ArrayList<Article> articles = new ArrayList<>();
        for (BroHistory history :histories) {
            Long typeId = history.getTypeId();
            if(typeId == 1){
                Article article = articleService.getById(history.getBroId());
                article.setCTime(DateFormat.formatDate(article.getCreateTime()));
                article.setBroTime(DateFormat.formatDate(history.getBroTime()));
                articles.add(article);
            }
        }
        return Result.success(articles);
    }
    @GetMapping("/findProHistory")
    public Result findProHistory(Long uId) {
        List<BroHistory> histories = service.listHistory(uId);
        ArrayList<Problem> problems = new ArrayList<>();
        for (BroHistory history :histories) {
            Long typeId = history.getTypeId();
            if(typeId == 0){
                Problem problem = problemService.getProblemById(history.getBroId());
                problem.setCTime(DateFormat.formatDate(problem.getCreateTime()));
                problem.setBroTime(DateFormat.formatDate(history.getBroTime()));
                problems.add(problem);
            }
        }
        return Result.success(problems);
    }
    @GetMapping("/listUserInfo")
    public Result listUserInfo(Long uId) {
        List<UserInfo> userInfos = service.listUserInfo(uId);
        for (UserInfo userInfo :userInfos) {
            userInfo.setCTime(DateFormat.formatDate(userInfo.getCreateTime()));
        }
        return Result.success(userInfos);
    }
}
