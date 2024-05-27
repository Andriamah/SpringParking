package com.gestion.parking.modele;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Datenow {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	Date date_projet;

	public Datenow(Date date_projet) {
		super();
		this.date_projet = date_projet;
	}

	public Datenow() {
		super();
	}

	public Date getDate_projet() {
		return date_projet;
	}

	public void setDate_projet(Date date_projet) {
		this.date_projet = date_projet;
	}
	
	
}
