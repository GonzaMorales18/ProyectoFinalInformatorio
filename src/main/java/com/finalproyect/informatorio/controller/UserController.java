package com.finalproyect.informatorio.controller;

import java.time.LocalDate;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import com.finalproyect.informatorio.entity.User;
import com.finalproyect.informatorio.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    // ALTA
    @PostMapping(value = "/user")
    public ResponseEntity<?> set(@RequestBody @Valid User user){
        return new ResponseEntity(userRepository.save(user), HttpStatus.CREATED);
    }

    // MOSTRAR TODOS
    @GetMapping(value = "/users")
    public ResponseEntity<User> getAll(){
        return new ResponseEntity(userRepository.findAll(), HttpStatus.OK);
    }

    // BAJA
    @DeleteMapping("/user/{idUser}/delete")
    public HttpStatus delete(@PathVariable ("idUser") Long idUser){
        User user = userRepository.findById(idUser).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        userRepository.delete(user);
        return HttpStatus.ACCEPTED;
    }

    // MODIFICACIÓN
    @PutMapping("/user/{idUser}/modify")
    public ResponseEntity<?> modifiy(@RequestBody @Valid User user, @PathVariable ("idUser") Long idUser){
        User newUser = userRepository.findById(idUser).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        newUser.setCity(user.getCity());
        newUser.setProvince(user.getProvince());
        newUser.setCountry(user.getCountry());
        newUser.setPassword(user.getPassword());
        return new ResponseEntity(userRepository.save(newUser),HttpStatus.CREATED);
    }

    // MOSTRAR POR CIUDAD
    @GetMapping(value = "/users/city={city}")
    public ResponseEntity<User> getByCity(@PathVariable ("city") String city){
        return new ResponseEntity(userRepository.findByCity(city), HttpStatus.OK);
    }

    // MOSTRAR POSTERIOR A FECHA
    @GetMapping(value = "/users/date")
    public ResponseEntity<User> getByDate(@RequestParam  @DateTimeFormat (iso = DateTimeFormat.ISO.DATE) LocalDate date){
        return new ResponseEntity(userRepository.findByDate(date), HttpStatus.OK);
    }
}