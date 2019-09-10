package com.example.think.test1.utils.retrofit;



import com.example.think.test1.utils.api.Api;
import com.example.think.test1.utils.okhttp.CacheInterceptor;
import com.example.think.test1.utils.okhttp.HttpCache;
import com.example.think.test1.utils.okhttp.TrustManager;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
//import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class RetrofitFactory {

    private static final int TIMEOUT_READ = 20;
    private static final int TIMEOUT_CONNECTION = 20;
//    private static final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor()
//            .setLevel(HttpLoggingInterceptor.Level.BODY);
    private static CacheInterceptor cacheInterceptor = new CacheInterceptor();

    private static RetrofitFactory mRetrofitFactory;
    private static Api mAPIFunction;

    private RetrofitFactory(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                //SSL证书
                .sslSocketFactory(TrustManager.getUnsafeOkHttpClient())
                .hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)
                //打印日志
//                .addInterceptor(interceptor)
                //设置Cache拦截器
                .addNetworkInterceptor(cacheInterceptor)
                .addInterceptor(cacheInterceptor)
                .cache(HttpCache.getCache())
                //time out
                .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
                //失败重连
                .retryOnConnectionFailure(true)
                .build();

        Retrofit mRetrofit=new Retrofit.Builder()
                .baseUrl(Api.HOST)
                .client(okHttpClient)
                .addConverterFactory(CustomGsonConverterFactory.create())//添加gson转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//添加rxjava转换器
                .build();
        mAPIFunction = mRetrofit.create(Api.class);

    }

    public static RetrofitFactory getInstence(){
        if (mRetrofitFactory==null){
            synchronized (RetrofitFactory.class) {
                if (mRetrofitFactory == null)
                    mRetrofitFactory = new RetrofitFactory();
            }

        }
        return mRetrofitFactory;
    }

    public Api API(){
        return mAPIFunction;
    }

}
