package com.finalproyect.informatorio.dto;

import javax.validation.constraints.NotBlank;

public class AddEntrepreneurship {

    @NotBlank
    private Long idEvent;
    @NotBlank
    private Long idEntrepreneurship;

    public Long getIdEvent() {
        return idEvent;
    }
    public void setIdEvent(Long idEvent) {
        this.idEvent = idEvent;
    }
    public Long getIdEntrepreneurship() {
        return idEntrepreneurship;
    }
    public void setIdEntrepreneurship(Long idEntrepreneurship) {
        this.idEntrepreneurship = idEntrepreneurship;
    }
    
}
