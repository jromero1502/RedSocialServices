package com.jromero.redsocial.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "USUAUTH")
@JsonIgnoreProperties({"id", "user"})
public class UserAuth {
    @Id
    private Integer id;

    @MapsId()
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USUID")
    private UserModel user;


    @Column(name = "USUPASS")
    private String password;

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserModel getUser() {
        return this.user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
