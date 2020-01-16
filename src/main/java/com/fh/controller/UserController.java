package com.fh.controller;

import com.fh.entity.po.User;
import com.fh.service.UserService;
import com.fh.util.AliYunUtil;
import com.fh.util.RandomCode;
import com.fh.util.RedisPools;
import com.fh.util.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/queryUserByPhone")
    public Map queryUserByPhone(String phone){
        Map map = new HashMap();
        map.put("valid",userService.queryUserByPhone(phone));
        return map;
    }

    @RequestMapping("/sendCode")
    public ResponseData sendCode(String phone){
        String code = RandomCode.getRandomCode(4);
        boolean isSuccess = AliYunUtil.sendSms(phone, code);
        Jedis jedis = RedisPools.getJedis();
        jedis.setex("code_"+phone,5*60,code);
        RedisPools.returnjedis(jedis);
        return ResponseData.success(isSuccess);
    }

    @RequestMapping("/addUser")
    public ResponseData addUser(User user,String code){
        Map map = userService.addUser(user,code);
        return ResponseData.success(map);
    }

}
