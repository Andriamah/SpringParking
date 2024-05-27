package com.gestion.parking.view;

import java.math.BigDecimal;
import java.util.Date;

public interface V_reservation_en_cours {
	
	int getID();
	int getID_CLIENT();
	int getID_PARKING();
	String getMATRICULATION();
	Date getDATE_DEBUT();
	Date getDATE_FIN();
	int getETAT();
	Date getDATE_QUITTER();
	int getID_TARIF();
	BigDecimal getVALEUR();
	int getDUREE();
	double getRESTANT();
}
