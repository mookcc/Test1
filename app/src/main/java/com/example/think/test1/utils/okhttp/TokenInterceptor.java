package com.example.think.test1.utils.okhttp;

import android.text.TextUtils;


import com.example.think.test1.MyApplication;
import com.example.think.test1.tools.RxDeviceTool;

import java.io.IOException;
import java.security.MessageDigest;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author 406
 * @version 1.0.0
 * @ClassName TokenInterceptor
 * @Description
 * @E-mail
 * @time 2018/06/08
 */
public class TokenInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();

                long time = System.currentTimeMillis();
                Request authorised = originalRequest.newBuilder()
                        .header("sign",getSign(time))
                        .header("time", time + "")
                        .header("aptype","2")
                        .header("did", RxDeviceTool.getDeviceIdIMEI(MyApplication.getContext()))

                        .build();
                return chain.proceed(authorised);




    }


    private String getSign(long time){
        String a = "aptype=2&did="+RxDeviceTool.getDeviceIdIMEI(MyApplication.getContext())+"&time="+time;

//        TLog.i("a::" + a);
        String b = toMD5(a);
//        TLog.i("b::" + b);
        String c = exChangeUP(b)+"cartoon123";
//        TLog.i("c::" + c);
        String sign = toMD5(c);
//        TLog.i("sign::" + sign);
        return exChangeLOW(sign);
    }


    //把一个字符串中的小写转换为大写
    public static String exChangeUP(String str){
        StringBuffer sb = new StringBuffer();
        int i;
        for(i = 0; i <= str.length()-1;i ++){//遍历字符串
            char ch;
            //通过str.charAt(i)遍历出字符串中每个字符
            if(str.charAt(i) >= 'a' && str.charAt(i) <= 'z'){//判断字符是否在a-z之间（小写）
                ch = (char) (str.charAt(i)-32);               //如果为小写则转换为相应大写,赋值给ch
            }else if(str.charAt(i) >= 'A' && str.charAt(i) <= 'Z'){//判断字符是否在A-Z之间（大写）
                ch = str.charAt(i);                 //
            }else if(str.charAt(i)>='0'&&str.charAt(i)<='9'){//判断字符是否在0-9之间（数字）
                ch = str.charAt(i);                         //如果为数字,将原数字赋值给ch
            }else {
                ch = '*';                                   //如果为其他则转为*号
            }
            sb.append(ch);                                    //将字符追加到sb序列
        }

        return sb.toString();
    }


    //把一个字符串中的大写转为小写
    public static String exChangeLOW(String str){
        StringBuffer sb = new StringBuffer();
        int i;
        for(i = 0; i <= str.length()-1;i ++){//遍历字符串
            char ch;
            //通过str.charAt(i)遍历出字符串中每个字符
            if(str.charAt(i) >= 'a' && str.charAt(i) <= 'z'){//判断字符是否在a-z之间（小写）
                ch = str.charAt(i);                //
            }else if(str.charAt(i) >= 'A' && str.charAt(i) <= 'Z'){//判断字符是否在A-Z之间（大写）
                ch = (char) (str.charAt(i)+32);
            }else if(str.charAt(i)>='0'&&str.charAt(i)<='9'){//判断字符是否在0-9之间（数字）
                ch = str.charAt(i);                         //如果为数字,将原数字赋值给ch
            }else {
                ch = '*';                                   //如果为其他则转为*号
            }
            sb.append(ch);                                    //将字符追加到sb序列
        }

        return sb.toString();
    }


    public final static String toMD5(String s) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

}
