package com.forzlp.zhihubackend.controller;

import com.forzlp.zhihubackend.pojo.*;
import com.forzlp.zhihubackend.service.*;
import com.forzlp.zhihubackend.utils.DateFormat;
import com.forzlp.zhihubackend.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zlp
 * @date 2023/8/6 20:59
 * 回复问题,回复文章,点赞评论
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    private CommentService service;
    private ProblemService problemService;
    private ArticleService articleService;
    private UserProblemCommentService userProblemCommentService;
    private UserArticleCommentService userArticleCommentService;
    @Autowired
    public CommentController(CommentService service, UserProblemCommentService userProblemCommentService, UserArticleCommentService userArticleCommentService,ProblemService problemService,ArticleService articleService) {
        this.service = service;
        this.userProblemCommentService = userProblemCommentService;
        this.userArticleCommentService = userArticleCommentService;
        this.problemService = problemService;
        this.articleService = articleService;
    }

    /**
     *
     * @param comment 评论对象
     * @param pId 用户问题的id
     * @return
     * 评论问题
     */
    @PostMapping("/replyPro")
    public Result replyPro(@RequestBody Comment comment,Long pId  ) {
        int i = service.save(comment);
        Problem problem = problemService.getProblemById(pId);
        int repCount = problem.getRepCount()+1;
        int k = service.commentPro(repCount,pId);
        if(i+k == 2) {
            // 关联评论和用户问题
            Long c_id = service.getLastId();
            UserProblemComment userProblemComment = new UserProblemComment();
            userProblemComment.setCId(c_id);
            userProblemComment.setPId(pId);
            int j = userProblemCommentService.save(userProblemComment);
            if(j == 1) {
                return Result.success("回复评论成功");
            }else {
                return Result.fail();
            }

        }else {
            return Result.fail();
        }
    }
    @PostMapping("/replyArt")
    public Result replyArt(@RequestBody Comment comment,Long aId) {
        int i = service.save(comment);
        Article article = articleService.getById(aId);
        int repCount = article.getRepCount()+1;
        int k = service.commentArt(repCount,aId);
        if(i+k == 2) {
            // 关联评论和用户问题
            Long cId = service.getLastId();
            UserArticleComment userArticleComment = new UserArticleComment();
            userArticleComment.setCId(cId);
            userArticleComment.setAId(aId);
            int j = userArticleCommentService.save(userArticleComment);
            if(j == 1) {
                return Result.success("评论成功");
            }else {
                return Result.fail();
            }

        }else {
            return Result.fail();
        }
    }

    /**
     * 展示文章评论
     * 根据aid找到cId,根据cId得到评论列表
     * @param aId
     * @return
     */
    @GetMapping("findArtComment")
    public Result findArtComment(Long aId) {
        List<Long> cIds = userArticleCommentService.getCIdByAId(aId);
        ArrayList<Comment> comments = new ArrayList<>();
        for (Long aLong :cIds) {
            Comment comment = service.getById(aLong);
            comment.setCTime(DateFormat.formatDate(comment.getCreateTime()));
            comments.add(comment);
        }
        return Result.success(comments);
    }
    /**
     * 展示文章评论
     * 根据aid找到cId,根据cId得到评论列表
     * @param pId
     * @return
     */
    @GetMapping("findProComment")
    public Result findProComment(Long pId) {
        List<Long> cIds = userProblemCommentService.getCIdByPId(pId);
        ArrayList<Comment> comments = new ArrayList<>();
        for (Long aLong :cIds) {
            Comment comment = service.getById(aLong);
            comment.setCTime(DateFormat.formatDate(comment.getCreateTime()));
            comments.add(comment);
        }
        return Result.success(comments);
    }
}
