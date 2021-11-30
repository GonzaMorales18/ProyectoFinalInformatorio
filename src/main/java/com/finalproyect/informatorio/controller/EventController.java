package com.finalproyect.informatorio.controller;

import java.time.LocalDateTime;

import javax.persistence.EntityNotFoundException;

import com.finalproyect.informatorio.dto.AddEntrepreneurship;
import com.finalproyect.informatorio.entity.Entrepreneurship;
import com.finalproyect.informatorio.entity.Event;
import com.finalproyect.informatorio.repository.EventRepository;
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
public class EventController {

    private EventRepository eventRepository;
    private EntrepreneurshipRepository entrepreneurshipRepository;

    @Autowired
    public EventController(EventRepository eventRepository, EntrepreneurshipRepository entrepreneurshipRepository){
        this.eventRepository = eventRepository;
        this.entrepreneurshipRepository = entrepreneurshipRepository;
    }

    // ALTA
    @PostMapping(value = "/event")
    public ResponseEntity<?> set(@RequestBody Event event){
        return new ResponseEntity(eventRepository.save(event), HttpStatus.CREATED);
        
    }

    // MOSTAR TODOS
    @GetMapping(value = "/events")
    public ResponseEntity<Event> getAll(){
        return new ResponseEntity(eventRepository.findAll(), HttpStatus.OK);
    }

     // BAJA
     @DeleteMapping("event/{idEvent}/delete")
     public HttpStatus delete(@PathVariable ("idEvent") Long idEvent){
        Event event = eventRepository.findById(idEvent).orElseThrow(() -> new EntityNotFoundException("Evento no encontrado"));
        eventRepository.delete(event);
        return HttpStatus.ACCEPTED;
     }

    // INSCRIBIR EMPRENDIMIENTO A EVENTO
    @PutMapping(value = "addEntrepreneurship")
    public ResponseEntity<?> addEntrepreneurship(@RequestBody AddEntrepreneurship addEntrepreneurship) throws Exception{
        Event event = eventRepository.findById(addEntrepreneurship.getIdEvent()).orElseThrow(() -> new EntityNotFoundException("Evento no encontrado"));
        Entrepreneurship entrepreneurship = entrepreneurshipRepository.findById(addEntrepreneurship.getIdEntrepreneurship()).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        event.addEntrepreneurships(entrepreneurship);
        return new ResponseEntity(eventRepository.save(event), HttpStatus.CREATED);
    }

    // MODIFICACIÓN
    @PutMapping("event/{idEvent}/modify")
    public ResponseEntity<?> modifiy(@RequestBody Event event, @PathVariable ("idEvent") Long idEvent){
        Event newEvent = eventRepository.findById(idEvent).orElseThrow(() -> new EntityNotFoundException("Evento no encontrado"));
        newEvent.setEventStatus(event.getEventStatus());
        newEvent.setDetails(event.getDetails());
        newEvent.setPrize(event.getPrize());
        return new ResponseEntity(eventRepository.save(newEvent),HttpStatus.CREATED);
    }
    // CERRAR
    @PutMapping("event/{idEvent}/close")
    public ResponseEntity<?> close(@RequestBody Event event, @PathVariable ("idEvent") Long idEvent){
        Event newEvent = eventRepository.findById(idEvent).orElseThrow(() -> new EntityNotFoundException("Evento no encontrado"));
        newEvent.setEventStatus(event.getEventStatus());
        newEvent.setCloseDate(LocalDateTime.now());
        return new ResponseEntity(eventRepository.save(newEvent),HttpStatus.CREATED);
    }

}