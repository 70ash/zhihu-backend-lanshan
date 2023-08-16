package com.forzlp.zhihubackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.forzlp.zhihubackend.pojo.UserArticleComment;
import com.forzlp.zhihubackend.service.UserArticleCommentService;
import com.forzlp.zhihubackend.mapper.UserArticleCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zlp
 * @date 2023/8/6 21:36
 */
@Service
public class UserArticleCommentServiceImpl implements UserArticleCommentService{
    @Autowired
    private UserArticleCommentMapper mapper;
    @Override
    public int save(UserArticleComment userArticleComment) {
        return mapper.save(userArticleComment);
    }

    @Override
    public List<Long> getCIdByAId(Long aId) {
        return mapper.getCIdByAId(aId);
    }
}




