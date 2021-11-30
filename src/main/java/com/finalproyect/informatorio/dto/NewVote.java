package com.finalproyect.informatorio.dto;

public class NewVote {

    private Long idUser;
    private Long idEntrepreneurship;
    private String generatedIn;

    public Long getIdUser() {
        return idUser;
    }
    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }
    public Long getIdEntrepreneurship() {
        return idEntrepreneurship;
    }
    public void setIdEntrepreneurship(Long idEntrepreneurship) {
        this.idEntrepreneurship = idEntrepreneurship;
    }
    public String getGeneratedIn() {
        return generatedIn;
    }
    public void setGeneratedIn(String generatedIn) {
        this.generatedIn = generatedIn;
    }
}

