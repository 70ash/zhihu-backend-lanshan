package com.forzlp.zhihubackend.mapper;

import com.forzlp.zhihubackend.pojo.Page;
import com.forzlp.zhihubackend.pojo.Problem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.forzlp.zhihubackend.pojo.UserPra;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 * @author zlp
 * @date 2023/8/4 19:56
 */
@Mapper
public interface ProblemMapper{

    int saveQuestion(Problem problem);

    int delProblemById(Long id);

    Problem getProblemById(Long id);

    Long getLastId();

    int collect(Long pId);

    int countBySearch(String search);

    List<Problem> listBySearch(Page page);

    int praise(int praCount,Long id);

}




