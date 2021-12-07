package com.finalproyect.informatorio.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.finalproyect.informatorio.dto.UserType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private LocalDate creationDate;
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vote> votes = new ArrayList<>();
    @NotBlank
    private String name;
    @NotBlank
    private String lastName;
    @NotBlank
    private String city;
    @NotBlank
    private String province;
    @NotBlank
    private String country;
    @Column(unique = true, nullable = false)
    @Email(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank
    private String password;
    @NotBlank
    private UserType userType;
    @JsonIgnore
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Entrepreneurship> entrepreneurships = new ArrayList<>();

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public LocalDate getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public UserType getUserType() {
        return userType;
    }
    public void setUserType(UserType userType) {
        this.userType = userType;
    }
    public List<Vote> getVotes() {
        return votes;
    }
    public void addVote(Vote vote) {
        votes.add(vote);
        vote.setUser(this);
    }
    public void removeVote(Vote vote) {
        votes.remove(vote);
        vote.setUser(null);
    }
    public List<Entrepreneurship> getEntrepreneurships() {
        return entrepreneurships;
    }
    public void addEntrepreneurship(Entrepreneurship entrepreneurship) {
        entrepreneurships.add(entrepreneurship);
        entrepreneurship.setOwner(this);
    }
    public void removeEntrepreneurship(Entrepreneurship entrepreneurship) {
        entrepreneurships.remove(entrepreneurship);
        entrepreneurship.setOwner(null);
    }
}
