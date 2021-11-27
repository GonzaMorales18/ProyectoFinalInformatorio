package com.finalproyect.informatorio.controller;

import com.finalproyect.informatorio.entity.Entrepreneurship;
import com.finalproyect.informatorio.repository.EntrepreneurshipRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EntrepreneurshipController {

    private EntrepreneurshipRepository entrepreneurshipRepository;

    @Autowired
    public EntrepreneurshipController(EntrepreneurshipRepository entrepreneurshipRepository){
        this.entrepreneurshipRepository = entrepreneurshipRepository;
    }

    @GetMapping(value = "/entrepreneurship")
    public ResponseEntity<Entrepreneurship> getAll(){
        return new ResponseEntity(entrepreneurshipRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/entrepreneurship")
    public ResponseEntity<?> set(@RequestBody Entrepreneurship entrepreneurship){
        return new ResponseEntity(entrepreneurshipRepository.save(entrepreneurship), HttpStatus.CREATED);
        
    }

}
