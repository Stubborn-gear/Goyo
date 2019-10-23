package com.htdata.goyo.network.manage;

/**
 * Created by Administrator on 2019/1/14.
 */

public interface OnResponseResult {

     void onResponseSuccess(String result, int tag);

     void onResponseError(String result, int tag);

}
