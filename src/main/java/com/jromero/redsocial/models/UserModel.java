package com.jromero.redsocial.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "USUTBL")
@JsonIgnoreProperties({
    "posts",
    "sharedPosts",
    "likes",
    "comments",
    "sentMessages",
    "receivedMessages",
    "files",
    "auth",
    "enabled",
    "credentialsNonExpired",
    "accountNonExpired",
    "password",
    "username",
    "authorities",
    "accountNonLocked"
})
public class UserModel implements UserDetails {

    @Id
    @Column(name = "USUID")
    private Integer id;

    @Column(name = "USUNOM")
    private String names;

    @Column(name = "USUNICK")
    private String nick;

    @Column(name = "USUEMAIL")
    private String email;

    @Column(name = "USUGEN")
    private String sex;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "USUFECHNAC")
    private Date bornDate;

    @Column(name = "CREATEDAT")
    private Timestamp createdAt;

    @Column(name = "UPDATEDAT")
    private Timestamp updatedAt;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PostModel> posts;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private UserAuth auth;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<SharedModel> sharedPosts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<LikesModel> likes;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<CommentModel> comments;

    @OneToMany(mappedBy = "sentBy", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<MessageModel> sentMessages;

    @OneToMany(mappedBy = "receivedBy", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<MessageModel> receivedMessages;

    @ManyToMany(cascade = {
        CascadeType.MERGE,
        CascadeType.PERSIST
    })
    @JoinTable(
        name = "USUTBL_HAS_FILES", 
        joinColumns = {@JoinColumn(name = "USUID")},
        inverseJoinColumns = {@JoinColumn(name = "FILEID")}
    )
    private Set<FilesModel> files;

    public Set<FilesModel> getFiles() {
        return this.files;
    }

    public void setFiles(Set<FilesModel> files) {
        this.files = files;
    }

    public Set<MessageModel> getReceivedMessages() {
        return this.receivedMessages;
    }

    public void setReceivedMessages(Set<MessageModel> receivedMessages) {
        this.receivedMessages = receivedMessages;
    }

    public Set<MessageModel> getSentMessages() {
        return this.sentMessages;
    }

    public void setSentMessages(Set<MessageModel> sentMessages) {
        this.sentMessages = sentMessages;
    }

    public Set<CommentModel> getComments() {
        return this.comments;
    }

    public void setComments(Set<CommentModel> comments) {
        this.comments = comments;
    }

    public Set<LikesModel> getLikes() {
        return this.likes;
    }

    public void setLikes(Set<LikesModel> likes) {
        this.likes = likes;
    }

    public Set<SharedModel> getSharedPosts() {
        return this.sharedPosts;
    }

    public void setSharedPosts(Set<SharedModel> sharedPosts) {
        this.sharedPosts = sharedPosts;
    }

    public Set<PostModel> getPosts() {
        return this.posts;
    }

    public void setPosts(Set<PostModel> posts) {
        this.posts = posts;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBornDate() {
        return bornDate;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public UserAuth getAuth() {
        return this.auth;
    }

    public void setAuth(UserAuth auth) {
        this.auth = auth;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getPassword() {
        return this.auth.getPassword();
    }

    @Override
    public String getUsername() {
        return this.getNick();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
