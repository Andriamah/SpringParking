package com.gestion.parking.modele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pointeur {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	String nom;
	String mdp;
	public Pointeur(int id, String nom, String mdp) {
		super();
		this.id = id;
		this.nom = nom;
		this.mdp = mdp;
	}
	public Pointeur(String nom, String mdp) {
		super();
		this.nom = nom;
		this.mdp = mdp;
	}
	public Pointeur() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
	
}
