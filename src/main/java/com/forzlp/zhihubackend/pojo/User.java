package com.forzlp.zhihubackend.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;


import lombok.Data;

/**
 * @author zlp
 * @date 2023/8/4 16:55
 */
@TableName(value ="zh_user")
@Data
public class User implements Serializable{
    @TableId
    private Long id;
    private String name;
    private String password;
    private String avatar;
    private Date createTime;
    private String cTime;
    private String personalPro;
    private String email;
    // 关注的人数
    private int followCount;
    // 被关注的人数
    private int followedCount;
    private int isMember;

}