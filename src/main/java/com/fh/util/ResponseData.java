package com.fh.util;

public class ResponseData {
    private Integer code;

    private String info;

    private Object data;

    private ResponseData(){

    }

    public ResponseData(Integer code, String info, Object data){
        this.code=code;
        this.data=data;
        this.info=info;
    }

    public static ResponseData success(Object data){
        return  new ResponseData(200,"success",data);
    }

    public static ResponseData error(String info){
        return  new ResponseData(500,info,null);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
