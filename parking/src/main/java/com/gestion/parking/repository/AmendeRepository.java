package com.gestion.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.parking.modele.Amende;

public interface AmendeRepository extends JpaRepository<Amende, Integer> {

}
