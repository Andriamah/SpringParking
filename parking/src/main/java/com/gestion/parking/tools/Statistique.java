package com.gestion.parking.tools;

public class Statistique {

	String designation;
	double nombre;
	public Statistique(String designation, double nombre) {
		super();
		this.designation = designation;
		this.nombre = nombre;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public double getNombre() {
		return nombre;
	}
	public void setNombre(double nombre) {
		this.nombre = nombre;
	}
	
	
}
