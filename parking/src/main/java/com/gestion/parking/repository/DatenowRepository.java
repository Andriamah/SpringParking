package com.gestion.parking.repository;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.gestion.parking.modele.Admin;
import com.gestion.parking.modele.Datenow;

public interface DatenowRepository extends JpaRepository<Datenow, Integer> {

	@Query(nativeQuery = true, value = "select getNow()")
	public Date getNow();

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update datenow set date_projet = ?1")
	public void modifieDateNow(Date date);
}
