package com.jromero.redsocial.models;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "COMMENTTBL")
public class CommentModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENTID")
    private Integer id;

    @Column(name = "COMMENTDESC")
    private String description;

    @Column(name = "CREATEDAT")
    private Timestamp createdAt;

    @Column(name = "UPDATEDAT")
    private Timestamp updatedAt;

    @ManyToOne()
    @JoinColumn(name = "POSTID")
    private PostModel post;

    @ManyToOne()
    @JoinColumn(name = "AUTHOR")
    private UserModel user;

    @ManyToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    },
    fetch = FetchType.LAZY)
    @JoinTable(
        joinColumns = {@JoinColumn(name = "COMMENTID")},
        inverseJoinColumns = {@JoinColumn(name = "FILEID")}
    )
    private Set<FilesModel> files;

    public Set<FilesModel> getFiles() {
        return this.files;
    }

    public void setFiles(Set<FilesModel> files) {
        this.files = files;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public PostModel getPost() {
        return this.post;
    }

    public void setPost(PostModel post) {
        this.post = post;
    }

    public UserModel getUser() {
        return this.user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

}
