package com.iware.lottery.admin.model;

import java.io.Serializable;
import java.util.Date;
/**
 * Created by wuhao on 16/11/6.
 */


public class Token implements Serializable {

    private String token;

    private Date tokenDate;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getTokenDate() {
        return tokenDate;
    }

    public void setTokenDate(Date tokenDate) {
        this.tokenDate = tokenDate;
    }
}
