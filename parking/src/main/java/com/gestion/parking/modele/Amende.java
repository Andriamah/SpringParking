package com.gestion.parking.modele;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Amende {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
    
    int id_reservation;
	BigDecimal depassement;
	BigDecimal valeur;
	public Amende(int id, int id_reservation, BigDecimal depassement, BigDecimal valeur) {
		super();
		this.id = id;
		this.id_reservation = id_reservation;
		this.depassement = depassement;
		this.valeur = valeur;
	}
	public Amende(int id_reservation, BigDecimal depassement, BigDecimal valeur) {
		super();
		this.id_reservation = id_reservation;
		this.depassement = depassement;
		this.valeur = valeur;
	}
	public Amende() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_reservation() {
		return id_reservation;
	}
	public void setId_reservation(int id_reservation) {
		this.id_reservation = id_reservation;
	}
	public BigDecimal getDepassement() {
		return depassement;
	}
	public void setDepassement(BigDecimal depassement) {
		this.depassement = depassement;
	}
	public BigDecimal getValeur() {
		return valeur;
	}
	public void setValeur(BigDecimal valeur) {
		this.valeur = valeur;
	}
	
	
}
