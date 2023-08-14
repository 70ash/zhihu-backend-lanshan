package com.forzlp.zhihubackend.pojo;

import org.omg.CORBA.PRIVATE_MEMBER;

import java.util.Date;

/**
 * 浏览记录
 * @author zlp
 * @date 2023/8/11 22:35
 */
public class BroHistory {
    private Long id;
    private Long uId;
    private Long typeId;
    private Date broTime;
    private Long broId;
    public BroHistory(Long uId, Long typeId,Long broId) {
        this.uId = uId;
        this.typeId = typeId;
        this.broId = broId;
    }
}
