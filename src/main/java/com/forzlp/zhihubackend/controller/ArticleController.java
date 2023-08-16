package com.forzlp.zhihubackend.controller;

import com.forzlp.zhihubackend.pojo.*;
import com.forzlp.zhihubackend.service.*;
import com.forzlp.zhihubackend.utils.DateFormat;
import com.forzlp.zhihubackend.utils.Result;
import com.mysql.cj.log.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zlp
 * @date 2023/8/5 22:05
 * 发布文章,查询文章,点赞文章
 */
@Slf4j
@RestController
@RequestMapping("/article")
public class ArticleController {
    private ArticleService service;
    private UserService userService;
    private PraService praService;
    private FollowService followService;
    private UserPraService userPraService;
    @Autowired
    public ArticleController(ArticleService service,UserService userService,PraService praService,FollowService followService,UserPraService userPraService) {
        this.service = service;
        this.userService = userService;
        this.praService = praService;
        this.followService = followService;
        this.userPraService = userPraService;
    }

    @GetMapping("/findArticle")
        public Result findArticle(Long uId, Long aId){
        User user = userService.getById(uId);
        Article article = service.getById(aId);
        if(article != null) {
            int isMember = user.getIsMember();
            // 根据是否为会员完全展示
            if(isMember == 0){
                article.setFullDisplay(0);
            }else {
                article.setFullDisplay(1);
            }
            BroHistory broHistory = new BroHistory(uId, 1L, aId);
            int i = userService.saveHistory(broHistory);
            article.setCTime(DateFormat.formatDate(article.getCreateTime()));
            return Result.success(article);
        }else {
            return Result.fail("没有找到文章");
        }
    }
    @PostMapping("/pubArticle")
    @Transactional
    public Result<Article> pubArticle(@RequestBody Article article) {
        int i = service.saveArticle(article);
        // 发送通知
        Long id = article.getAuthorId();
        List<Long> followIds = followService.getFansIdById(id);
        String name = userService.getById(id).getName();
        for (Long aLong :followIds) {
            UserInfo userInfo = new UserInfo(aLong, "你的关注"+ name + "发表了文章《"+article.getTitle() + "》");
            int j = userService.info(userInfo);
            if(j == 1) log.info("发送通知成功");
        }

        Long lastId = service.getLastId();
        Article article1 = service.getById(lastId);
        article1.setCTime(DateFormat.formatDate(article1.getCreateTime()));
        if(i == 1) {
            log.info("发布问题成功");
            return Result.success(article1);
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
        @GetMapping("/listArt")
    public Result listArt(String search, int cur, int size) {
        Page page = new Page(search,cur,size);
        page.setLimit((cur-1)*3);
        List<Article> list = service.listBySearch(page);
        for (Article article :list) {
            System.out.println(article);
        }
        return Result.success(list);
    }

    /**
     * 点赞文章
     * @return
     * 1.增加点赞量
     * 2.关联用户和文章
     * 使用事务保障安全性
     */
    @Transactional
    @PutMapping("/praiseArt")
    public Result praiseArt(Long uId,Long aId) {
        // 根据id查询文章
        Article article = service.getById(aId);
        article.setCTime(DateFormat.formatDate(article.getCreateTime()));
        // 查询是否点赞过这篇文章(不能重复点赞)
        // 查询用户点赞过的文章
        List<Long> praIds = userPraService.getById(uId);
        for (Long aLong :praIds) {
            Long aIdById = praService.getAIdById(aLong);
            if(aIdById == aId) return Result.fail("你已经点赞过这篇文章了");
        }

        // 查询点赞的人是不是作者的关注.
        List<Long> followedIds = new ArrayList<>();
        // 作者id
        Long followId = article.getAuthorId();
        System.out.println("作者id"+followId);
        // 根据作者id查询作者的关注
        followedIds = followService.getById(followId);
        System.out.println("followedIds");
        for (Long aLong :followedIds) {
            if (uId == aLong) {
                String name = userService.getById(aLong).getName();
                // 发送消息
                UserInfo userInfo = new UserInfo(followId, "你的关注"+ name + "给你的文章" + article.getTitle() + "点赞了");
                int i = userService.info(userInfo);
                if(i == 1) log.info("发送通知成功");
                break;
            }
        }
        System.out.println("uId"+uId);
        int praCount = article.getPraCount()+1;
        int i = service.praise(praCount,aId);
        int k = praService.praiseArt(aId);
        article.setPraCount(praCount);
        if(i+k == 2) {
            Long praId = praService.getLastId();
            UserPra userPra = new UserPra();
            userPra.setUId(uId);
            userPra.setPraId(praId);
            int j = praService.savePra(userPra);
            if(j == 1) {
                return Result.success(article);
            }else {
                return Result.fail();
            }
        }else {
            return Result.fail();
        }
    }

    /**
     * 根据aid搜索文章,给文章的praCount减1,
     * 根据aid删除文章点赞数据
     * @param uId
     * @param aId
     * @return
     */
    @Transactional
    @PutMapping("cancelPra")
    public Result cancelPra(Long uId,Long aId){
        Article article = service.getById(aId);
        int praCount = article.getPraCount()-1;
        int i = service.praise(praCount,aId);
        List<Long> praIds = praService.getPraIdByAId(aId);
        for (Long aLong :praIds) {
            Long getUId = userPraService.getUIdByPraId(aLong);
            if(getUId == uId){
                praService.delPraById(aLong);
                userPraService.delById(aLong,uId);
            }
        }
        return Result.success("取消点赞成功");
    }
}
