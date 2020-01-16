package com.fh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication//启动类  声明启动类
@MapperScan("com.fh.dao")
public class SpringBootStart {
    public static void main(String[] args) {
        //启动服务    启动类的类对象
        SpringApplication.run(SpringBootStart.class,args);
    }

}
