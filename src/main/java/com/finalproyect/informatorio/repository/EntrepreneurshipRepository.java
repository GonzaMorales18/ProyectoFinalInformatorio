package com.finalproyect.informatorio.repository;

import com.finalproyect.informatorio.entity.Entrepreneurship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrepreneurshipRepository extends JpaRepository<Entrepreneurship, Long> {

}
