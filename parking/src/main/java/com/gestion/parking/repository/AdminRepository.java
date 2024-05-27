package com.gestion.parking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gestion.parking.modele.Admin;
import com.gestion.parking.view.V_parking;

public interface AdminRepository extends JpaRepository<Admin, Integer>{

	@Query(nativeQuery = true, value="select * from admin where nom =?1 and mdp=?2")
	public Admin findAdmin(String nom,String mdp);
}
