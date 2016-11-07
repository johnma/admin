package com.iware.lottery.admin.domain;


import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by johnma on 2016/11/7.
 */
public class UserRolePK  implements Serializable {

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    @ManyToOne
    @JoinColumn(name="roleId")
    private Role role;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString(){
        return  "UserRolePK{" +", user'sname=" + user.getName() + ", user's role=" + role.getName()+"}";
    }

}
