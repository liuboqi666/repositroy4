package com.itheima.service;


import com.itheima.domain.Pages;
import com.itheima.domain.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    void  saveUser(User user);

    User findUser(User user);

    void delete(User user);

    User findOne(User user);

    void update(User user);

    int findCount(Pages pages);

    List<User> findList(Pages pages);

    void delSelect(String id);
}
