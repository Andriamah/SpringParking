package com.gestion.parking.view;

import java.math.BigDecimal;
import java.util.Date;

public interface V_parking_heure {
	int getID();
	int getID_CLIENT();
	int getID_PARKING();
	String getMATRICULATION();
	Date getDATE_DEBUT();
	Date getDATE_FIN();
	BigDecimal getVALEUR();
	int getETAT();
	Date getDATE_QUITTER();
	String getTITRE();
	int getIDPARKING();
	boolean getDISPONIBILITE();
	int getDISPO_HEURE();
}
