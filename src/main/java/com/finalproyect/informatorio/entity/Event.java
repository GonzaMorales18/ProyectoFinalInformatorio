package com.finalproyect.informatorio.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.finalproyect.informatorio.dto.EventStatus;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private LocalDate creationDate;
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Entrepreneurship> entrepreneurships = new ArrayList<>();
    private LocalDate closeDate;
    private String details;
    @NotBlank
    private EventStatus eventStatus;
    @NotBlank
    private Long prize;

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
    public LocalDate getCloseDate() {
        return closeDate;
    }
    public void setCloseDate(LocalDate closeDate) {
        this.closeDate = closeDate;
    }
    public String getDetails() {
        return details;
    }
    public void setDetails(String details) {
        this.details = details;
    }
    public EventStatus getEventStatus() {
        return eventStatus;
    }
    public void setEventStatus(EventStatus eventStatus) {
        this.eventStatus = eventStatus;
    }
    public Long getPrize() {
        return prize;
    }
    public void setPrize(Long prize) {
        this.prize = prize;
    }
    public List<Entrepreneurship> getEntrepreneurships() {
        return entrepreneurships;
    }
    public void addEntrepreneurships(Entrepreneurship entrepreneurship) {
        entrepreneurships.add(entrepreneurship);
        entrepreneurship.setEvent(this);
    }
    public void removentrepreneurship(Entrepreneurship entrepreneurship) {
        entrepreneurships.remove(entrepreneurship);
        entrepreneurship.setEvent(null);
    }
}