package com.example.think.test1.utils.okhttp;



import com.example.think.test1.MainActivity;
import com.example.think.test1.MyApplication;
import com.example.think.test1.utils.okhttp.util.NetworkConnectionUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.think.test1.utils.okhttp.util.HttpUtils.getUserAgent;


/**
 * Created by Horrarndoo on 2017/9/18.
 * <p>
 * 有网络时的缓存拦截器
 */
public class NetInterceptor implements Interceptor{
    @Override
    public Response intercept(Chain chain) throws IOException {
        // 有网络时, 缓存1分钟, 最大保存时长为60s
        int maxAge = 60;
        Request request = chain.request();

        if (NetworkConnectionUtils.isNetworkConnected(MyApplication.getContext())) {
            request = request.newBuilder()
                    .removeHeader("User-Agent")
                    .header("User-Agent", getUserAgent())
                    //                    .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1;
                    // WOW64) AppleWebKit/537.36" +
                    //                            " (KHTML, like Gecko) Chrome/50.0.2661.102
                    // Safari/537.36")
                    .build();

            Response response = chain.proceed(request);
            return response.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .build();
        }

        return chain.proceed(request);
    }
}
