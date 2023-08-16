package com.forzlp.zhihubackend.service;

import com.forzlp.zhihubackend.pojo.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.forzlp.zhihubackend.pojo.Page;
import com.forzlp.zhihubackend.pojo.Problem;
import com.forzlp.zhihubackend.pojo.UserPra;

import java.util.List;

/**
 * @author zlp
 * @date 2023/8/5 22:08
 */
public interface ArticleService {

    int saveArticle(Article article);

    Long getLastId();

    int collect(int colCount,Long aId);

    List<Article> listBySearch(Page page);

    int praise(int praCount,Long aId);

    Article getById(Long id);
}
