package com.gestion.parking.tools;

import java.util.Calendar;
import java.util.Date;

public class Tool {

	public static Date ajouterMinutes(Date date,int nbMinutes) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, nbMinutes);
		return cal.getTime();
	}
	
	public static double pourcentage(int total,int nombre) {
		double division = (double)nombre/total;
		System.out.println("division "+division);
		double pourcentage = division*100;
		System.out.println("pourcentage "+pourcentage);
		return pourcentage;
	}
}
