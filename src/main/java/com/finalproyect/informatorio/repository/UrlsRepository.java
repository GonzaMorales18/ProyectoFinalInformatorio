package com.finalproyect.informatorio.repository;

import com.finalproyect.informatorio.entity.Urls;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlsRepository extends JpaRepository<Urls, Long> {

}
