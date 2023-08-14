package com.forzlp.zhihubackend.mapper;

import com.forzlp.zhihubackend.pojo.UserCollect;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zlp
 * @date 2023/8/7 18:28
 */
@Mapper
public interface UserCollectMapper{

    int saveArt(UserCollect userCollect);
}




