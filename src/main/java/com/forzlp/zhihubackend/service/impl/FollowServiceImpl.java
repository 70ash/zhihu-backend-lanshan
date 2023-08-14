package com.forzlp.zhihubackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.forzlp.zhihubackend.pojo.Follow;
import com.forzlp.zhihubackend.service.FollowService;
import com.forzlp.zhihubackend.mapper.FollowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author zlp
 * @date 2023/8/9 21:19
 */
@Service
public class FollowServiceImpl implements FollowService{
    @Autowired
    private FollowMapper mapper;
    @Override
    public int follow(Follow follow) {
        return mapper.follow(follow);
    }

    @Override
    public List<Long> getById(Long followId) {
        return mapper.getById(followId);
    }

    @Override
    public List<Long> getFansIdById(Long id) {
        return mapper.getFansIdById(id);
    }
}




