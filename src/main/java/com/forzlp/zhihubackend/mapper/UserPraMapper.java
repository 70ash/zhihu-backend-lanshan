package com.forzlp.zhihubackend.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author zlp
 * @date 2023/8/15 11:27
 */
@Mapper
public interface UserPraMapper {
    List<Long> getById(Long uId);

    int delById(Long praId,Long uId);

    Long getUIdByPraId(Long aLong);
}
