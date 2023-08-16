package com.forzlp.zhihubackend.service;

import java.util.List;

/**
 * @author zlp
 * @date 2023/8/15 11:25
 */
public interface UserPraService {
    List<Long> getById(Long uId);

    int delById(Long praId,Long uId);

    Long getUIdByPraId(Long aLong);
}
