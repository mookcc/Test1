package com.example.think.test1.utils.entity;

/**
 * @author 406
 * @version 1.0.0
 * @ClassName AgreementCode
 * @Description 协议约定200正常返回数据时，所获得json数据内的约定code值
 * @E-mail
 * @time 2018/06/04
 */
public class AgreementCode {

    /**
     * 请求成功
     */
    public static final int SUCCESS_CODE = 0;

    /**
     * 未知错误
     */
    public static final int UNKNOWN = 1000;
    /**
     * 解析错误
     */
    public static final int PARSE_ERROR = 1001;
    /**
     * 网络错误
     */
    public static final int NETWORK_ERROR = 1002;
    /**
     * 协议出错
     */
    public static final int HTTP_ERROR = 1003;
    /**
     * HTTP401
     */
    public static final int TOKEN_ERROR = 1008;

}
