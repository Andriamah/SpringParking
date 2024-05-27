package com.gestion.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.parking.modele.Tarif;

public interface TarifRepository extends JpaRepository<Tarif, Integer> {

}
