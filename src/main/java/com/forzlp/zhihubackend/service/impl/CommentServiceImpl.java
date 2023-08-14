package com.forzlp.zhihubackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.forzlp.zhihubackend.pojo.Comment;
import com.forzlp.zhihubackend.service.CommentService;
import com.forzlp.zhihubackend.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zlp
 * @date 2023/8/6 21:10
 */
@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentMapper mapper;
    @Override
    public int save(Comment comment) {
        return mapper.save(comment);
    }

    @Override
    public Long getLastId() {
        return mapper.getLastId();
    }

    @Override
    public int praise(Long id) {
        return mapper.praise(id);
    }
}




