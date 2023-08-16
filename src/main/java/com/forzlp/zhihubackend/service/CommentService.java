package com.forzlp.zhihubackend.service;

import com.forzlp.zhihubackend.pojo.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author zlp
 * @date 2023/8/6 21:09
 */
public interface CommentService{

    int save(Comment comment);

    Long getLastId();

    int praise(Long id);

    int commentPro(int repCount, Long pId);

    int commentArt(int repCount, Long aId);

    Comment getById(Long aLong);
}
