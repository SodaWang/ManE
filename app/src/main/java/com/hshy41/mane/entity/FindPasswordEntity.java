package com.hshy41.mane.entity;

/**
 * Created by Administrator on 2015/12/29.
 */
public class FindPasswordEntity {
    /**
     * Result : 1
     * Message : 报错信息
     */

    private String Result;
    private String Message;

    public void setResult(String Result) {
        this.Result = Result;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public String getResult() {
        return Result;
    }

    public String getMessage() {
        return Message;
    }
}
