package com.iware.lottery.admin.model;

import com.iware.lottery.admin.domain.User;

import java.io.Serializable;

import com.iware.lottery.admin.DTOUtils;

/**
 * Created by johnma on 2016/11/2.
 */
public class LoginForm implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String name;

    private String password;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString(){
        return "User{" + "name=" +  name + ", password=" + password + "}";
    }

    public User toEntity(){
        return DTOUtils.map(this, User.class);
    }
}
