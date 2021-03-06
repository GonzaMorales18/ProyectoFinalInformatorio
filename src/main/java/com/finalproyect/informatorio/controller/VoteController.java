package com.finalproyect.informatorio.controller;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import java.util.List;

import com.finalproyect.informatorio.dto.NewVote;
import com.finalproyect.informatorio.entity.Entrepreneurship;
import com.finalproyect.informatorio.entity.User;
import com.finalproyect.informatorio.entity.Vote;
import com.finalproyect.informatorio.repository.EntrepreneurshipRepository;
import com.finalproyect.informatorio.repository.UserRepository;
import com.finalproyect.informatorio.repository.VoteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.bytebuddy.asm.Advice.AllArguments;

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

    // ALTA
    @PostMapping(value = "/vote")
    public ResponseEntity<?> setVote(@RequestBody @Valid NewVote newVote){
        User user = userRepository.findById(newVote.getIdUser()).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        Entrepreneurship entrepreneurship = entrepreneurshipRepository.findById(newVote.getIdEntrepreneurship()).orElseThrow(() -> new EntityNotFoundException("Emprendimiento no encontrado"));
        Vote vote = new Vote();
        boolean flag = true;
        if (String.valueOf(user.getUserType()) == "USUARIO" || String.valueOf(user.getUserType()) == "COLABORADOR"){
            for (Vote x: voteRepository.findAll()){
                if (x.getEntrepreneurship() == entrepreneurship && x.getUser() == user){
                    flag = false;
                } 
            }
            if (flag == true){
                vote.setUser(user);
                vote.setEntrepreneurship(entrepreneurship);
                vote.setGeneratedIn(newVote.getGeneratedIn());
                return new ResponseEntity(voteRepository.save(vote), HttpStatus.CREATED);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    // MOSTRAR TODOS
    @GetMapping(value = "/votes")
    public ResponseEntity<Vote> getAll(){
        return new ResponseEntity(voteRepository.findAll(), HttpStatus.OK);
    }

    // BAJA
    @DeleteMapping("/vote/{idVote}/delete")
    public HttpStatus delete(@PathVariable ("idVote") Long idVote){
       Vote vote = voteRepository.findById(idVote).orElseThrow(() -> new EntityNotFoundException("Voto no encontrado"));
       voteRepository.delete(vote);
       return HttpStatus.ACCEPTED;
    }

    // MOSTRAR POR USUARIO
    @GetMapping(value = "/votes/{idUser}")
    public ResponseEntity<User> getByUserId(@PathVariable ("idUser") Long idUser){
        User user = userRepository.findById(idUser).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        user.getVotes();
        return new ResponseEntity(user.getVotes(), HttpStatus.OK);
    }

}
