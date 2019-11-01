package com.htdata.goyo.util.chart;

import java.util.List;

/**
 * cuibo
 * 2018/5/8 11:34
 */

public class RunHistoryModel {


    private List<String> date;
    private List<Double> stop;
    private List<Double> standby;
    private List<Double> run;

    public List<String> getDate() {
        return date;
    }

    public void setDate(List<String> date) {
        this.date = date;
    }

    public List<Double> getStop() {
        return stop;
    }

    public void setStop(List<Double> stop) {
        this.stop = stop;
    }

    public List<Double> getStandby() {
        return standby;
    }

    public void setStandby(List<Double> standby) {
        this.standby = standby;
    }

    public List<Double> getRun() {
        return run;
    }

    public void setRun(List<Double> run) {
        this.run = run;
    }
}
