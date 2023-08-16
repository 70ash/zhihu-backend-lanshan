package com.forzlp.zhihubackend.controller;

import com.forzlp.zhihubackend.pojo.Article;
import com.forzlp.zhihubackend.pojo.UserCollect;
import com.forzlp.zhihubackend.service.ArticleService;
import com.forzlp.zhihubackend.service.ProblemService;
import com.forzlp.zhihubackend.service.UserCollectService;
import com.forzlp.zhihubackend.utils.DateFormat;
import com.forzlp.zhihubackend.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zlp
 * @date 2023/8/7 18:51
 * 收藏文章,收藏问题,展示收藏
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

    @PostMapping("collectArt")
    public Result collectArt(Long uId,Long aId) {
        // 判断用户是否已经收藏过这篇文章
        List<Long> aIdByUId = service.getAIdByUId(uId);
        for (Long aLong :aIdByUId) {
            if(aId == aLong) return Result.fail("你已经收藏过这篇文章了");
        }
        Article article = articleService.getById(aId);
        int colCount = article.getColCount()+1;
        int i = articleService.collect(colCount, aId);
        UserCollect userCollect = new UserCollect();
        userCollect.setAId(aId);
        userCollect.setUId(uId);
        int j = service.saveArt(userCollect);
        if(i+j == 2) {
            article.setCTime(DateFormat.formatDate(article.getCreateTime()));
            return Result.success(article);
        }else {
            return Result.fail();
        }
    }

    /**
     * 根据用户id展示用户收藏的文章
     * @param uId
     * @return
     */
    @GetMapping("listCollectArt")
    public Result listCollectArt(Long uId) {

        List<Long> aIds = service.getAIdByUId(uId);
        List<Article> articles = new ArrayList<>();
        for (Long aLong :aIds) {
            Article article = articleService.getById(aLong);
            article.setCTime(DateFormat.formatDate(article.getCreateTime()));
            articles.add(article);
        }
        return Result.success(articles);
    }

    /**
     *
     * @param uId
     * @param aId
     * @return
     */
    @DeleteMapping("cancelCollectArt")
    public Result cancelCollectArt(Long uId,Long aId) {
        int i = service.delById(uId,aId);
        Article article = articleService.getById(aId);
        int colCount = article.getColCount()-1;
        int j = articleService.collect(colCount,aId);
        if(i+j == 1) {
            return Result.success("取消收藏成功");
        }else {
            return Result.fail("取消收藏失败");
        }
    }
    @PostMapping("collectPro")
    public Result collectPro(Long uId, Long pId) {
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
