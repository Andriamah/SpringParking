package com.gestion.parking.modele;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Reservation {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
    
    int id_client;
	int id_parking;
	String matriculation ;
	Date date_debut;
	Date date_fin;
	int etat;
	Date date_quitter;
	int id_tarif;
	
	
	public Reservation(int id_client, int id_parking, String matriculation, Date date_debut, Date date_fin, int etat,
			Date date_quitter, int id_tarif) {
		super();
		this.id_client = id_client;
		this.id_parking = id_parking;
		this.matriculation = matriculation;
		this.date_debut = date_debut;
		this.date_fin = date_fin;
		this.etat = etat;
		this.date_quitter = date_quitter;
		this.id_tarif = id_tarif;
	}
	public Reservation(int id, int id_client, int id_parking, String matriculation, Date date_debut, Date date_fin,
			int etat, Date date_quitter, int id_tarif) {
		super();
		this.id = id;
		this.id_client = id_client;
		this.id_parking = id_parking;
		this.matriculation = matriculation;
		this.date_debut = date_debut;
		this.date_fin = date_fin;
		this.etat = etat;
		this.date_quitter = date_quitter;
		this.id_tarif = id_tarif;
	}
	public Reservation() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_client() {
		return id_client;
	}
	public void setId_client(int id_client) {
		this.id_client = id_client;
	}
	public int getId_parking() {
		return id_parking;
	}
	public void setId_parking(int id_parking) {
		this.id_parking = id_parking;
	}
	public String getMatriculation() {
		return matriculation;
	}
	public void setMatriculation(String matriculation) {
		this.matriculation = matriculation;
	}
	public Date getDate_debut() {
		return date_debut;
	}
	public void setDate_debut(Date date_debut) {
		this.date_debut = date_debut;
	}
	public Date getDate_fin() {
		return date_fin;
	}
	public void setDate_fin(Date date_fin) {
		this.date_fin = date_fin;
	}
	public int getEtat() {
		return etat;
	}
	public void setEtat(int etat) {
		this.etat = etat;
	}
	
	
}
