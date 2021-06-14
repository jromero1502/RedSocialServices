package com.jromero.redsocial.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LIKESTBL")
public class LikesModel {

    @Id
    @Column(name = "LIKEID")
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "USUID")
    private UserModel user;

    @ManyToOne()
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
