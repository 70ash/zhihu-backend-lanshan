package com.forzlp.zhihubackend.mapper;

import com.forzlp.zhihubackend.pojo.UserProblemComment;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zlp
 * @date 2023/8/6 21:44
 */
@Mapper
public interface UserProblemCommentMapper {

    int save(UserProblemComment userProblemComment);
}




