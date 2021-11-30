package com.finalproyect.informatorio.controller;

import javax.persistence.EntityNotFoundException;

import com.finalproyect.informatorio.entity.Entrepreneurship;
import com.finalproyect.informatorio.repository.EntrepreneurshipRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EntrepreneurshipController {

    private EntrepreneurshipRepository entrepreneurshipRepository;

    @Autowired
    public EntrepreneurshipController(EntrepreneurshipRepository entrepreneurshipRepository){
        this.entrepreneurshipRepository = entrepreneurshipRepository;
    }

    // ALTA
    @PostMapping(value = "/entrepreneurship")
    public ResponseEntity<?> set(@RequestBody Entrepreneurship entrepreneurship){
        return new ResponseEntity(entrepreneurshipRepository.save(entrepreneurship), HttpStatus.CREATED);
    }

    // MOSTRAR TODOS
    @GetMapping(value = "/entrepreneurship")
    public ResponseEntity<Entrepreneurship> getAll(){
        return new ResponseEntity(entrepreneurshipRepository.findAll(), HttpStatus.OK);
    }

     // BAJA
     @DeleteMapping("entrepreneurship/{idEntrepreneurship}/delete")
     public HttpStatus delete(@PathVariable ("idEntrepreneurship") Long idEntrepreneurship){
        Entrepreneurship entrepreneurship = entrepreneurshipRepository.findById(idEntrepreneurship).orElseThrow(() -> new EntityNotFoundException("Emprendimiento no encontrado"));
        entrepreneurshipRepository.delete(entrepreneurship);
        return HttpStatus.ACCEPTED;
     }

     // MODIFICACIÓN
    @PutMapping("entrepreneurship/{idEntrepreneurship}/modify")
    public ResponseEntity<?> modifiy(@RequestBody Entrepreneurship entrepreneurship, @PathVariable ("idEntrepreneurship") Long idEntrepreneurship){
        Entrepreneurship newEntrepreneurship = entrepreneurshipRepository.findById(idEntrepreneurship).orElseThrow(() -> new EntityNotFoundException("Emprendimiento no encontrado"));
        newEntrepreneurship.setEvent(entrepreneurship.getEvent());
        newEntrepreneurship.setName(entrepreneurship.getName());
        newEntrepreneurship.setDescription(entrepreneurship.getDescription());
        newEntrepreneurship.setContent(entrepreneurship.getContent());
        newEntrepreneurship.setObjetive(entrepreneurship.getObjetive());
        newEntrepreneurship.setTags(entrepreneurship.getTags());
        newEntrepreneurship.setPublicated(entrepreneurship.isPublicated());
        return new ResponseEntity(entrepreneurshipRepository.save(newEntrepreneurship),HttpStatus.CREATED);
    }

    // PUBLICAR
    @PutMapping("entrepreneurship/{idEntrepreneurship}/publish")
    public ResponseEntity<?> publish(@RequestBody Entrepreneurship entrepreneurship, @PathVariable ("idEntrepreneurship") Long idEntrepreneurship){
        Entrepreneurship newEntrepreneurship = entrepreneurshipRepository.findById(idEntrepreneurship).orElseThrow(() -> new EntityNotFoundException("Emprendimiento no encontrado"));
        newEntrepreneurship.setPublicated(entrepreneurship.isPublicated());
        return new ResponseEntity(entrepreneurshipRepository.save(newEntrepreneurship),HttpStatus.CREATED);
    }

}
