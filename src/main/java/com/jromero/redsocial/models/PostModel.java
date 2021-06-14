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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "POSTTBL")
public class PostModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POSTID")
    private Integer id;

    @Column(name = "POSTTITLE")
    private String title;

    @Column(name = "POSTDESC")
    private String description;

    @Column(name = "CREATEDAT")
    private Timestamp createdAt;

    @Column(name = "UPDATEDAT")
    private Timestamp updatedAt;

    @ManyToOne()
    @JoinColumn(name = "AUTHOR")
    private UserModel author;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<SharedModel> sharedPosts;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<LikesModel> likes;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<CommentModel> comments;

    @ManyToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    @JoinTable(
        name = "POSTTBL_HAS_FILES",
        joinColumns = {@JoinColumn(name = "POSTID")},
        inverseJoinColumns = {@JoinColumn(name = "FILEID")}
    )
    private Set<FilesModel> files;

    public Set<FilesModel> getFiles() {
        return this.files;
    }

    public void setFiles(Set<FilesModel> files) {
        this.files = files;
    }

    public Set<CommentModel> getComments() {
        return this.comments;
    }

    public void setComments(Set<CommentModel> comments) {
        this.comments = comments;
    }

    public Set<SharedModel> getSharedPosts() {
        return this.sharedPosts;
    }

    public void setSharedPosts(Set<SharedModel> sharedPosts) {
        this.sharedPosts = sharedPosts;
    }

    public Set<LikesModel> getLikes() {
        return this.likes;
    }

    public void setLikes(Set<LikesModel> likes) {
        this.likes = likes;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public UserModel getAuthor() {
        return this.author;
    }

    public void setAuthor(UserModel author) {
        this.author = author;
    }

}
