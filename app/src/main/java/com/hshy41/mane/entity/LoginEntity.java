package com.hshy41.mane.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/12/29.
 */
public class LoginEntity implements Serializable {
    /**
     * id : 2
     * username : admin
     * cnname : 啦啦啦
     * avatar :
     * integral : 1
     * wallet : null
     * downpeople : null
     */

    private String id;
    private String username;
    private String cnname;
    private String avatar;
    private String integral;
    private String wallet;
    private String downpeople;

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setCnname(String cnname) {
        this.cnname = cnname;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }

    public void setDownpeople(String downpeople) {
        this.downpeople = downpeople;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getCnname() {
        return cnname;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getIntegral() {
        return integral;
    }

    public Object getWallet() {
        return wallet;
    }

    public Object getDownpeople() {
        return downpeople;
    }

    @Override
    public String toString() {
        return "LoginEntity{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", cnname='" + cnname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", integral='" + integral + '\'' +
                ", wallet='" + wallet + '\'' +
                ", downpeople='" + downpeople + '\'' +
                '}';
    }
}
