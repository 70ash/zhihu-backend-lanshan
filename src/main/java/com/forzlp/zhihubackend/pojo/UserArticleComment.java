package com.forzlp.zhihubackend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @author zlp
 * @date 2023/8/6 21:16
 */
@TableName(value ="zh_user_article_comment")
@Data
public class UserArticleComment implements Serializable {
    private Long id;
    private Long aId;
    private Long cId;

}