package com.forzlp.zhihubackend.service;

import com.forzlp.zhihubackend.pojo.BroHistory;
import com.forzlp.zhihubackend.pojo.User;
import com.forzlp.zhihubackend.pojo.UserInfo;
import org.springframework.stereotype.Service;

/**
 * @author zlp
 * @date 2023/8/4 17:18
 */

public interface UserService{
    User getUserByNameAndPassword(String name, String password);

    int saveUser(User user);

    int updateUser(User user);

    int member(Long id);

    User getById(Long uId);

    int info(UserInfo userInfo);

    int saveHistory(BroHistory broHistory);
}
