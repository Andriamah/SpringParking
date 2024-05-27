package com.gestion.parking.modele;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Portefeuille {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	int id_client;
	BigDecimal valeur_entre;
	BigDecimal valeur_sortie;
	Date date_portefeille;
	int etat;
	public Portefeuille(int id, int id_client, BigDecimal valeur_entrer, BigDecimal valeur_sortie,
			Date date_portefeille, int etat) {
		super();
		this.id = id;
		this.id_client = id_client;
		this.valeur_entre = valeur_entrer;
		this.valeur_sortie = valeur_sortie;
		this.date_portefeille = date_portefeille;
		this.etat = etat;
	}
	public Portefeuille(int id_client, BigDecimal valeur_entrer, BigDecimal valeur_sortie, Date date_portefeille,
			int etat) {
		super();
		this.id_client = id_client;
		this.valeur_entre = valeur_entrer;
		this.valeur_sortie = valeur_sortie;
		this.date_portefeille = date_portefeille;
		this.etat = etat;
	}
	public Portefeuille() {
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
	public BigDecimal getValeur_entre() {
		return valeur_entre;
	}
	public void setValeur_entre(BigDecimal valeur_entrer) {
		this.valeur_entre = valeur_entrer;
	}
	public BigDecimal getValeur_sortie() {
		return valeur_sortie;
	}
	public void setValeur_sortie(BigDecimal valeur_sortie) {
		this.valeur_sortie = valeur_sortie;
	}
	public Date getDate_portefeille() {
		return date_portefeille;
	}
	public void setDate_portefeille(Date date_portefeille) {
		this.date_portefeille = date_portefeille;
	}
	public int getEtat() {
		return etat;
	}
	public void setEtat(int etat) {
		this.etat = etat;
	}
	
	
	
}
