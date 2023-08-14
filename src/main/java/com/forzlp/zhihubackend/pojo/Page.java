package com.forzlp.zhihubackend.pojo;

import lombok.Data;

/**
 * @author zlp
 * @date 2023/8/8 20:33
 */
@Data
public class Page {
    private String search;
    private int limit;
    private int size;
    public Page(String search, int limit, int size) {
        this.search = search;
        this.limit = limit;
        this.size = size;
    }
}
