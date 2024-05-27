package com.gestion.parking.controller;

import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.gestion.parking.modele.Amende;
import com.gestion.parking.modele.Portefeuille;
import com.gestion.parking.repository.AmendeRepository;
import com.gestion.parking.repository.DatenowRepository;
import com.gestion.parking.repository.PortefeuilleRepository;
import com.gestion.parking.repository.ReservationRepository;
import com.gestion.parking.view.Compte_protefeuille;
import com.gestion.parking.view.Reservation_depasse;

@Controller
public class AmendeController {
	
	@Autowired
	DatenowRepository datenowRepo;

	@Autowired
	AmendeRepository amendeRepo;

	@Autowired
	ReservationRepository reservationRepo;

	@Autowired
	PortefeuilleRepository portefeuilleRepo;

	@RequestMapping(value = "/payerAmende")
	public RedirectView payerAmende(@RequestParam("idReservation") String idReservation, HttpSession session,
			RedirectAttributes redirectAttributes) {
		RedirectView redirectViews = new RedirectView("/ficheReservation", true);
		try {
			Object idclient = session.getAttribute("idclient");
			int client = (int) idclient;

			int idreservation = Integer.parseInt(idReservation);
			Reservation_depasse depassement = reservationRepo.findOneReservation_depasse(client, idreservation);

			Compte_protefeuille compte_protefeuille = portefeuilleRepo.findLeComptePortefeuille(client);
			if (compte_protefeuille.getRESTANT() >= 150000) {

				int id_reservation = depassement.getID();
				BigDecimal restant = BigDecimal.valueOf(Math.abs(depassement.getRESTANT()));
				BigDecimal valeur = BigDecimal.valueOf(150000.0);
				BigDecimal entrer = BigDecimal.valueOf(0.0);

				Amende amende = new Amende(id_reservation, restant, valeur);

				Date date = datenowRepo.getNow();

				Portefeuille portefeuille = new Portefeuille(client, entrer, valeur, date, 1);

				amendeRepo.save(amende);
				portefeuilleRepo.save(portefeuille);

				redirectAttributes.addFlashAttribute("amendeSucces", true);
				redirectAttributes.addFlashAttribute("valeur", valeur);
				redirectViews = new RedirectView("/fichePortefeuille", true);

			} else {
				redirectAttributes.addFlashAttribute("insuffisance", true);

				// redirectAttributes.addFlashAttribute("valeur", valeur);
				redirectViews = new RedirectView("/fichePortefeuille", true);
			}

		} catch (Exception e) {

		}
		final RedirectView redirectView = redirectViews;
		return redirectViews;
	}
}
