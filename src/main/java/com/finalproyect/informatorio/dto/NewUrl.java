package com.finalproyect.informatorio.dto;

import javax.validation.constraints.NotBlank;

public class NewUrl {

    @NotBlank
    private Long idEntrepreneurship;
    @NotBlank
    private String url;

    public Long getIdEntrepreneurship() {
        return idEntrepreneurship;
    }
    public void setIdEntrepreneurship(Long idEntrepreneurship) {
        this.idEntrepreneurship = idEntrepreneurship;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
