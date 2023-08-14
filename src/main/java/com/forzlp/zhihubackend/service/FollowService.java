package com.forzlp.zhihubackend.service;


import com.forzlp.zhihubackend.pojo.Follow;

import java.util.List;

/**
 * @author zlp
 * @date 2023/8/9 21:15
 */
public interface FollowService {

    int follow(Follow follow);

    List<Long> getById(Long followId);

    List<Long> getFansIdById(Long id);
}
