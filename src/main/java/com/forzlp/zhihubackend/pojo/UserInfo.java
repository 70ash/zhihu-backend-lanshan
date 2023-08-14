package com.forzlp.zhihubackend.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author zlp
 * @date 2023/8/11 21:26
 */
@TableName(value ="zh_user_info")
@Data
public class UserInfo {
    private Long id;
    private Long uId;
    private String msg;
    private Date createTime;

    public UserInfo() {
    }

    public UserInfo(Long uId, String msg) {
        this.uId = uId;
        this.msg = msg;
    }
}
