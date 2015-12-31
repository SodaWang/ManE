package com.hshy41.mane.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/12/29.
 */
public class LoginEntity implements Serializable {

    /**
     * id : 5
     * username : 18600364741
     * mobile : 18600364741
     * cnname : 18600364741
     * face : null
     * integral : 0
     * expval : 0
     * money : 0
     * sum : 0
     */

    private String id;
    private String username;
    private String mobile;
    private String cnname;
    private String face;
    private String integral;
    private String expval;
    private String money;
    private String sum;

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setCnname(String cnname) {
        this.cnname = cnname;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public void setExpval(String expval) {
        this.expval = expval;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getMobile() {
        return mobile;
    }

    public String getCnname() {
        return cnname;
    }

    public String getFace() {
        return face;
    }

    public String getIntegral() {
        return integral;
    }

    public String getExpval() {
        return expval;
    }

    public String getMoney() {
        return money;
    }

    public String getSum() {
        return sum;
    }
}
