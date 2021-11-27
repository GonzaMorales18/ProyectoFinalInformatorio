package com.finalproyect.informatorio.controller;

import com.finalproyect.informatorio.entity.Event;
import com.finalproyect.informatorio.repository.EventRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

    private EventRepository eventRepository;

    @Autowired
    public EventController(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    @GetMapping(value = "/events")
    public ResponseEntity<Event> getAll(){
        return new ResponseEntity(eventRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/event")
    public ResponseEntity<?> set(@RequestBody Event event){
        return new ResponseEntity(eventRepository.save(event), HttpStatus.CREATED);
        
    }

}