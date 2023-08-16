package com.forzlp.zhihubackend.mapper;

import com.forzlp.zhihubackend.pojo.UserArticleComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author zlp
 * @date 2023/8/6 22:11
 */
@Mapper
public interface UserArticleCommentMapper{

    int save(UserArticleComment userArticleComment);

    List<Long> getCIdByAId(Long aId);

}




