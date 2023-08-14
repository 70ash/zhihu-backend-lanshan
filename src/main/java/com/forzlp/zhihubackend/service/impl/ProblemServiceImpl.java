package com.forzlp.zhihubackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.forzlp.zhihubackend.pojo.Page;
import com.forzlp.zhihubackend.pojo.Problem;
import com.forzlp.zhihubackend.pojo.UserPra;
import com.forzlp.zhihubackend.service.ProblemService;
import com.forzlp.zhihubackend.mapper.ProblemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zlp
 * @date 2023/8/4 20:13
 */
@Service
public class ProblemServiceImpl implements ProblemService {
    private final ProblemMapper mapper;

    @Autowired
    public ProblemServiceImpl(ProblemMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public int saveQuestion(Problem problem) {
        return mapper.saveQuestion(problem);
    }

    @Override
    public int delProblemById(Long id) {
        return mapper.delProblemById(id);
    }

    @Override
    public Problem getProblemById(Long id) {
        return mapper.getProblemById(id);
    }

    @Override
    public Long getLastId() {
        return mapper.getLastId();
    }

    @Override
    public int collect(Long pId) {
        return mapper.collect(pId);
    }

    @Override
    public int countBySearch(String search) {
        return mapper.countBySearch(search);
    }

    @Override
    public List<Problem> listBySearch(Page page) {
        return mapper.listBySearch(page);
    }

    @Override
    public int praise(int praCount,Long id) {
        return mapper.praise(praCount,id);
    }

}




