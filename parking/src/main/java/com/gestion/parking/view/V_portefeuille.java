package com.gestion.parking.view;

import java.util.Date;

public interface V_portefeuille {

	int getID();
	int getID_CLIENT();
	double getVALEUR_ENTRE();
	double getVALEUR_SORTIE();
	Date getDATE_PORTEFEILLE();
	int getETAT();
	String getUSERNAME();
}
