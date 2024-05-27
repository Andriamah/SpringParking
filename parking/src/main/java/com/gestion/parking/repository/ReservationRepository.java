package com.gestion.parking.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.gestion.parking.modele.Reservation;
import com.gestion.parking.view.Reservation_depasse;
import com.gestion.parking.view.V_parking_heure;
import com.gestion.parking.view.V_reservation_en_cours;


public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

	@Query(nativeQuery = true, value = "select * from v_reservation_en_cours where id_client =?1 ")
	public List<V_reservation_en_cours> findReservationClient(int id_client);
	
	@Query(nativeQuery = true, value = "select *,verifierDisponibilite(?1,date_fin,date_quitter,date_debut) as dispo_heure from v_parking ")
	public List<V_parking_heure> findReservationByDate(Date dateSaisi);
	
	@Query(nativeQuery = true, value = "select * from reservation where id =?1 ")
	public Reservation findReservationById(int reservation);
	
	@Query(nativeQuery = true, value = "select * from Reservation_depasse where id_client =?1 ")
	public List<Reservation_depasse> findReservation_depasse(int idclient);
	
	@Query(nativeQuery = true, value = "select * from Reservation_depasse where id_client =?1 and id = ?2")
	public Reservation_depasse findOneReservation_depasse(int idclient,int idreservation);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value="update reservation set date_quitter = ?1  where id = ?2")
	public void quitterParking(Date date_quitte,int idreservation);
}
