package com.gestion.parking.modele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Client {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	String nom;
	String email;
	String mdp;
	

	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Client(String nom, String email, String mdp) {
		super();
		this.nom = nom;
		this.mdp = mdp;
		this.email = email;
	}
	public Client(int id, String nom, String mdp, String email) {
		super();
		this.id = id;
		this.nom = nom;
		this.mdp = mdp;
		this.email = email;
	}
	public Client() {
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
