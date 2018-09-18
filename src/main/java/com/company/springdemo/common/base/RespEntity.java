package com.company.springdemo.common.base;

/**
 * @description: 返回响应实体
 * @author: whs
 * @create: 2018-07-15 14:20
 **/
public class RespEntity {

    private Integer code;

    private String message;

//    Object均有很大的灵活性，比String要好
    private Object data;

    private RespEntity(){
    }

    public RespEntity(RespCode respCode){
        this.code = respCode.getCode();
        this.message = respCode.getMsg();
    }

    public RespEntity(RespCode respCode, Object data){
        this.code = respCode.getCode();
        this.message = respCode.getMsg();
        this.data = data;
    }

    public RespEntity(Integer code, String message, Object data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
