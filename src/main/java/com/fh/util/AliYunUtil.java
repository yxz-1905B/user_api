package com.fh.util;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

import java.util.HashMap;
import java.util.Map;

public class AliYunUtil {


    public static boolean sendSms(String phone,String code){
        Map codeMap=new HashMap();
        codeMap.put("code",code);
        boolean rs=false;
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou","LTAI4FtFYnRaykEYHv9UTcmQ", "uotJFzLERPabOZrKFEJRu4WYddn1gf");
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "飞狐商城服务平台");
        request.putQueryParameter("TemplateCode", "SMS_180961327");
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(codeMap));
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println("阿里云发送信息结果："+response.getData());
            JSONObject object=JSONObject.parseObject(response.getData());
            if(object.get("Code")!=null&&"OK".equals(object.get("Code"))){
                rs=true;
            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return rs;
    }
}
