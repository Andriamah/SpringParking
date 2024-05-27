package com.gestion.parking.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gestion.parking.modele.Pointeur;
import com.gestion.parking.modele.Portefeuille;
import com.gestion.parking.view.V_parking;

public interface PointeurRepository extends JpaRepository<Pointeur, Integer>{
	
}
