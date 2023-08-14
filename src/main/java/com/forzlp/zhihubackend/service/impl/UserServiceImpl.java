package com.forzlp.zhihubackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.forzlp.zhihubackend.pojo.BroHistory;
import com.forzlp.zhihubackend.pojo.User;
import com.forzlp.zhihubackend.pojo.UserInfo;
import com.forzlp.zhihubackend.service.UserService;
import com.forzlp.zhihubackend.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @author zlp
 * @date 2023/8/4 17:25
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper mapper;
    @Override
    public User getUserByNameAndPassword(String name, String password) {
        User user = mapper.getUserByNameAndPassword(name, password);
        return user;
    }

    @Override
    public int saveUser(User user) {
        return mapper.saveUser(user);
    }

    @Override
    public int updateUser(User user) {
        return mapper.updateUser(user);
    }

    @Override
    public int member(Long id) {
        return mapper.member(id);
    }

    @Override
    public User getById(Long uId) {
        return mapper.getById(uId);
    }

    @Override
    public int info(UserInfo userInfo) {
        return mapper.info(userInfo);
    }

    @Override
    public int saveHistory(BroHistory broHistory) {
        return mapper.saveHistory(broHistory);
    }
}




