package com.forzlp.zhihubackend.service.impl;

import com.forzlp.zhihubackend.mapper.UserProblemCommentMapper;
import com.forzlp.zhihubackend.pojo.Comment;
import com.forzlp.zhihubackend.pojo.UserProblemComment;
import com.forzlp.zhihubackend.service.UserProblemCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zlp
 * @date 2023/8/6 21:23
 */
@Service
public class UserProblemProblemCommentServiceImpl implements UserProblemCommentService {
    @Autowired
    private UserProblemCommentMapper mapper;
    @Override
    public int save(UserProblemComment userProblemComment) {
        return mapper.save(userProblemComment);
    }

    @Override
    public List<Long> getCIdByPId(Long pId) {
        return mapper.getCIdByPId(pId);
    }
}




