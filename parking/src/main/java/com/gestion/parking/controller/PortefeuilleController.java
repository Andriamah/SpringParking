package com.gestion.parking.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.gestion.parking.modele.Portefeuille;
import com.gestion.parking.repository.PortefeuilleRepository;
import com.gestion.parking.view.Compte_protefeuille;
import com.gestion.parking.view.V_portefeuille;

@Controller
public class PortefeuilleController {

	@Autowired
	PortefeuilleRepository portefeuilleRepo;
	
	@RequestMapping(value = "/ajouterPortefeuille")
	public RedirectView reserver(HttpSession session,RedirectAttributes redirectAttributes) {

		RedirectView redirectViews = new RedirectView();

		try {

			Object idclient = session.getAttribute("idclient");
			if (idclient != null) {
				redirectViews = new RedirectView("/fichePortefeuille", true);
				
			}else {
				redirectViews = new RedirectView("/login-client", true);
			}
			
		}catch(Exception e) {
			
		}
		final RedirectView redirectView = redirectViews;
		return redirectView;
	}
	
	
	@RequestMapping(value = "/fichePortefeuille")
	public ModelAndView fichePortefeuille(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		try {
			Object idclient = session.getAttribute("idclient");
			int client = (int) idclient;
			if (idclient != null) {
				Compte_protefeuille compte = portefeuilleRepo.findLeComptePortefeuille(client);
				List<V_portefeuille> liste = portefeuilleRepo.findlistePortefeuilleByClient(client);
				System.out.println("le volaa "+compte.getRESTANT());
				mv.addObject("liste", liste);
				mv.addObject("vola", compte.getRESTANT());
			}
			
		}catch(Exception e) {
			
		}
		mv.setViewName("portefeuille");
		return mv;
	}
	
	@RequestMapping(value = "/rechargePortefeuille")
	public RedirectView recharger(HttpSession session,@RequestParam("montant") String montant) {

		try {

			Object idclient = session.getAttribute("idclient");
			int client = (int) idclient;
			BigDecimal entrer = BigDecimal.valueOf(Double.valueOf(montant));
			BigDecimal sortie = BigDecimal.valueOf(0.0);
			//int id_client, BigDecimal valeur_entrer, BigDecimal valeur_sortie, Date date_portefeille,
			//int etat
			Date date = new Date();
			
			Portefeuille portefeuille = new Portefeuille(client,entrer,sortie,date,0);
			portefeuilleRepo.save(portefeuille);
		}catch(Exception e) {
			
		}
		final RedirectView redirectView = new RedirectView("/", true);
		return redirectView;
	}
	
	@RequestMapping(value="/listeRecharge")
	public ModelAndView portefeuille() {
		ModelAndView mv = new ModelAndView();
		try {
		List<V_portefeuille> liste = portefeuilleRepo.findlistePortefeuille();
		mv.addObject("listeRecharge", liste);
		mv.setViewName("admin/validerRecharge");
		}catch(Exception e) {}
		return mv;
	}
	
	@RequestMapping(value="/validerRecharge")
	public RedirectView validerRecharge(@RequestParam("idRecharge") String idRecharge) {
		 final RedirectView redirectViews = new RedirectView("/listeRecharge", true);
		
		int id = Integer.parseInt(idRecharge);
		try {
			portefeuilleRepo.validerPortefuille(id);
		}catch(Exception e){
			System.out.println("tsy mety update le portefeuille");
		}
		return redirectViews;
	}
	
}
