package com.fh.dao;

import com.fh.entity.po.User;

import java.util.List;

public interface UserDao {

    User queryUserByPhone(String phone);

    void addUser(User user);
}
