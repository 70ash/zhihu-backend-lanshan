package com.forzlp.zhihubackend.service;

import com.forzlp.zhihubackend.pojo.Page;
import com.forzlp.zhihubackend.pojo.Problem;
import com.baomidou.mybatisplus.extension.service.IService;
import com.forzlp.zhihubackend.pojo.UserPra;

import java.util.List;

/**
 * @author zlp
 * @date 2023/8/4 20:10
 */
public interface ProblemService{

    int saveQuestion(Problem problem);

    int delProblemById(Long id);

    Problem getProblemById(Long id);

    Long getLastId();

    int collect(Long pId);

    int countBySearch(String search);

    List<Problem> listBySearch(Page page);

    int praise(int praCount,Long id);
}
