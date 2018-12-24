package com.itheima.mapper;

import com.itheima.domain.Pages;
import com.itheima.domain.User;

import java.util.List;

public interface UserMapper {
    List<User> findAll();
    User findUser(User user);
    void  saveUser(User user);

    void delete(User user);

    User findOne(User user);

    void update(User user);

    int findCount(Pages pages);

    List<User> findList(Pages pages);

    void delSelect(int i);
}
