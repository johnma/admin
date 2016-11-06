package com.iware.lottery.admin.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by johnma on 2016/11/3.
 */
@Entity
@Table(name = "role")
public class Role implements Serializable{
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

    @Column(name = "desc")
    @Size(max=500)
    private String desc;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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
        return  "Role{" + "id=" + id +", name=" + name + ", desc=" + desc + ", createdDate=" + createdDate + "}";
    }
}
