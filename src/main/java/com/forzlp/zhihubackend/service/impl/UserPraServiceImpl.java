package com.forzlp.zhihubackend.service.impl;

import com.forzlp.zhihubackend.mapper.UserPraMapper;
import com.forzlp.zhihubackend.pojo.UserPra;
import com.forzlp.zhihubackend.service.UserPraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zlp
 * @date 2023/8/15 11:25
 */
@Service
public class UserPraServiceImpl implements UserPraService {
    @Autowired
    private UserPraMapper mapper;
    @Override
    public List<Long> getById(Long uId) {
        return mapper.getById(uId);
    }

    @Override
    public int delById(Long praId,Long uId) {
        return mapper.delById(praId,uId);
    }

    @Override
    public Long getUIdByPraId(Long aLong) {
        return mapper.getUIdByPraId(aLong);
    }
}
