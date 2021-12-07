package com.finalproyect.informatorio.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Entrepreneurship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private LocalDate creationDate;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Event event;
    @OneToMany(mappedBy = "entrepreneurship", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Urls> urls = new ArrayList<>();
    @NotBlank
    private String name;
    private String description;
    private String content;
    @NotBlank
    private Long objetive;
    private String tags;
    @NotBlank
    private boolean publicated;
    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;
    @JsonIgnore
    @OneToMany(mappedBy = "entrepreneurship", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vote> votes = new ArrayList<>();

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDate getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Long getObjetive() {
        return objetive;
    }
    public void setObjetive(Long objetive) {
        this.objetive = objetive;
    }
    public String getTags() {
        return tags;
    }
    public void setTags(String tags) {
        this.tags = tags;
    }
    public boolean isPublicated() {
        return publicated;
    }
    public void setPublicated(boolean publicated) {
        this.publicated = publicated;
    }
    public Event getEvent() {
        return event;
    }
    public void setEvent(Event event) {
        this.event = event;
    }
    public List<Urls> getUrls() {
        return urls;
    }
    public void addUrl(Urls url) {
        urls.add(url);
    }
    public void removeUrl(Urls url) {
        urls.remove(url);
    }
    public User getOwner() {
        return owner;
    }
    public void setOwner(User owner) {
        this.owner = owner;
    }
    public List<Vote> getVotes() {
        return votes;
    }
    public void addVotes(Vote vote) {
        votes.add(vote);
    }
    public void removeVotes(Vote vote) {
        votes.remove(vote);
    }
}