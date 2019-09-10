package com.example.think.test1.utils.rxjava;

import android.accounts.NetworkErrorException;
import android.content.Intent;

import com.example.think.test1.MyApplication;
import com.example.think.test1.utils.entity.AgreementCode;
import com.example.think.test1.utils.entity.BaseEntity;
import com.example.think.test1.utils.exception.ApiException;
import com.google.gson.JsonParseException;


import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observer;
import retrofit2.HttpException;

public abstract class BaseObserver<T> implements Observer<BaseEntity<T>> {

    @Override
    public void onNext(BaseEntity<T> tBaseEntity) {
//        TLog.i();
        if (tBaseEntity.isSuccess()) {
            try {
                onSuccees(tBaseEntity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                ApiException exception = new ApiException(tBaseEntity.getCode());
                exception.setDisplayMessage(tBaseEntity.getMsg());
                onError(exception);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onError(Throwable e) {
//        TLog.i("onError: " + e.toString());//这里可以打印错误信息
        ApiException ex;
        try {
            if (e instanceof HttpException) {             //HTTP错误
                HttpException httpException = (HttpException) e;
//                TLog.i("code: " + httpException.code());
                ex = new ApiException(AgreementCode.HTTP_ERROR);
                String errorMsg = "网络错误";
//                switch (httpException.code()) {
//                    case HTTP_Status_Code.HTTP_UNAUTHORIZED:
//                        TLog.i("code: " + HTTP_Status_Code.HTTP_UNAUTHORIZED);
//                        Intent intent401 = new Intent();
//                        //设置广播的名字（设置Action）
//                        intent401.setAction(ActionKeyConstant.INTENT_ACTION_LOGOUT);
//                        App.getInstance().sendBroadcast(intent401);
//                        break;
//                    case HTTP_Status_Code.HTTP_FORBIDDEN:
//                        TLog.i("code: " + HTTP_Status_Code.HTTP_FORBIDDEN);
//                        break;
//                    case HTTP_Status_Code.HTTP_NOT_FOUND:
//                        TLog.i("code: " + HTTP_Status_Code.HTTP_NOT_FOUND);
//                        break;
//                    case HTTP_Status_Code.HTTP_BAD_METHOD:
//                        TLog.i("code: " + HTTP_Status_Code.HTTP_NOT_FOUND);
//                        errorMsg = "您的帐号已在其他设备登录，如果不是您本人操作，请修改密码注意隐私安全";
//                        Intent intent405 = new Intent();
//                        //设置广播的名字（设置Action）
//                        intent405.setAction(ActionKeyConstant.INTENT_ACTION_LOGOUT);
//                        App.getInstance().sendBroadcast(intent405);
//                        break;
//                    case HTTP_Status_Code.HTTP_CLIENT_TIMEOUT:
//                        TLog.i("code: " + HTTP_Status_Code.HTTP_CLIENT_TIMEOUT);
//                        break;
//                    case HTTP_Status_Code.HTTP_GATEWAY_TIMEOUT:
//                        TLog.i("code: " + HTTP_Status_Code.HTTP_GATEWAY_TIMEOUT);
//                        break;
//                    case HTTP_Status_Code.HTTP_SERVER_ERROR:
//                        TLog.i("code: " + HTTP_Status_Code.HTTP_SERVER_ERROR);
//                        break;
//                    case HTTP_Status_Code.HTTP_BAD_GATEWAY:
//                        TLog.i("code: " + HTTP_Status_Code.HTTP_BAD_GATEWAY);
//                        break;
//                    case HTTP_Status_Code.HTTP_UNAVAILABLE:
//                        TLog.i("code: " + HTTP_Status_Code.HTTP_UNAVAILABLE);
//                        break;
//                    default:
//                        TLog.i("code: default");
//
//                        break;
//                }
                ex.setDisplayMessage(errorMsg);  //均视为网络错误
                onFailure(ex, true);
            } else if (e instanceof ConnectException
                    || e instanceof TimeoutException
                    || e instanceof NetworkErrorException
                    || e instanceof UnknownHostException
                    || e instanceof SocketTimeoutException
                    || e instanceof SocketException) {
                e.printStackTrace();
//                TLog.i("非Retrofit2 HttpException网络错误");
                ex = new ApiException(AgreementCode.HTTP_ERROR);
                ex.setDisplayMessage("网络错误");
                onFailure(ex, true);
            } else if (e instanceof JsonParseException
                    || e instanceof JSONException
                    || e instanceof ParseException){
//                TLog.i("解析错误");
                ex = new ApiException(AgreementCode.PARSE_ERROR);
                ex.setDisplayMessage("解析错误");
                //均视为解析错误
                onFailure(ex, true);
            } else if (e instanceof ApiException) {
                ApiException apiException = (ApiException) e;
                if (apiException.getCode()==AgreementCode.TOKEN_ERROR){
                    Intent intent = new Intent();
                    //设置广播的名字（设置Action）
//                    intent.setAction(ActionKeyConstant.INTENT_ACTION_LOGOUT);
                    MyApplication.getContext().sendBroadcast(intent);
                }
//                TLog.i("ApiException-----" + apiException.getDisplayMessage());
                onFailure(apiException, false);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void onComplete() {
//        TLog.i();
    }

    /**
     * 返回成功
     *
     * @param t
     * @throws Exception
     */
    protected abstract void onSuccees(BaseEntity<T> t) throws Exception;

    /**
     * 返回失败
     *
     * @param e
     * @param isNetWorkError 是否是网络错误
     * @throws Exception
     */
    protected abstract void onFailure(ApiException e, boolean isNetWorkError) throws Exception;

    /**
     * 返回成功了,但是code错误
     *
     * @param t
     * @throws Exception
     */
    protected void onCodeError(BaseEntity<T> t) throws Exception {
    }

}
