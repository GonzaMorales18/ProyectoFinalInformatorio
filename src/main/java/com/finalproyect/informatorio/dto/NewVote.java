package com.finalproyect.informatorio.dto;

import javax.validation.constraints.NotBlank;

public class NewVote {

    @NotBlank
    private Long idUser;
    @NotBlank
    private Long idEntrepreneurship;
    @NotBlank
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

