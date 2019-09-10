package com.example.think.test1.utils;

import com.example.think.test1.MyApplication;
import com.example.think.test1.tools.RxDeviceTool;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Route;
import okio.BufferedSink;

import static com.example.think.test1.utils.okhttp.TokenInterceptor.exChangeLOW;
import static com.example.think.test1.utils.okhttp.TokenInterceptor.exChangeUP;
import static com.example.think.test1.utils.okhttp.TokenInterceptor.toMD5;


/**
 * 网络请求工具（备用）
 */

public class HttpUtil {
    /**
     * 获取token令牌
     */
    public static String getToken() {
        String token = "";
        // 创建一个okhttp连接
        OkHttpClient client = new OkHttpClient.Builder().build();

        // 创建一个请求体
        RequestBody requestBody = new RequestBody() {
            @Override
            public MediaType contentType() {
                // 设置请求体类型
                return MediaType.parse("text/plain");
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                // 写入请求体数据
                String post = "grant_type=client_credentials&client_id=StudentForest&client_secret=information12016921";
                sink.writeUtf8(post);
            }
        };

        // 创建一个post请求
        Request request = new Request.Builder()
                .url("http://api.hicc.cn//token")
                .post(requestBody)
                .build();

        try {
            // 通过okhttp发起一次请求，获得响应
            Response response = client.newCall(request).execute();

            // 获得响应体
            String result = response.body().string();
            // 解析json数据
            JSONObject jsonObject = new JSONObject(result);
            // 获得返回的token
            token = jsonObject.getString("access_token");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return token;
    }

    /**
     * 发送一个get请求
     * @param url      地址（只需要关键部分，不需要全路径）
     * @param callback 回调接口
     */
    public static void sendHttpGetRequest(String url, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                // 添加一个认证
                .authenticator(new Authenticator() {
                    @Override
                    public Request authenticate(Route route, Response response) throws IOException {
                        long time = System.currentTimeMillis();
                        return response.request().newBuilder()
                                .header("sign",getSign(time))
                                .header("time", time + "")
                                .header("aptype","2")
                                .header("did", RxDeviceTool.getDeviceIdIMEI(MyApplication.getContext()))
                                .build();
                    }
                }).build();


        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(callback);
    }
    private static String getSign(long time){
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

}