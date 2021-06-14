package com.jromero.redsocial.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SHAREDTBL")
public class SharedModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SHAREDID")
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "USUID")
    private UserModel user;

    @ManyToOne
    @JoinColumn(name = "POSTID")
    private PostModel post;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserModel getUser() {
        return this.user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public PostModel getPost() {
        return this.post;
    }

    public void setPost(PostModel post) {
        this.post = post;
    }

}
