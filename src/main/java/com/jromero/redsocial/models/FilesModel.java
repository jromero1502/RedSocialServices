package com.jromero.redsocial.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "FILES")
public class FilesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FILEID")
    private Integer id;

    @Column(name = "FILETYPE")
    private String type;

    @Column(name = "FILEURL")
    private String url;

    @ManyToMany(mappedBy = "files")
    private Set<UserModel> users;

    @ManyToMany(mappedBy = "files")
    private Set<PostModel> posts;

    @ManyToMany(mappedBy = "files")
    private Set<MessageModel> messages;

    @ManyToMany(mappedBy = "files")
    private Set<CommentModel> comments;

    public Set<MessageModel> getMessages() {
        return this.messages;
    }

    public void setMessages(Set<MessageModel> messages) {
        this.messages = messages;
    }

    public Set<CommentModel> getComments() {
        return this.comments;
    }

    public void setComments(Set<CommentModel> comments) {
        this.comments = comments;
    }

    public Set<PostModel> getPosts() {
        return this.posts;
    }

    public void setPosts(Set<PostModel> posts) {
        this.posts = posts;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
