package com.htdata.goyo.network.manage;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by Administrator on 2019/1/4.
 */

public interface RequestMethod {

    @GET
    Call<ResponseBody> reqGetParamet(@Url String url, @QueryMap Map<String, Object> map);

    @POST
    Call<ResponseBody> reqPostMap(@Url String url, @Body Map<String, Object> map);

    @POST
    Call<ResponseBody> reqPostJson(@Url String url, @Body String json);

    @PUT
    Call<ResponseBody> reqPutMap(@Url String url, @Body Map<String, Object> map);

    @PUT
    Call<ResponseBody> reqPutJson(@Url String url, @Body String json);

    @DELETE
    Call<ResponseBody> reqDelete(@Url String url, @QueryMap Map<String, Object> map);

    @Streaming //表示返回的数据以流的形式返回，适用于返回数据较大的场景，如果没有使用该注解，默认把数据全部载入内存，之后获取数据也是从内存中读取
    @GET
    Call<ResponseBody> reqFileDownload(@Url String url, @QueryMap Map<String, Object> map);

    @FormUrlEncoded // 标记:表示请求体是一个form表单
    @POST
    Call<ResponseBody> reqFormUrl(@Url String url, @FieldMap Map<String, Object> map);//或者是  @Field("userName") name

    /**
     * {@link Part} 后面支持三种类型，{@link RequestBody}、{@link MultipartBody.Part} 、任意类型
     * 除 {@link MultipartBody.Part} 以外，其它类型都必须带上表单字段({@link MultipartBody.Part} 中已经包含了表单字段的信息)，
     */
    // 单独文件上传
    @Multipart // 标记类注解:表示请求体是一个支持文件上传的form表单
    @POST
    Call<ResponseBody> reqPostAloneFileUpLoad(@Url String url, @Part("file") RequestBody name/*, @Part MultipartBody.Part file*/);

    // 多文件上传
    @Multipart
    @POST
    Call<ResponseBody> reqPostMoreFileUpLoad(@Url String url, @PartMap Map<String, RequestBody> map, @Part MultipartBody.Part file);























/*    //Get无参  返回对应实体
    @GET
    Call<ResponseBody> reqGetNoParamet(@Url String url);

    //Get有参 返回原始字符串
    @GET
    Call<ResponseBody> reqGetParamet(@Url String url, @Query("user") String user);

    //Get有参  返回原始字符串
    @GET
    Call<ResponseBody> reqGetParamet(@Url String url, @QueryMap Map<String, Object> map);

    //Post有参  返回原始字符串
    @Headers("{Accept: application/vnd.github.v3.full+json, User-Agent: Retrofit-Sample-App}")
    @FormUrlEncoded //表单提交数据
    @POST
    Call<ResponseBody> reqPostParamet(@Url String url, @FieldMap Map<String, Object> map);

    //Post有参  返回原始字符串
    @Headers("{Accept: application/vnd.github.v3.full+json, User-Agent: Retrofit-Sample-App}")
    @POST
    Call<ResponseBody> reqPostCustom(@Url String url, @Body String json);//自定义请求体  如果是map需要经过new FormBody.Builder()转换

    *//**
     * {@link Part} 后面支持三种类型，{@link RequestBody}、{@link MultipartBody.Part} 、任意类型
     * 除 {@link MultipartBody.Part} 以外，其它类型都必须带上表单字段({@link MultipartBody.Part} 中已经包含了表单字段的信息)，
     *//*
    // 单独文件上传
    @Multipart
    @POST
    Call<ResponseBody> reqPostAloneFileUpLoad(@Url String url, @Part("name") RequestBody name*//*, @Part MultipartBody.Part file*//*);

    // 多文件上传
    @Multipart
    @POST
    Observable<ResponseBody> reqPostMoreFileUpLoad(@Url String url, @PartMap Map<String, RequestBody> file, @Part MultipartBody.Part file2);

//    // 替换{}中的地址
//    @GET("users/iop/{repos}")
//    Observable<ResponseBody> reqGetParamet33(@Path("repos") String user);

    //Get无参  返回对应实体
//    @GET(ReqUrl.SOLUTION)
//    Observable<DataModel1> reqGetNoParamet();

    //Get有参 带请求头
//    @Headers("{Accept: application/vnd.github.v3.full+json, User-Agent: Retrofit-Sample-App}")
//    @GET(ReqUrl.CHECK_USER)
//    Observable<ResponseBody> reqGetParamet(@Query("user") String user);*/



}
