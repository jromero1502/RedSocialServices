package com.jromero.redsocial.models;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MESSAGESTBL")
public class MessageModel {
    
    @Id
    @Column(name = "MESSAGEID")
    private Integer id;

    @Column(name = "MESSAGEDESC")
    private String description;

    @ManyToOne
    @JoinColumn(name = "SENTBY")
    private UserModel sentBy;

    @ManyToOne
    @JoinColumn(name = "RECEIVEDBY")
    private UserModel receivedBy;

    @Column(name = "CREATEDAT")
    private Timestamp createdAt;

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

    public UserModel getSentBy() {
        return this.sentBy;
    }

    public void setSentBy(UserModel sentBy) {
        this.sentBy = sentBy;
    }

    public UserModel getReceivedBy() {
        return this.receivedBy;
    }

    public void setReceivedBy(UserModel receivedBy) {
        this.receivedBy = receivedBy;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

}
