package com.company.springdemo.common.base;

/**
 * @Auther: whs
 * @Date: 2018/7/16 16:27
 * @Description:
 */
public enum RespCode {

    SUCCESS(0, "请求成功"),
    WARN(-1, "网络异常，请稍后重试"),
    INSERT_ERROR(200, "入库失败"),
    NOT_LOGIN(100, "请登陆"),
    URL_NOT_ILLEGAL(1001, "URL请求不合法"),
    PARAMS_NOT_ILLEGAl(1001, "请求参数包含敏感信息");

    private int code;
    private String msg;

    RespCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }

}
