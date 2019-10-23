package com.htdata.goyo.network.manage;

/**
 * Created by Administrator on 2019/1/2.
 */

public class ReqUrl {


    //=====================线上服务器===================================
    public static final String BASE_URL = "http://m.iiot.htdata.com:18080";



//    public static final String BASE_URL = "http://192.168.14.115:8080/";// 测试版


    /**
     * 获取首页解决方案
     */
    public static final String SOLUTION = "api/common/moduleInfo";
    /**
     * 验证用户名
     */
    public static final String CHECK_USER = "api/user/mobile/checkUser";

    /**
     * 添加关注
     */
    public static final String ADD_FOLLOW = "api/orderclient/engineerAttention";

    /**
     * 取消关注
     */
    public static final String UN_FOLLOW = "api/orderclient/engineerAttentionDelete";



}
