package com.itheima.service.impl;

import com.itheima.domain.Pages;
import com.itheima.domain.User;
import com.itheima.mapper.UserMapper;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> findAll() {
        List<User> all = userMapper.findAll();
        return all;
    }

    @Override
    public void saveUser(User user) {
        userMapper.saveUser(user);
    }

    @Override
    public User findUser(User user) {
        return userMapper.findUser(user);
    }

    @Override
    public void delete(User user) {
        userMapper.delete(user);
    }

    @Override
    public User findOne(User user) {
      return   userMapper.findOne(user);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public int findCount(Pages pages) {
        if(pages.getUser()!=null){

            if(pages.getUser().getUsername()!=null && pages.getUser().getUsername().trim().length()==0){
                pages.getUser().setUsername(null);
            }
            if(pages.getUser().getAddress()!=null &&pages.getUser().getAddress().trim().length()==0){
                pages.getUser().setAddress(null);
            }
            if(pages.getUser().getEmail() !=null && pages.getUser().getEmail().trim().length()==0){
                pages.getUser().setEmail(null);
            }
        }
        return  userMapper.findCount(pages);
    }

    @Override
    public List<User> findList(Pages pages) {
        if(pages.getUser()!=null){

            if(pages.getUser().getUsername()!=null && pages.getUser().getUsername().trim().length()==0){
                pages.getUser().setUsername(null);
            }
            if(pages.getUser().getAddress()!=null &&pages.getUser().getAddress().trim().length()==0){
                pages.getUser().setAddress(null);
            }
            if(pages.getUser().getEmail() !=null && pages.getUser().getEmail().trim().length()==0){
                pages.getUser().setEmail(null);
            }
        }
        return userMapper.findList(pages);
    }

    @Override
    public void delSelect(String id) {
        int i = Integer.parseInt(id);
        userMapper.delSelect(i);
    }
}
