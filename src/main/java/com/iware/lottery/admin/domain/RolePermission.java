package com.iware.lottery.admin.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by johnma on 2016/11/3.
 */
@Entity
@Table(name = "RolePermission")
//@Table(name = "rolePermission", indexes = {@Index(name = "RolePermission",  column_names = {"roleId", "permission"})})
class RolePermission implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "roleId")
    @ManyToOne(optional = false)
    private Role role;

    @Column(name = "permission")
    @Size(max=50)
    private String permission;

    @Column(name = "createdDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @PrePersist
    public void prePersist(){
        this.createdDate = new Date();
    }

    @Override
    public String toString(){
        return  "RolePermission{" + "role=" + role.getId() +", permission=" + permission + ", createdDate = " + createdDate + "}";
    }
}
