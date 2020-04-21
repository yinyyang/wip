package com.springcloud.admin.ajax;

public class APIResponse <T> {

    public static final String SUCCESS = "0";

    public static final String FAIL = "1";

    private Object code;
    private T data;
    private String msg;

    public APIResponse(){

    }

    public APIResponse(Object code){
        this.code = code;
    }

    public APIResponse(String code, T data){
        this.code = code;
        this.data = data;
    }

    public APIResponse(String code, String msg){
        this.code = code;
        this.msg = msg;
    }
    public APIResponse(String code, T data,String msg){
        this.code = code;
        this.data = data;
        this.msg = msg;
    }
    public static APIResponse success(){
        return new APIResponse(SUCCESS);
    }

    public static APIResponse success(Object data){
        return new APIResponse(SUCCESS, data);
    }

    public static APIResponse fail(String msg){
        return new APIResponse(FAIL, msg);
    }

    public static APIResponse withCode(Object code) {
        return new APIResponse(code);
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getCode() {
        return code;
    }

    public void setCode(Object code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}