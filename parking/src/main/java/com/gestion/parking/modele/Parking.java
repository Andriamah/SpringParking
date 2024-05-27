package com.gestion.parking.modele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Parking {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	String titre;
	//boolean disponibilite;

	

	public Parking(String titre) {
		super();
		this.titre = titre;
	}

	public Parking(int id, String titre) {
		super();
		this.id = id;
		this.titre = titre;
	}

	
	public Parking() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	
}
