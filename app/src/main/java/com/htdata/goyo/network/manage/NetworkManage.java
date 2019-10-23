package com.htdata.goyo.network.manage;

import android.content.Context;

import com.htdata.goyo.network.utils.NetUtil;

import java.io.IOException;
import java.util.Map;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @作者：cb
 * @日期：2019-10-17 14:37
 * @描述：网络管理
 */
public class NetworkManage {

    private static NetworkManage mInstance ;
    private static RequestMethod requestMethod;
    private Call<ResponseBody> myCall;
//    private NetLoadingDialog mDialog;

    /**
     * 获取 NetworkManage 唯一实例
     * @return
     */
    public static NetworkManage getInstance(){
        if (mInstance == null){
            synchronized (NetworkManage.class){
                if (mInstance == null){
                    mInstance = new NetworkManage();
                }
            }
        }
        return mInstance ;
    }

    /**
     * 获取 RequestMethod 唯一实例
     * @return
     */
    private static RequestMethod getRequestMethod(){
        if (requestMethod == null){
            synchronized (NetworkManage.class){
                if (requestMethod == null){
                    requestMethod = RetrofitClient.
                            getInstance()
                            .RetrofitClient()
                            .create(RequestMethod.class);
                }
            }
        }
        return requestMethod ;
    }

    /**
     * Get请求 同步
     * @param result  接收 请求结果回调
     * @param code  请求状态码
     * @param map   请求参数
     * @param isShowDialog  是否显示加载框
     * @param context   需要activity的Context，如果不需要加载框可以传null
     */
    public void reqGetSync(OnResponseResult result, String url, int code, Map<String, Object> map,
                           boolean isShowDialog, Context context){

        showLoadWaitDialog(isShowDialog,context);
        Call<ResponseBody> call = getRequestMethod().reqGetParamet(url,map);
        try {
            ResponseBody body = call.execute().body();
            dismissDialog();
            if (!body.string().isEmpty()){
                result.onResponseSuccess(body.string(),code);
            }else {
                result.onResponseError(body.string(),code);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get请求 异步
     * @param result  接收 请求结果回调
     * @param code  请求状态码
     * @param map   请求参数
     * @param isShowDialog  是否显示加载框
     * @param context   需要activity的Context，如果不需要加载框可以传null
     */
    public void reqGetAsync(OnResponseResult result, String url, int code, Map<String, Object> map,
                              boolean isShowDialog, Context context){
        showLoadWaitDialog(isShowDialog,context);
        Call<ResponseBody> call = getRequestMethod().reqGetParamet(url,map);
        onNetCallBack(call,result,context,code);
        dismissDialog();
    }

    /**
     * Post请求
     * @param result  接收 请求结果回调
     * @param code  请求状态码
     * @param map   请求参数
     * @param isShowDialog  是否显示加载框
     * @param context   需要activity的Context，如果不需要加载框可以传null
     */
    public void reqPost(OnResponseResult result, String url, int code, Map<String, Object> map,
                        boolean isShowDialog, Context context){
        showLoadWaitDialog(isShowDialog,context);
        Call<ResponseBody> call = getRequestMethod().reqPostMap(url,map);
        onNetCallBack(call,result,context,code);
    }

    /**
     * Post请求
     * @param result  接收 请求结果回调
     * @param code  请求状态码
     * @param json   请求参数
     * @param isShowDialog  是否显示加载框
     * @param context   需要activity的Context，如果不需要加载框可以传null
     */
    public void reqPost(OnResponseResult result, String url, int code, String json,
                        boolean isShowDialog, Context context){
        showLoadWaitDialog(isShowDialog,context);
        Call<ResponseBody> call = getRequestMethod().reqPostJson(url,json);
        onNetCallBack(call,result,context,code);
    }

    /**
     * Post请求
     * @param result  接收 请求结果回调
     * @param code  请求状态码
     * @param map   请求参数
     * @param isShowDialog  是否显示加载框
     * @param context   需要activity的Context，如果不需要加载框可以传null
     */
    public void reqPut(OnResponseResult result, String url, int code, Map<String, Object> map,
                        boolean isShowDialog, Context context){
        showLoadWaitDialog(isShowDialog,context);
        Call<ResponseBody> call = getRequestMethod().reqPutMap(url,map);
        onNetCallBack(call,result,context,code);
    }

    /**
     * Post请求
     * @param result  接收 请求结果回调
     * @param code  请求状态码
     * @param json   请求参数
     * @param isShowDialog  是否显示加载框
     * @param context   需要activity的Context，如果不需要加载框可以传null
     */
    public void reqPut(OnResponseResult result, String url, int code, String json,
                        boolean isShowDialog, Context context){
        showLoadWaitDialog(isShowDialog,context);
        Call<ResponseBody> call = getRequestMethod().reqPutJson(url,json);
        onNetCallBack(call,result,context,code);
    }

    /**
     * delete 请求
     * @param result  接收 请求结果回调
     * @param code  请求状态码
     * @param map   请求参数
     * @param isShowDialog  是否显示加载框
     * @param context   需要activity的Context，如果不需要加载框可以传null
     */
    public void reqDelete(OnResponseResult result, String url, int code, Map<String, Object> map,
                       boolean isShowDialog, Context context){
        showLoadWaitDialog(isShowDialog,context);
        Call<ResponseBody> call = getRequestMethod().reqDelete(url,map);
        onNetCallBack(call,result,context,code);
    }

    /**
     * 表单 请求
     * @param result  接收 请求结果回调
     * @param code  请求状态码
     * @param map   请求参数
     * @param isShowDialog  是否显示加载框
     * @param context   需要activity的Context，如果不需要加载框可以传null
     */
    public void reqPostFormUrl(OnResponseResult result, String url, int code, Map<String, Object> map,
                          boolean isShowDialog, Context context){
        showLoadWaitDialog(isShowDialog,context);
        Call<ResponseBody> call = getRequestMethod().reqFormUrl(url,map);
        onNetCallBack(call,result,context,code);
    }

    /**
     * 文件下载 请求
     * @param result  接收 请求结果回调
     * @param code  请求状态码
     * @param map   请求参数
     * @param isShowDialog  是否显示加载框
     * @param context   需要activity的Context，如果不需要加载框可以传null
     */
    public void reqFileDownload(OnResponseResult result, String url, int code, Map<String, Object> map,
                               boolean isShowDialog, Context context){
        showLoadWaitDialog(isShowDialog,context);
        Call<ResponseBody> call = getRequestMethod().reqFileDownload(url,map);
        onNetDownloadCallBack(call,result,code);
    }

    /**
     * 单一文件上传 请求
     * @param result  接收 请求结果回调
     * @param code  请求状态码
     * @param body   请求参数
     * @param isShowDialog  是否显示加载框
     * @param context   需要activity的Context，如果不需要加载框可以传null
     */
    public void reqPostAloneFileUpLoad(OnResponseResult result, String url, int code, RequestBody body,
                                boolean isShowDialog, Context context){
        showLoadWaitDialog(isShowDialog,context);
        Call<ResponseBody> call = getRequestMethod().reqPostAloneFileUpLoad(url,body);
        onNetUpLoadCallBack(call,result,code);
    }

    /**
     * 多文件上传 请求
     * @param result  接收 请求结果回调
     * @param code  请求状态码
     * @param map   请求参数
     * @param file   请求参数
     * @param isShowDialog  是否显示加载框
     * @param context   需要activity的Context，如果不需要加载框可以传null
     */
    public void reqPostMoreFileUpLoad(OnResponseResult result, String url, int code, Map<String, RequestBody> map, MultipartBody.Part file, boolean isShowDialog, Context context){
        showLoadWaitDialog(isShowDialog,context);
        Call<ResponseBody> call = getRequestMethod().reqPostMoreFileUpLoad(url,map,file);
        onNetUpLoadCallBack(call,result,code);
    }

    /**
     * 请求 返回
     */
    private void onNetCallBack(Call<ResponseBody> call,final OnResponseResult result ,Context context,final int code) {
        myCall = call ;
        if (!NetUtil.isNetConnected(context)){
            //做通知处理
        }

        call.enqueue(new Callback<ResponseBody>() {
           @Override
           public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
               try {
                   if (response.code() == Command.STATUS_CODE_200){
                       result.onResponseSuccess(response.body().string(),code);
                   }else {
                       System.out.println("======返回异常======="+response.toString());
                   }
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }

           @Override
           public void onFailure(Call<ResponseBody> call, Throwable t) {
               result.onResponseError(t.getMessage(),code);
               if (call.isCanceled()) {
                   System.out.println("=======取消请求======"+ t.getMessage());
               } else {
                   System.out.println("=======其他原因======"+ t.getMessage());
               }
           }
       });
    }

    /**
     * 请求 文件下载返回
     */
    private void onNetDownloadCallBack(Call<ResponseBody> call,final OnResponseResult result ,final int code) {
        myCall = call ;
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.code() == Command.STATUS_CODE_200){
                        result.onResponseSuccess(response.body().string(),code);
                    }else {
                        System.out.println("======返回异常======="+response.toString());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                result.onResponseError(t.getMessage(),code);
                if (call.isCanceled()) {
                    System.out.println("=======取消请求======"+ t.getMessage());
                } else {
                    System.out.println("=======其他原因======"+ t.getMessage());
                }
            }
        });
    }

    /**
     * 请求 文件上传返回
     */
    private void onNetUpLoadCallBack(Call<ResponseBody> call,final OnResponseResult result ,final int code) {
        myCall = call ;
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.code() == Command.STATUS_CODE_200){
                        result.onResponseSuccess(response.body().string(),code);
                    }else {
                        System.out.println("======返回异常======="+response.toString());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                result.onResponseError(t.getMessage(),code);
                if (call.isCanceled()) {
                    System.out.println("=======取消请求======"+ t.getMessage());
                } else {
                    System.out.println("=======其他原因======"+ t.getMessage());
                }
            }
        });
    }


    private Context mContext ;
    /**
     *  显示网络加载框
     * @param isShow 是否显示
     * @param context context
     */
    private void showLoadWaitDialog(boolean isShow,Context context){
        mContext = context ;
        if (!isShow){
            return;
        }
        /*NetLoadingDialog.getInstance(context).showDialog();*/
//        mDialog = NetLoadingDialog.getInstance(context);
//        if (mDialog != null){
//            mDialog.showDialog();
//            LogUtils.v("=========显示==========");
//        }else {
//            LogUtils.v("=========dialog为null=不能显示=========");
//        }
    }

    private void dismissDialog(){
       /* NetLoadingDialog.getInstance(mContext).showDialog();*/
//        if (mDialog != null){
//            mDialog.dismissDialog();
//            LogUtils.v("=========关闭==========");
//
//        }else {
//            LogUtils.v("=========关闭dialog时为null==========");
//        }
    }

    /**
     * 取消请求
     */
    public void cancelRequest(){
        if (myCall != null){
            myCall.cancel();
        }
    }






}
