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

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "userId")
    @ManyToOne(optional = false)
    private User user;

    @JoinColumn(name = "roleId")
    @ManyToOne(optional = false)
    private Role role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
        return  "UserRole{" + "user=" + user.getId() +", role=" + role.getId()  + "}";
    }
}
