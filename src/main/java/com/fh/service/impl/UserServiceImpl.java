package com.fh.service.impl;

import com.fh.dao.UserDao;
import com.fh.entity.po.User;
import com.fh.service.UserService;
import com.fh.util.MD5Util;
import com.fh.util.RedisPools;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public Boolean queryUserByPhone(String phone) {
        Boolean flag = false;
        User user = userDao.queryUserByPhone(phone);
        if(user==null){
            flag = true;
        }
        return flag;
    }

    @Override
    public Map addUser(User user, String code) {
        Map map = new HashMap();
        Jedis jedis = RedisPools.getJedis();
        String s = jedis.get("code_" + user.getPhone());
        if(s.equals(code)){
            String phone = user.getPhone();
            String password = user.getPassword();
            String md5Pwd = MD5Util.EncoderByMd5(MD5Util.EncoderByMd5(password) + phone);
            user.setPassword(md5Pwd);

            user.setCreateDate(new Date());
            userDao.addUser(user);
            map.put("remark",2);

        }else{ //验证码错误
            map.put("remark",1);
        }
        return map;
    }
}
