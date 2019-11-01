package com.htdata.goyo.main.make.model;

import com.htdata.goyo.base.BaseModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @作者：cb
 * @日期：2019-11-01 14:18
 * @描述：
 */
public class MakeHomeBarModel extends BaseModel {

    private List<String> keyList = new ArrayList<>();
    private List<String> valueList  = new ArrayList<>();

    public void setKeyList(List<String> keyList) {
        this.keyList = keyList;
    }

    public void setValueList(List<String> valueList) {
        this.valueList = valueList;
    }

    public List<String> getKeyList() {
        return keyList;
    }

    public List<String> getValueList() {
        return valueList;
    }
}
