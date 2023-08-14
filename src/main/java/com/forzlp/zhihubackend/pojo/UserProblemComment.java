package com.forzlp.zhihubackend.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @author zlp
 * @date 2023/8/6 21:20
 */
@TableName(value ="zh_user_problem_comment")
@Data
public class UserProblemComment implements Serializable {
    private Long id;
    private Long pId;
    private Long cId;

}