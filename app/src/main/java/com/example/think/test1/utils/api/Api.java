package com.example.think.test1.utils.api;



import android.content.pm.PackageInstaller;

import com.example.think.test1.utils.entity.BaseEntity;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author 406
 * @version 1.0.0
 * @ClassName Api
 * @Description
 * @E-mail
 * @time 2018/03/12
 */
public interface Api {

    String HOST = "http://39.106.138.186:8900/app/";

    @FormUrlEncoded
    @POST("app/login")
    Observable<BaseEntity<PackageInstaller.Session>> login(
            @Field("userName") String username,
            @Field("password") String password);

    @FormUrlEncoded
    @POST("{version}/login")
    Observable<BaseEntity<PackageInstaller.Session>> loginByPhone(
            @Path("version") String version,
            @Field("phone") String phone,
            @Field("code") String code);


    /**
     * 获取验证码
     */
    @POST("{version}/captcha")
    Observable<BaseEntity> getCode(@Path("version") String version, @Query("phone") String phone);

    /**
     * messageType : 1 login
     * 2 regist
     *
     * 4修改密码
     * 5更换手机号
     * 6 绑定邮箱
     * 7 修改密码
     */
    @FormUrlEncoded
    @POST("app/verify/code")
    Observable<BaseEntity> verifyCode(
            @Field("msgCode") String msgCode,
            @Field("mobile") String mobile,
            @Field("msgType") String msgType);

    /**
     * 登出
     */
    @GET("app/logout")
    Observable<BaseEntity> logout();

    /**
     * 注册
     */
    @FormUrlEncoded
    @POST("app/register")
    Observable<BaseEntity<PackageInstaller.Session>> regist(
            @Field("userName") String username,
            @Field("password") String password,
            @Field("msgCode") String code);

    /**
     * 忘记密码
     */
    @FormUrlEncoded
    @POST("app/forgetpasswd")
    Observable<BaseEntity<PackageInstaller.Session>> getPwdBack(
            @Field("userName") String username,
            @Field("password") String password,
            @Field("msgCode") String code);

    /**
     * 搜索页-加载热门搜索关键字，最多10个
//     */
//    @GET("products/search_hotSearch")
//    Observable<BaseEntity<BaseListEntity<HotSearchBean>>> getHotSearch();
//
//    /**
//     * 搜索
//     */
//    @POST("{version}/search")
//    Observable<BaseEntity<BaseListEntity<CartoonContentsBean>>> getSearch(
//            @Path("version") String version,
//            @Query("keywords") String keywords,
//            @Query("page") int page);
//
//
//    /**
//     * 首页
//     */
//    @GET("{version}/rhomepage")
//    Observable<BaseEntity<HomeNetDataBean>> getHomeData(
//            @Path("version") String version);
//
//    /**
//     * 获取首页更多
//     */
//    @POST("{version}/rmodule/{moduleId}")
//    Observable<BaseEntity<BaseListEntity<CartoonContentsBean>>> getHomeModule(
//            @Path("version") String version,
//            @Path("moduleId") int moduleId,
//            @Query("user_id") int user_id,
//            @Query("page") int page);
//
//
//    /**
//     * 获取分类卡通
//     */
//    @POST("{version}/rcategory")
//    Observable<BaseEntity<BaseListEntity<CartoonContentsBean>>> getClassificationCartoon(
//            @Path("version") String version,
//            @Query("cate") int cate,
//            @Query("rate") int rate,
//            @Query("page") int page);
//
//    /**
//     * 目录页获取顶部详情
//     */
//    @POST("{version}/rcartoon/{cartoonId}")
//    Observable<BaseEntity<CartoonContentsBean>> getContentsTopData(
//            @Path("version") String version,
//            @Path("cartoonId") int cartoonId,
//            @Query("user_id") int user_id);
//
//    /**
//     * 目录页获取目录
//     */
//    @POST("{version}/rcarlist/{cartoonId}")
//    Observable<BaseEntity<BaseListEntity<ContentsBean>>> getContents(
//            @Path("cartoonId") int cartoonId,
//            @Query("user_id") int user_id,
//            @Query("page") int page);

//page    /**
//     * 阅读页获取图片
//     */
//    @POST("{version}/rchapter/{chapter_id}")
//    Observable<BaseEntity<ContentReadBean>> getContentPic(
//            @Path("version") String version,
//            @Path("chapter_id") int chapterId,
//            @Query("cartoon_id") int cartoonId);

//    /**
//     * 历史列表
//     */
//    @POST("{version}/rhistory")
//    Observable<BaseEntity<BaseListEntity<CartoonBook>>> getHistory(
//            @Path("version") String version,
//            @Query("page") int page);
//
//    /**
//     * 收藏列表
//     */
//    @POST("{version}/rfavorites")
//    Observable<BaseEntity<BaseListEntity<CartoonBook>>> getCollect(
//            @Path("version") String version,
//            @Query("page") int page);
//
//    /**
//     * 历史和收藏列表删除
//     */
//    @POST("{version}/delbooks")
//    Observable<BaseEntity<BaseListEntity<CartoonBook>>> delBooks(
//            @Path("version") String version,
//            @Query("cartoon_ids") String cartoon_ids,
//            @Query("type") int type);

}
