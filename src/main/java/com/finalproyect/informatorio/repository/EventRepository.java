package com.finalproyect.informatorio.repository;

import com.finalproyect.informatorio.entity.Event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

}