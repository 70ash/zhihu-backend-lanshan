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
 * @date 2023/8/4 16:55
 */
@TableName(value ="zh_user")
@Data
public class User implements Serializable {

    private Long uId;
    private String name;
    private String password;
    private String avatar;
    private Date createTime;
    private String personalPro;
    private String email;
    // 关注的人数
    private int followCount;
    // 被关注的人数
    private int followedCount;
    private int isMember;

    public User(Long uId, String name, String password, String avatar, Date createTime, String personalPro, String email) {
        this.uId = uId;
        this.name = name;
        this.password = password;
        this.avatar = avatar;
        this.createTime = createTime;
        this.personalPro = personalPro;
        this.email = email;
    }


}