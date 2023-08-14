package com.forzlp.zhihubackend.mapper;

import com.forzlp.zhihubackend.pojo.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zlp
 * @date 2023/8/6 21:12
 */
@Mapper
public interface CommentMapper{

    int save(Comment comment);

    Long getLastId();

    int praise(Long id);
}




