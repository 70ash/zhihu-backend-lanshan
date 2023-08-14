package com.forzlp.zhihubackend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author zlp
 * @date 2023/8/9 23:23
 */
@TableName("zh_user_pra")
@Data
public class UserPra {
    private Long id;
    private Long uId;
    private Long praId;

}
