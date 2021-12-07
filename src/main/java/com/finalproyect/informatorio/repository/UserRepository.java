package com.finalproyect.informatorio.repository;

import java.time.LocalDate;
import java.util.List;

import com.finalproyect.informatorio.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("FROM User WHERE city LIKE %:name%")
    List<User> findByCity(@Param("name") String city);

    @Query("FROM User WHERE creationDate >= ?1")
    List<User> findByDate(LocalDate date);

}
