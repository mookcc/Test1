package com.example.think.test1;

import android.app.Application;
import android.content.Context;

import com.example.think.test1.tools.RxDeviceTool;
import com.example.think.test1.utils.HttpUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Authenticator;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

import static com.example.think.test1.utils.okhttp.TokenInterceptor.exChangeLOW;
import static com.example.think.test1.utils.okhttp.TokenInterceptor.exChangeUP;
import static com.example.think.test1.utils.okhttp.TokenInterceptor.toMD5;

/**
 * Created by Think on 2019/9/10.
 */

public class MyApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

        // 配置OkHttpUtils
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
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
                })
                .build();

        OkHttpUtils.initClient(okHttpClient);

        // 捕获全局未捕获的异常
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable ex) {
                // 输出异常
                ex.printStackTrace();
//                Logs.d("捕获到的异常：" + ex.toString());
                System.exit(0);
            }
        });
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
    public static Context getContext () {
        return mContext;
    }
}
