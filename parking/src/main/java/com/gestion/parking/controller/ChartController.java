package com.gestion.parking.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.parking.repository.ParkingRepository;
import com.gestion.parking.tools.Statistique;
import com.gestion.parking.tools.Tool;

@RestController
@RequestMapping("/chart")
public class ChartController {

	@Autowired
	ParkingRepository parkingRepo;

	@RequestMapping(value="/stat")
	public List<Statistique> stat() {
		List<Statistique> liste = new ArrayList();
		System.out.println("occupeee "+parkingRepo.countOccupe());
		int total = parkingRepo.countParking();
		System.out.println("total "+total);
		double nbOccupe = Tool.pourcentage(total, parkingRepo.countOccupe());
		double nbLibre= Tool.pourcentage(total, parkingRepo.countLibre());
		double nbInfraction = Tool.pourcentage(total, parkingRepo.countInfraction());
		Statistique occupe = new Statistique("Occupe",nbOccupe);
		Statistique libre = new Statistique("Libre",nbLibre);
		Statistique infraction = new Statistique("Infraction",nbInfraction);
		liste.add(libre);
		liste.add(occupe);
		liste.add(infraction);
		return liste;
	}
}
