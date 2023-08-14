package com.forzlp.zhihubackend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @author zlp
 * @date 2023/8/7 18:16
 */
@TableName(value ="zh_user_collect")
@Data
public class UserCollect implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long uId;
    private Long aId;
    private Long pId;

}