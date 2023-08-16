package com.forzlp.zhihubackend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @author zlp
 * @date 2023/8/6 21:05
 */
@TableName(value ="zh_comment")
@Data
public class Comment implements Serializable {
    private Long id;

    private Date createTime;
    private String cTime;
    private Integer praCount;
    // 作者id
    private Long authorId;
    private String content;
}