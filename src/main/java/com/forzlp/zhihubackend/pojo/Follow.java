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
 * @date 2023/8/9 21:12
 */
@TableName(value ="zh_follow")
@Data
public class Follow implements Serializable {
    private Long id;

    private Long followId;

    private Long followedId;

    private Date createTime;

}