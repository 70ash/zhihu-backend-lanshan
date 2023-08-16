package com.forzlp.zhihubackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.forzlp.zhihubackend.pojo.Article;
import com.forzlp.zhihubackend.pojo.Page;
import com.forzlp.zhihubackend.pojo.Problem;
import com.forzlp.zhihubackend.pojo.UserPra;
import com.forzlp.zhihubackend.service.ArticleService;
import com.forzlp.zhihubackend.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zlp
 * @date 2023/8/5 22:11
 */
@Service
public class ArticleServiceImpl implements ArticleService{
    @Autowired
    private ArticleMapper mapper;
    @Override
    public int saveArticle(Article article) {
        return mapper.saveArticle(article);
    }

    @Override
    public Long getLastId() {
        return mapper.getLastId();
    }

    @Override
    public int collect(int colCount, Long aId) {
        return mapper.collect(colCount,aId);
    }

    @Override
    public List<Article> listBySearch(Page page) {
        return mapper.listBySearch(page);
    }

    @Override
    public int praise(int praCount,Long aId) {
        return mapper.praise(praCount,aId);
    }

    @Override
    public Article getById(Long id) {
        return mapper.getById(id);
    }

}




