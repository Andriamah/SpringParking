package com.gestion.parking.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.gestion.parking.modele.Portefeuille;
import com.gestion.parking.view.Compte_protefeuille;
import com.gestion.parking.view.V_portefeuille;



public interface PortefeuilleRepository extends JpaRepository<Portefeuille, Integer>{
	
	@Query(nativeQuery = true, value="select * from v_portefeuille where etat = 0")
	public List<V_portefeuille> findlistePortefeuille();
	
	@Query(nativeQuery = true, value="select * from v_portefeuille where id_client = ?1 and etat = 1")
	public List<V_portefeuille> findlistePortefeuilleByClient(int idClient);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value="update portefeuille set etat = 1 where id = ?1")
	public void validerPortefuille(int idRecharge);
	
	@Query(nativeQuery = true, value="select * from compte_protefeuille where id_client = ?1")
	public Compte_protefeuille findLeComptePortefeuille(int idClient);

}
