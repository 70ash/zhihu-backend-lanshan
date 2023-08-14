package com.forzlp.zhihubackend.controller;

import com.forzlp.zhihubackend.pojo.*;
import com.forzlp.zhihubackend.service.*;
import com.forzlp.zhihubackend.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    public ArticleController(ArticleService service,UserService userService,PraService praService,FollowService followService) {
        this.service = service;
        this.userService = userService;
        this.praService = praService;
        this.followService = followService;
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
            return Result.success(article);
        }else {
            return Result.fail("没有找到文章");
        }
    }
    @PostMapping("/pubArticle")
    public Result<Article> pubArticle(@RequestBody Article article) {
        int i = service.saveArticle(article);
        if(i == 1) {
            log.info("发布问题成功");
            return Result.success(article);
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
    public Result listArt(@RequestBody User user, String search, int limit, int size) {
        Page page = new Page(search,limit,size);
        page.setLimit((limit-1)*3);
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
        if(i+k == 2) {
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
