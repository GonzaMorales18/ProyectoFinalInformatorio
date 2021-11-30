package com.finalproyect.informatorio.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private LocalDateTime creationDate;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @OneToOne(fetch = FetchType.LAZY)
    private Entrepreneurship entrepreneurship;

    private String generatedIn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Entrepreneurship getEntrepreneurship() {
        return entrepreneurship;
    }

    public void setEntrepreneurship(Entrepreneurship entrepreneurship) {
        this.entrepreneurship = entrepreneurship;
    }

    public String getGeneratedIn() {
        return generatedIn;
    }

    public void setGeneratedIn(String generatedIn) {
        this.generatedIn = generatedIn;
    }

}
