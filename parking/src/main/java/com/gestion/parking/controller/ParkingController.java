package com.gestion.parking.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.gestion.parking.modele.Parking;
import com.gestion.parking.modele.Tarif;
import com.gestion.parking.repository.DatenowRepository;
import com.gestion.parking.repository.ParkingRepository;
import com.gestion.parking.repository.TarifRepository;
import com.gestion.parking.view.V_parking;

@Controller
public class ParkingController {

	@Autowired
	DatenowRepository datenowRepo;
	
	@Autowired
	ParkingRepository parkingRepo;
	
	@Autowired
	TarifRepository tarifRepo;

	@RequestMapping(value = "/")
	public ModelAndView listerPrking() {
		ModelAndView mv = new ModelAndView();
		try {
			List<V_parking> liste = parkingRepo.findListParking();
			int size = liste.size();
			int occupe = parkingRepo.countOccupe();
			int libre = parkingRepo.countLibre();
			int infraction = parkingRepo.countInfraction();
			System.out.println(liste.size());
			List<Tarif> listee = tarifRepo.findAll();

			Date ladate = datenowRepo.getNow();
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm");  
			String strDate = dateFormat.format(ladate);
			
			System.out.println("date"+ladate);
			mv.addObject("date", ladate);
			mv.addObject("listeTarif", listee);
			mv.addObject("listeParking", liste);
			mv.addObject("nombre", size);
			mv.addObject("nboccupe", occupe);
			mv.addObject("nblibre", libre);
			mv.addObject("nbinfraction", infraction);
			mv.setViewName("index");
		} catch (Exception e) {
			System.out.println("manao catch");
		}
		return mv;
	}

	@RequestMapping(value = "/formulaireParking")
	public ModelAndView formulaireParking() {
		ModelAndView mv = new ModelAndView();
		List<Parking> liste = parkingRepo.findAll();
		mv.addObject("listeParking", liste);
		mv.setViewName("admin/index");
		return mv;
	}
	
	@RequestMapping(value = "/stat")
	public ModelAndView stat() {
		ModelAndView mv = new ModelAndView();
		//sdiv
		mv.setViewName("admin/statistique");
		return mv;
	}

	@RequestMapping(value = "/ajoutParking")
	public RedirectView ajouterParking(@RequestParam("parking") String parking) {
		try {
			//boolean dispo = false;
		Parking leparking = new Parking(parking);
		parkingRepo.save(leparking);	
		} catch (Exception e) {
			System.out.println("manao catch");
		}
		final RedirectView redirectViews = new RedirectView("/formulaireParking", true);
		return redirectViews;
	}
}
