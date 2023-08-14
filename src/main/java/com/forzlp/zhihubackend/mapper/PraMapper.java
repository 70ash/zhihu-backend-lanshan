package com.forzlp.zhihubackend.mapper;

import com.forzlp.zhihubackend.pojo.Article;
import com.forzlp.zhihubackend.pojo.Pra;
import com.forzlp.zhihubackend.pojo.Problem;
import com.forzlp.zhihubackend.pojo.UserPra;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zlp
 * @date 2023/8/4 16:59
 */
@Mapper
public interface PraMapper{

    Long getLastId();

    int praiseArt(Long aId);

    int praisePro(Long pId);

    int savePra(UserPra userPra);

    Problem getById(Long pId);
}




