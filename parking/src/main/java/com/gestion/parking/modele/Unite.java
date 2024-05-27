package com.gestion.parking.modele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Unite {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	String nom;

	public Unite(int id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}

	public Unite(String nom) {
		super();
		this.nom = nom;
	}

	public Unite() {
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
	
	
}
