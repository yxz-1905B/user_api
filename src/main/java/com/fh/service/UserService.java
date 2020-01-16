package com.fh.service;

import com.fh.entity.po.User;

import java.util.Map;

public interface UserService {
    Boolean queryUserByPhone(String phone);

    Map addUser(User user, String code);
}
