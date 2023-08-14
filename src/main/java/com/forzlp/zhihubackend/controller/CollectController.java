package com.forzlp.zhihubackend.controller;

import com.forzlp.zhihubackend.pojo.UserCollect;
import com.forzlp.zhihubackend.service.ArticleService;
import com.forzlp.zhihubackend.service.ProblemService;
import com.forzlp.zhihubackend.service.UserCollectService;
import com.forzlp.zhihubackend.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zlp
 * @date 2023/8/7 18:51
 * 收藏文章,收藏问题
 */
@RestController
@RequestMapping("/collect")
public class CollectController {
    private UserCollectService service;
    private ArticleService articleService;
    private ProblemService problemService;
    @Autowired
    public CollectController(UserCollectService service, ArticleService articleService, ProblemService problemService) {
        this.service = service;
        this.articleService = articleService;
        this.problemService = problemService;
    }

    @PostMapping("collectArt/{uId}/{aId}")
    public Result collectArt(@PathVariable("uId") Long uId,@PathVariable("aId") Long aId) {

        UserCollect userCollect = new UserCollect();
        userCollect.setAId(aId);
        userCollect.setUId(uId);
        int j = service.saveArt(userCollect);
        if(j == 1) {
            return Result.success();
        }else {
            return Result.fail();
        }
    }
    @PostMapping("collectPro/{uId}/{p_id}")
    public Result collectPro(@PathVariable("uId") Long uId,@PathVariable("p_id") Long pId) {
        int i = problemService.collect(pId);
        if(i == 1) {
            UserCollect userCollect = new UserCollect();
            userCollect.setPId(pId);
            userCollect.setUId(uId);
            int j = service.saveArt(userCollect);
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
