package com.gestion.parking.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.gestion.parking.modele.Tarif;
import com.gestion.parking.modele.Unite;
import com.gestion.parking.repository.TarifRepository;
import com.gestion.parking.repository.UniteRepository;

@Controller
public class TarifController {
	@Autowired
	TarifRepository tarifRepo;
	
	@Autowired
	UniteRepository uniteRepo;
	
	@RequestMapping(value = "/listeTarif")
	public ModelAndView listeTarif() {
		ModelAndView mv = new ModelAndView();
		List<Unite> listeUnite = uniteRepo.findAll();
		List<Tarif> liste = tarifRepo.findAll();
		mv.addObject("liste", liste);
		mv.addObject("listeUnite", listeUnite);
		mv.setViewName("admin/listeTarif");
		return mv;
	}
	
	@RequestMapping(value = "/supprimerTarif")
	public RedirectView supprimerTarif(@RequestParam("id")String idTarif) {
		int tarif = Integer.parseInt(idTarif);
		tarifRepo.deleteById(tarif);
		final RedirectView redirectViews = new RedirectView("/listeTarif",true);
		return redirectViews;

	}
	
	@RequestMapping(value = "/modifierTarif")
	public ModelAndView modifierTarif(@RequestParam("id")String idTarif) {
		ModelAndView mv = new ModelAndView();
		int tarif = Integer.parseInt(idTarif);
		Tarif letarif =  tarifRepo.getById(tarif);
		mv.addObject("tarif", letarif);
		mv.setViewName("admin/formulaireTarif");
		
		return mv;
	}
	@RequestMapping(value = "/effectuerModif")
	public RedirectView effectuerModif(@RequestParam("id")String idTarif,
			@RequestParam("duree")String duree,
			@RequestParam("valeur")String valeur) {
		int tarif = Integer.parseInt(idTarif);
		tarifRepo.deleteById(tarif);
		final RedirectView redirectViews = new RedirectView("/listeTarif",true);
		return redirectViews;
	}
	
	@RequestMapping(value = "/ajoutTarif")
	public RedirectView ajoutTarif(@RequestParam("montant")String valeur,
			@RequestParam("duree")String duree,
			@RequestParam("idUnite")String idUnite) {
		int unite = Integer.parseInt(idUnite);
		int laduree = Integer.parseInt(duree);
		BigDecimal montant = BigDecimal.valueOf(Double.valueOf(valeur));
		
		//(int id, int id_unite, int duree, BigDecimal valeur
		
		if(unite == 1 ) {
			laduree = laduree*60;
		}
		Tarif nouveau = new Tarif(unite,laduree,montant);
		tarifRepo.save(nouveau);
		
		final RedirectView redirectViews = new RedirectView("/listeTarif",true);
		return redirectViews;
	}
	
}
