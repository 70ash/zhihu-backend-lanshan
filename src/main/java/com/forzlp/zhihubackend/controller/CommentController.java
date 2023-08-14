package com.forzlp.zhihubackend.controller;

import com.forzlp.zhihubackend.pojo.Comment;
import com.forzlp.zhihubackend.pojo.UserArticleComment;
import com.forzlp.zhihubackend.pojo.UserProblemComment;
import com.forzlp.zhihubackend.service.CommentService;
import com.forzlp.zhihubackend.service.UserArticleCommentService;
import com.forzlp.zhihubackend.service.UserProblemCommentService;
import com.forzlp.zhihubackend.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zlp
 * @date 2023/8/6 20:59
 * 回复问题,回复文章,点赞评论
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    private CommentService service;
    private UserProblemCommentService userProblemCommentService;
    private UserArticleCommentService userArticleCommentService;
    @Autowired
    public CommentController(CommentService service, UserProblemCommentService userProblemCommentService, UserArticleCommentService userArticleCommentService) {
        this.service = service;
        this.userProblemCommentService = userProblemCommentService;
        this.userArticleCommentService = userArticleCommentService;
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
        if(i == 1) {
            // 关联评论和用户问题
            Long c_id = service.getLastId();
            UserProblemComment userProblemComment = new UserProblemComment();
            userProblemComment.setCId(c_id);
            userProblemComment.setPId(pId);
            int j = userProblemCommentService.save(userProblemComment);
            if(j == 1) {
                return Result.success();
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
        if(i == 1) {
            // 关联评论和用户问题
            Long cId = service.getLastId();
            UserArticleComment userArticleComment = new UserArticleComment();
            userArticleComment.setCId(cId);
            userArticleComment.setAId(aId);
            int j = userArticleCommentService.save(userArticleComment);
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
