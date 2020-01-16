package com.fh.util;

import java.util.Random;

public class RandomCode {

   //length  验证码的位数
    public static String getRandomCode(int length){
        Random random = new Random();
        String randomCode = random.nextInt((int) Math.pow(10,length)) +"";
        int randLength = randomCode.length();
        if(randLength<length){
            for(int i=1; i<=length-randLength; i++)
                randomCode = "0" + randomCode ;
        }
        return randomCode;
    }

}
