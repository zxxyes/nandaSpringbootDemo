package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity // This tells Hibernate to make a table out of this class
@Table(name="joke")
@EntityListeners(AuditingEntityListener.class)

@JsonIgnoreProperties(value = {"createdAt","updatedAt"}, allowGetters = true)

public class Joke {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @Column(name = "created_on",nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    public Joke(){
    }
    public Joke(String title){
        this.title= title;
    }

    public Integer getId() {
        return id;
    }

    public Date getCreatedAt(){
        return createdAt;
    }

    public Date getUpdatedAt(){
        return updatedAt;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
