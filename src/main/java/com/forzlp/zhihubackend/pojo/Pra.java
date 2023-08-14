package com.forzlp.zhihubackend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
/**
 * @author zlp
 * @date 2023/8/4 16:53
 */
@TableName(value ="zh_pra")
@Data
public class Pra implements Serializable {
    private Long praId;
    private Integer article;
    private Integer problem;
    private Integer comment;
    public Pra(Long praId, Integer article, Integer problem, Integer comment) {
        this.praId = praId;
        this.article = article;
        this.problem = problem;
        this.comment = comment;
    }
}