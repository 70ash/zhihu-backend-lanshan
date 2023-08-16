package com.forzlp.zhihubackend.service;

import com.forzlp.zhihubackend.pojo.Comment;
import com.forzlp.zhihubackend.pojo.UserProblemComment;

import java.util.List;

/**
 * @author zlp
 * @date 2023/8/6 21:22
 */
public interface UserProblemCommentService {

    int save(UserProblemComment userProblemComment);

    List<Long> getCIdByPId(Long pId);
}
