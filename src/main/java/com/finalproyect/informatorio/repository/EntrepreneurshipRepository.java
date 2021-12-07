package com.finalproyect.informatorio.repository;

import java.util.List;

import com.finalproyect.informatorio.entity.Entrepreneurship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrepreneurshipRepository extends JpaRepository<Entrepreneurship, Long> {

    @Query("FROM Entrepreneurship WHERE tags LIKE %:tag%")
    List<Entrepreneurship> findByTag(@Param("tag") String tag);

    @Query("FROM Entrepreneurship WHERE publicated = false")
    List<Entrepreneurship> findByStatus();

    /* @Query(value="FROM Entrepreneurship WHERE event_id = ?1 ORDER BY id DESC LIMIT :lim", nativeQuery = true)
    List<Entrepreneurship> rankingForVotes(@Param("idEvent") Long idEvent); */

}
