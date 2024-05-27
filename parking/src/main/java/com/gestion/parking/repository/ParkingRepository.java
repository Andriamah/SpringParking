package com.gestion.parking.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.gestion.parking.modele.Parking;
import com.gestion.parking.view.V_parking;

public interface ParkingRepository extends JpaRepository<Parking, Integer>{

	@Query(nativeQuery = true, value="select * from V_parking")
	public List<V_parking> findListParking();
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value="update parking set disponibilite = ?1 where id = ?2")
	public void updateParking(boolean disponibilite,int idParking);
	
	@Query(nativeQuery = true, value="select * from v_parking where idparking = ?1")
	public V_parking findByIdParking(int idParking);
	
	@Query(nativeQuery = true, value="select possibilite(?1,?2,?3)")
	public int findByPossibilite(Date sortie,Date debut,int parking);
	
	//select count(*) from V_parking where disponibilite = true
	
	@Query(nativeQuery = true, value="select count(*) from V_parking where disponibilite  > 0 ")
	public int countLibre();
	
	@Query(nativeQuery = true, value="select count(*) from V_parking where disponibilite = 0")
	public int countOccupe();
	
	@Query(nativeQuery = true, value="select count(*) from V_parking where disponibilite = -1")
	public int countInfraction();
	
	@Query(nativeQuery = true, value="select count(*) from parking ")
	public int countParking();
}
