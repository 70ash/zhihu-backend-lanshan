package com.forzlp.zhihubackend.controller;

import com.forzlp.zhihubackend.pojo.*;
import com.forzlp.zhihubackend.service.FollowService;
import com.forzlp.zhihubackend.service.PraService;
import com.forzlp.zhihubackend.service.ProblemService;
import com.forzlp.zhihubackend.service.UserService;
import com.forzlp.zhihubackend.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * @author zlp
 * @date 2023/8/4 21:53
 * 提出问题和回答问题,查询问题,点赞问题
 */
@RestController
@Slf4j
@RequestMapping("/problem")
public class ProblemController {
    private ProblemService problemService;
    private UserService userService;
    private PraService praService;
    private FollowService followService;
    @Autowired
    public ProblemController(ProblemService problemService,UserService userService,PraService praService,FollowService followService) {
        this.problemService = problemService;
        this.userService = userService;
        this.praService = praService;
        this.followService = followService;
    }

    @GetMapping("/findProblem")
    public Result findProblem(Long uId, Long pId){
        User user = userService.getById(uId);
        System.out.println(user);
        Problem problem = problemService.getProblemById(pId);
        if(problem != null) {
            int isMember = user.getIsMember();
            // 根据是否为会员完全展示
            if(isMember == 0){
                problem.setFullDisplay(0);
               problem.setFullDisplay(1);
            }
            BroHistory broHistory = new BroHistory(uId, 0L, pId);
            int i = userService.saveHistory(broHistory);
            return Result.success(problem);
        }else {
            return Result.fail("没有找到问题");
        }
    }

    /**
     *
     * @param problem 问题
     * @param id 提出或者回答问题的用户的id
     * @return
     */
    @PostMapping("/Question")
    public Result askQuestion(@RequestBody Problem problem) {
        Long id = problem.getAuthorId();
        System.out.println(id);
        // 查询该用户的粉丝id
        List<Long> followIds = followService.getFansIdById(id);
        String name = userService.getById(id).getName();
        int askReply = problem.getAskReply();
        String s = askReply == 0?"提出了问题":"回答了问题";
        // 根据粉丝id给粉丝发通知
        for (Long aLong :followIds) {
            UserInfo userInfo = new UserInfo(aLong, "你的关注"+ name + s + problem.getTitle());
            int i = userService.info(userInfo);
            if(i == 1) log.info("发送通知成功");
        }
        int i = problemService.saveQuestion(problem);
        if(i == 1) {
            log.info("发布问题成功");
            return Result.success();
        }else {
            return Result.fail();
        }
    }

    /**
     *
     * @param search 模糊查询
     * @param limit 当前页码
     * @param size 每页显示的数据量
     * @return
     */
    @GetMapping("/listPro")
    public Result listPro(String search,int limit,int size) {
        Page page = new Page(search,limit,size);
        int i = problemService.countBySearch(page.getSearch());
        System.out.println(i);
        List<Problem> list = problemService.listBySearch(page);
        for (Problem problem :list) {
            System.out.println(list);
        }
        System.out.println(i);
        return Result.success(list);
    }

    /**
     * 点赞问题
     * @return
     */
    @Transactional
    @PutMapping("/praisePro")
    public Result praisePro(Long uId,Long pId) {
        // 根据id查询文章
        Problem problem = praService.getById(pId);
        // 查询点赞的人是不是作者的关注.
        List<Long> followedIds;
        // 作者id
        Long followId = problem.getAuthorId();
        // 根据作者id查询作者的关注
        followedIds = followService.getById(followId);
        for (Long aLong :followedIds) {
            if (uId == aLong) {
                String name = userService.getById(aLong).getName();
                // 发送消息
                UserInfo userInfo = new UserInfo(followId, "你的关注"+ name + "给你的问题" + problem.getTitle() + "点赞了");
                int i = userService.info(userInfo);
                if(i == 1) log.info("发送通知成功");
                break;
            }
        }
        int praCount = problem.getPraCount()+1;
        int i = problemService.praise(praCount,pId);
        int k = praService.praisePro(pId);
        if(i+k == 2) {
            // 关联用户和文章
            Long praId = praService.getLastId();
            UserPra userPra = new UserPra();
            userPra.setUId(uId);
            userPra.setPraId(praId);
            int j = praService.savePra(userPra);
            if(j == 1) {
                return Result.success();
            }else {
                return Result.fail();
            }
        }else {
            return Result.fail();
        }
    }
}
