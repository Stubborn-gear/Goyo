package com.htdata.goyo.util;

/**
 * @作者：cb
 * @日期：2019-10-23 8:52
 * @描述： 用户工具类
 */
public class UserUtil {
    /**
     * 用户类型 ：1使用方 ，2制造方，3平台方
     */
    private static int userType = 1 ;

    /**
     * 设置用户类型 ：1使用方 ，2制造方，3平台方
     */
    public static void setUserType(int userType) {
        UserUtil.userType = userType;
    }

    /**
     *  获取用户类型 ：1使用方 ，2制造方，3平台方
     */
    public static int getUserType() {
        return userType;
    }


}

