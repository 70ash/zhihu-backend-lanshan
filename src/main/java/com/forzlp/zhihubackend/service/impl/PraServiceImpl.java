package com.forzlp.zhihubackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.forzlp.zhihubackend.pojo.Article;
import com.forzlp.zhihubackend.pojo.Pra;
import com.forzlp.zhihubackend.pojo.Problem;
import com.forzlp.zhihubackend.pojo.UserPra;
import com.forzlp.zhihubackend.service.PraService;
import com.forzlp.zhihubackend.mapper.PraMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zlp
 * @date 2023/8/4 17:14
 */
@Service
public class PraServiceImpl implements PraService{
    @Autowired
    private PraMapper mapper;
    @Override
    public Long getLastId() {
        return mapper.getLastId();
    }

    @Override
    public int praiseArt(Long aId) {
        return mapper.praiseArt(aId);
    }

    @Override
    public int praisePro(Long pId) {
        return mapper.praisePro(pId);
    }

    @Override
    public int savePra(UserPra userPra) {
        return mapper.savePra(userPra);
    }

    @Override
    public Problem getById(Long pId) {
        return mapper.getById(pId);
    }

    @Override
    public Long getAIdById(Long praId) {
        return mapper.getAIdById(praId);
    }

    @Override
    public List<Long> getPraIdByAId(Long aId) {
        return mapper.getPraIdByAId(aId);
    }

    @Override
    public int delPraById(Long praId) {
        return mapper.delPraById(praId);
    }

    @Override
    public Long getpIdById(Long aLong) {
        return mapper.getpIdById(aLong);
    }
}




