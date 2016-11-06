package com.iware.lottery.admin.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by johnma on 2016/11/2.
 */
@Entity
@Table(name = "User")
public class User implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @Size(max=100)
    private String name;

    @Column(name = "email")
    @Size(max = 100)
    private String email;

    @Column(name = "password")
    @Size(max = 100)
    private String password;

    @Column(name = "salt")
    @Size(max = 100)
    private String salt;

    @Column(name = "createdDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
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
        return  "User{" + "id=" + id +", name=" + name + ", email=" + email + ", password=" + password +
                ", salt=" + salt + ", createdDate = " + createdDate + "}";
    }
}
