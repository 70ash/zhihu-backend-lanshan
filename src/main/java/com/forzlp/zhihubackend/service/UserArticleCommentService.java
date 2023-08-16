package com.forzlp.zhihubackend.service;

import com.forzlp.zhihubackend.pojo.UserArticleComment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author zlp
 * @date 2023/8/6 21:26
 */
public interface UserArticleCommentService{

    int save(UserArticleComment userArticleComment);

    List<Long> getCIdByAId(Long aId);

}
