package com.example.think.test1.utils.entity;

/**
 * @author 406
 * @version 1.0.0
 * @ClassName BaseEntity
 * @Description 返回数据统一格式bean  列表数据配合BaseListEntity
 * @E-mail
 * @time 2018/06/04
 */
public class BaseEntity<T> {

    private int code;
    private String msg;
    private T data;

    public boolean isSuccess(){
        return getCode() == AgreementCode.SUCCESS_CODE;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
