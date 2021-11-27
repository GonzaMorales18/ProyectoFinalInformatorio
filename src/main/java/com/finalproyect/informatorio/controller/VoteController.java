package com.finalproyect.informatorio.controller;

import javax.persistence.EntityNotFoundException;

import com.finalproyect.informatorio.entity.Entrepreneurship;
import com.finalproyect.informatorio.entity.User;
import com.finalproyect.informatorio.entity.Vote;
import com.finalproyect.informatorio.repository.EntrepreneurshipRepository;
import com.finalproyect.informatorio.repository.UserRepository;
import com.finalproyect.informatorio.repository.VoteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VoteController {

    private VoteRepository voteRepository;
    private UserRepository userRepository;
    private EntrepreneurshipRepository entrepreneurshipRepository;

    @Autowired
    public VoteController(VoteRepository voteRepository, UserRepository userRepository, EntrepreneurshipRepository entrepreneurshipRepository){
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.entrepreneurshipRepository = entrepreneurshipRepository;
    }

    @GetMapping(value = "/votes")
    public ResponseEntity<Vote> getAll(){
        return new ResponseEntity(voteRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/user={idUser}/vote/ent={IdEntrepreneurship}")
    public ResponseEntity<?> setVote(@PathVariable ("idUser") Long idUser, @PathVariable ("IdEntrepreneurship") Long IdEntrepreneurship, @RequestBody Vote vote){
        User user = userRepository.findById(idUser).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        vote.setUser(user);
        Entrepreneurship entrepreneurship = entrepreneurshipRepository.findById(IdEntrepreneurship).orElseThrow(() -> new EntityNotFoundException("Emprendimiento no encontrado"));
        vote.setEntrepreneurship(entrepreneurship);
        return new ResponseEntity(voteRepository.save(vote), HttpStatus.CREATED);
    }

}
