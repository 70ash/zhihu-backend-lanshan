package com.forzlp.zhihubackend.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @author zlp
 * @date 2023/8/5 22:05
 */
@TableName(value ="zh_article")
@Data
public class Article implements Serializable {
    private Long id;
    private String title;
    private String content;
    private Date createTime;
    // 展示给前端的时间
    private String cTime;
    private String broTime;
    // 点赞数
    private int praCount;
    // 收藏数
    private int colCount;
    // 回复数
    private int repCount;
    // 根据是否为会员完全展示
    private int fullDisplay;
    // 作者id
    private Long authorId;
}