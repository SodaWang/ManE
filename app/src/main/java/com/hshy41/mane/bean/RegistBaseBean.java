package com.hshy41.mane.bean;

import com.hshy41.mane.entity.RegistEntity;

/**
 * Created by Administrator on 2015/12/29.
 */
public class RegistBaseBean {

    /**
     * SeqNo : 123123
     * Result : 1
     * Message : 账号或密码错误
     */

    private String SeqNo;
    private String Result;
    private String Message;
    public RegistEntity data;

    public void setSeqNo(String SeqNo) {
        this.SeqNo = SeqNo;
    }

    public void setResult(String Result) {
        this.Result = Result;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public String getSeqNo() {
        return SeqNo;
    }

    public String getResult() {
        return Result;
    }

    public String getMessage() {
        return Message;
    }
}
