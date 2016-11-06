package com.iware.lottery.admin.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by johnma on 2016/11/3.
 */
@Entity
@Table(name = "userRole")
public class UserRole implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private UserRolePK userRolePK;

    public UserRolePK getUserRolePK() {
        return userRolePK;
    }

    public void setUserRolePK(UserRolePK userRolePK) {
        this.userRolePK = userRolePK;
    }

    @Override
    public String toString(){
        return  "UserRole{" + "user=" + userRolePK.getUser().getId() +", role=" + userRolePK.getRole().getId()  + "}";
    }
}
