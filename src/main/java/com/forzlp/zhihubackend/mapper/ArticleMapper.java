package com.forzlp.zhihubackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.forzlp.zhihubackend.pojo.Article;
import com.forzlp.zhihubackend.pojo.Page;
import com.forzlp.zhihubackend.pojo.Problem;
import com.forzlp.zhihubackend.pojo.UserPra;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @author zlp
 * @date 2023/8/5 22:14
 */
@Mapper
public interface ArticleMapper{

    int saveArticle(Article article);

    Long getLastId();

    int collect(int colCount, Long aId);

    List<Article> listBySearch(Page page);

    int praise(int praCount,Long aId);

    Article getById(Long id);

    List<Article> listHot();
}




