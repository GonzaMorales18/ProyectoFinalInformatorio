package com.finalproyect.informatorio.repository;

import com.finalproyect.informatorio.entity.Vote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

}
