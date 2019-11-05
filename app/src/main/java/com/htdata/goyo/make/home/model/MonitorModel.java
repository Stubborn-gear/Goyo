package com.htdata.goyo.make.home.model;

import java.util.List;

/**
 * @作者：cb
 * @日期：2019-11-04 17:23
 * @描述：
 */
public class MonitorModel {

    private String region ;
    private String model ;
    private List<String> list ;

    public void setRegion(String region) {
        this.region = region;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public String getRegion() {
        return region;
    }

    public String getModel() {
        return model;
    }

    public List<String> getList() {
        return list;
    }
}
