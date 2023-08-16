package com.forzlp.zhihubackend.service.impl;

import com.forzlp.zhihubackend.mapper.UserCollectMapper;
import com.forzlp.zhihubackend.pojo.UserCollect;
import com.forzlp.zhihubackend.service.UserCollectService;
import com.forzlp.zhihubackend.service.UserPraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<Long> getAIdByUId(Long uId) {
        return mapper.getAIdByUId(uId);
    }

    @Override
    public int delById(Long uId, Long aId) {
        return mapper.delById(uId,aId);
    }
}




