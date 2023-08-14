package com.forzlp.zhihubackend.service.impl;

import com.forzlp.zhihubackend.mapper.UserCollectMapper;
import com.forzlp.zhihubackend.pojo.UserCollect;
import com.forzlp.zhihubackend.service.UserCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zlp
 * @date 2023/8/7 18:26
 */
@Service
public class UserCollectServiceImpl implements UserCollectService{
    @Autowired
    private UserCollectMapper mapper;
    @Override
    public int saveArt(UserCollect userCollect) {
        return mapper.saveArt(userCollect);
    }
}




