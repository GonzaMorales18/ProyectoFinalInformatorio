package com.finalproyect.informatorio.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import com.finalproyect.informatorio.dto.NewUrl;
import com.finalproyect.informatorio.entity.Entrepreneurship;
import com.finalproyect.informatorio.entity.Event;
import com.finalproyect.informatorio.entity.Urls;
import com.finalproyect.informatorio.entity.User;
import com.finalproyect.informatorio.repository.EntrepreneurshipRepository;
import com.finalproyect.informatorio.repository.EventRepository;
import com.finalproyect.informatorio.repository.UrlsRepository;
import com.finalproyect.informatorio.repository.UserRepository;

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
    private UrlsRepository urlsRepository;
    private UserRepository userRepository;
    private EventRepository eventRepository;

    @Autowired
    public EntrepreneurshipController(EntrepreneurshipRepository entrepreneurshipRepository, UrlsRepository urlsRepository, UserRepository userRepository, EventRepository eventRepository){
        this.entrepreneurshipRepository = entrepreneurshipRepository;
        this.urlsRepository = urlsRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    // ALTA
    @PostMapping(value = "/entrepreneurship/{idOwner}")
    public ResponseEntity<?> set(@PathVariable ("idOwner") Long idOwner, @RequestBody @Valid Entrepreneurship entrepreneurship){
        User user = userRepository.findById(idOwner).orElseThrow(() -> new EntityNotFoundException("OWNER no encontrado"));
        if (String.valueOf(user.getUserType()) == "OWNER"){
            entrepreneurship.setOwner(user);
            return new ResponseEntity(entrepreneurshipRepository.save(entrepreneurship), HttpStatus.CREATED);
        } else {
            new EntityNotFoundException("El usuario asignado no es OWNER");
            return null;
        }
    }

    // MOSTRAR TODOS
    @GetMapping(value = "/entrepreneurship")
    public ResponseEntity<Entrepreneurship> getAll(){
        return new ResponseEntity(entrepreneurshipRepository.findAll(), HttpStatus.OK);
    }

     // BAJA
     @DeleteMapping("/entrepreneurship/{idEntrepreneurship}/delete")
     public HttpStatus delete(@PathVariable ("idEntrepreneurship") Long idEntrepreneurship){
        Entrepreneurship entrepreneurship = entrepreneurshipRepository.findById(idEntrepreneurship).orElseThrow(() -> new EntityNotFoundException("Emprendimiento no encontrado"));
        entrepreneurshipRepository.delete(entrepreneurship);
        return HttpStatus.ACCEPTED;
     }

     // MODIFICACIÓN
    @PutMapping("/entrepreneurship/{idEntrepreneurship}/modify")
    public ResponseEntity<?> modifiy(@RequestBody @Valid Entrepreneurship entrepreneurship, @PathVariable ("idEntrepreneurship") Long idEntrepreneurship){
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
    @PutMapping("/entrepreneurship/{idEntrepreneurship}/publish")
    public ResponseEntity<?> publish(@RequestBody @Valid Entrepreneurship entrepreneurship, @PathVariable ("idEntrepreneurship") Long idEntrepreneurship){
        Entrepreneurship newEntrepreneurship = entrepreneurshipRepository.findById(idEntrepreneurship).orElseThrow(() -> new EntityNotFoundException("Emprendimiento no encontrado"));
        newEntrepreneurship.setPublicated(entrepreneurship.isPublicated());
        return new ResponseEntity(entrepreneurshipRepository.save(newEntrepreneurship),HttpStatus.CREATED);
    }

    // SUBIR CAPTURAS
    @PostMapping(value = "/url")
    public ResponseEntity<?> setUrl(@RequestBody @Valid NewUrl newUrl){
        Urls urls = new Urls();
        Entrepreneurship entrepreneurship = entrepreneurshipRepository.findById(newUrl.getIdEntrepreneurship()).orElseThrow(() -> new EntityNotFoundException("Emprendimiento no encontrado"));
        urls.setEntrepreneurship(entrepreneurship);
        urls.setUrl(newUrl.getUrl());
        return new ResponseEntity(urlsRepository.save(urls), HttpStatus.CREATED);
    }

    // MOSTRAR POR TAG
    @GetMapping(value = "/entrepreneurship/tag={tag}")
    public ResponseEntity<Entrepreneurship> getByTag(@PathVariable ("tag") String tag){
        return new ResponseEntity(entrepreneurshipRepository.findByTag(tag), HttpStatus.OK);
    }

    // MOSTRAR POR NO PUBLICADOS
    @GetMapping(value = "/entrepreneurship/nonPublicated")
    public ResponseEntity<Entrepreneurship> getByStatus(){
        return new ResponseEntity(entrepreneurshipRepository.findByStatus(), HttpStatus.OK);
    }

    // MOSTAR RANKING POR EVENTO
    @GetMapping(value = "/events/ranking/{idEvent}")
    public ResponseEntity<?> getRanking(@PathVariable ("idEvent") Long idEvent){
        Event event = eventRepository.findById(idEvent).orElseThrow(() -> new EntityNotFoundException("Evento no encontrado"));
        List<Entrepreneurship> ent = event.getEntrepreneurships();
        Map<String, Entrepreneurship> ranking = new HashMap();
        int count = 0;
        Entrepreneurship max = null;
        int size = ent.size();
        for (int i = 0; i < size; ++i){
            for (Entrepreneurship x: ent){
                if (x.getVotes().size() >= count){
                    max = x;
                    count = x.getVotes().size();
                }
            }
            ranking.put("Votos: " + count, max);
            ent.remove(max);
            count = 0;
        }
        Map<String, Entrepreneurship> orderRanking = new TreeMap<String, Entrepreneurship>(Collections.reverseOrder());
        orderRanking.putAll(ranking);
        return new ResponseEntity(orderRanking, HttpStatus.OK);
    } 

}
