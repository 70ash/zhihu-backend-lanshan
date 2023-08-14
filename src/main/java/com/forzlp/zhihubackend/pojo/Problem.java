package com.forzlp.zhihubackend.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @author zlp
 * @date 2023/8/4 19:53
 */
@TableName(value ="zh_problem")
@Data
public class Problem implements Serializable {
    private Long id;
    private String title;
    private String content;
    private Date createTime;
    // 评论数量
    private int repCount;
    // 点赞数量
    private int praCount;
    // 收藏数量
    private int colCount;
    // 根据会员是否展示全部
    private int fullDisplay;
    private Long authorId;
    // 回答还是提出
    // 0代表提出,1代表回答
    private int askReply;

}