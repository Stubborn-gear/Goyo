package com.htdata.goyo.util.eventbus;

/**
 * cuibo
 * 2018/5/3 16:32
 */

public class EventBusMessage {


    private int code ; //消息状态码
    private String message ; // 消息内容
    private int messageType ;
    private String startTime ;
    private String endTime ;


    public EventBusMessage() {}

    public EventBusMessage(int code) {
        this.code = code;
    }

    public EventBusMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }
}
