package com.finalproyect.informatorio.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private LocalDateTime creationDate;
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Entrepreneurship> entrepreneurships = new ArrayList<>();

    private LocalDateTime closeDate;
    private String details;
    private String status;
    private Long prize;

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
    public LocalDateTime getCloseDate() {
        return closeDate;
    }
    public void setCloseDate(LocalDateTime closeDate) {
        this.closeDate = closeDate;
    }
    public String getDetails() {
        return details;
    }
    public void setDetails(String details) {
        this.details = details;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Long getPrize() {
        return prize;
    }
    public void setPrize(Long prize) {
        this.prize = prize;
    }
    public void addCartLine(Entrepreneurship entrepreneurship) {
        entrepreneurships.add(entrepreneurship);
        entrepreneurship.setEvent(this);
    }
    public void removeCartLine(Entrepreneurship entrepreneurship) {
        entrepreneurships.remove(entrepreneurship);
        entrepreneurship.setEvent(null);
    }
    public void setEntrepreneurships(List<Entrepreneurship> entrepreneurships) {
        this.entrepreneurships = entrepreneurships;
    }
}