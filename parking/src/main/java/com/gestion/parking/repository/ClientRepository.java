package com.gestion.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gestion.parking.modele.Admin;
import com.gestion.parking.modele.Client;
public interface ClientRepository extends JpaRepository<Client, Integer>{
	
	@Query(nativeQuery = true, value="select * from client where nom =?1 and mdp=?2")
	public Client findClient(String nom,String mdp);
}
