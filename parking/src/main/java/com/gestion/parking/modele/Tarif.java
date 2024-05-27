package com.gestion.parking.modele;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tarif {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
    int id_unite;
	int duree;
	BigDecimal valeur;
	
	
	public Tarif(int id_unite, int duree, BigDecimal valeur) {
		super();
		this.id_unite = id_unite;
		this.duree = duree;
		this.valeur = valeur;
	}
	public Tarif(int id, int id_unite, int duree, BigDecimal valeur) {
		super();
		this.id = id;
		this.id_unite = id_unite;
		this.duree = duree;
		this.valeur = valeur;
	}
	public Tarif() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_unite() {
		return id_unite;
	}
	public void setId_unite(int id_unite) {
		this.id_unite = id_unite;
	}
	public int getDuree() {
		return duree;
	}
	public void setDuree(int duree) {
		this.duree = duree;
	}
	public BigDecimal getValeur() {
		return valeur;
	}
	public void setValeur(BigDecimal valeur) {
		this.valeur = valeur;
	}
	
	
}
