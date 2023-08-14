package com.forzlp.zhihubackend.mapper;

import com.forzlp.zhihubackend.pojo.Follow;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author zlp
 * @date 2023/8/9 21:22
 */
@Mapper
public interface FollowMapper{

    int follow(Follow follow);

    List<Long> getById(Long followId);

    List<Long> getFansIdById(Long id);
}




