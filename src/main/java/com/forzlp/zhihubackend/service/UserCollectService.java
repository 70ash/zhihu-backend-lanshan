package com.forzlp.zhihubackend.service;


import com.forzlp.zhihubackend.pojo.UserCollect;

import java.util.List;

/**
 * @author zlp
 * @date 2023/8/7 18:18
 */
public interface UserCollectService{

    int saveArt(UserCollect userCollect);

    List<Long> getAIdByUId(Long uId);

    int delById(Long uId, Long aId);
}
