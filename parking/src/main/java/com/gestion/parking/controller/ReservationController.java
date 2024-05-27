package com.gestion.parking.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
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

import com.gestion.parking.modele.Parking;
import com.gestion.parking.modele.Portefeuille;
import com.gestion.parking.modele.Reservation;
import com.gestion.parking.modele.Tarif;
import com.gestion.parking.repository.DatenowRepository;
import com.gestion.parking.repository.ParkingRepository;
import com.gestion.parking.repository.PortefeuilleRepository;
import com.gestion.parking.repository.ReservationRepository;
import com.gestion.parking.repository.TarifRepository;
import com.gestion.parking.tools.Tool;
import com.gestion.parking.view.Compte_protefeuille;
import com.gestion.parking.view.Reservation_depasse;
import com.gestion.parking.view.V_parking;
import com.gestion.parking.view.V_parking_heure;
import com.gestion.parking.view.V_reservation_en_cours;

@Controller
public class ReservationController {

	@Autowired
	DatenowRepository datenowRepo;

	@Autowired
	ReservationRepository reservationRepo;

	@Autowired
	TarifRepository tarifRepo;

	@Autowired
	PortefeuilleRepository portefeuilleRepo;

	@Autowired
	ParkingRepository parkingRepo;

	@RequestMapping(value = "/formulaireReservation")
	public ModelAndView formulaireReservation(@RequestParam("idParking") String idParking) {
		ModelAndView mv = new ModelAndView();

		int parking = Integer.valueOf(idParking);
		List<Tarif> liste = tarifRepo.findAll();

		mv.addObject("listeTarif", liste);
		mv.addObject("idparking", parking);
		mv.setViewName("reservation");

		return mv;
	}

	@RequestMapping(value = "/reserver")
	public RedirectView reserver(@RequestParam("idParking") String idParking,
			@RequestParam("idtarif") String idTarif,
			@RequestParam("matriculation") String matriculation,
			@RequestParam("datedebut") String datedebut,
			HttpSession session, 
			RedirectAttributes redirectAttributes) {
		
		System.out.println("Anao reservation");
		RedirectView redirectViews = new RedirectView();

		try {

			Object idclient = session.getAttribute("idclient");
			if (idclient != null) {
				System.out.println("efa connecter");
				int client = (int) idclient;

                int parking = Integer.parseInt(idParking);
					int tarif = Integer.parseInt(idTarif);

					V_parking leparking = parkingRepo.findByIdParking(parking);
					int dispo = leparking.getDISPONIBILITE();

					if (dispo > 0) {
						System.out.println("disponible le parking");
						
						Tarif leTarifChoisi = tarifRepo.getById(tarif);
						java.util.Date date = new Date(Timestamp.valueOf(LocalDateTime.parse(datedebut)).getTime());

                        int duree = leTarifChoisi.getDuree();
                        Date date_fin = Tool.ajouterMinutes(date, duree);
                        
                        System.out.println("Debut "+date);
                        System.out.println("Fin "+date_fin);
                        
                        int possibilite = parkingRepo.findByPossibilite(date_fin,date,parking);
                        System.out.println("Valiny possibilite "+possibilite);
                        int etat = 0;
                        if(dispo == 2) {
                        	etat = 1;
                        }
                        if(possibilite==0){
                        	System.out.println("Bola possible");
                             try {
                                    Compte_protefeuille compte_protefeuille = portefeuilleRepo.findLeComptePortefeuille(client);

                                    BigDecimal compteClient = BigDecimal.valueOf(Double.valueOf(compte_protefeuille.getRESTANT()));
                                    BigDecimal prixParking = leTarifChoisi.getValeur();

                                    System.out.println("Ny volany "+compteClient);
                                    System.out.println("Ny vidiny "+prixParking);
                                    if (compteClient.compareTo(prixParking) == 1 || compteClient.compareTo(prixParking) == 0) {
                                        
                                        System.out.println("Ampy ny volany");
                                        

                                        Reservation reservation = new Reservation(client, parking, matriculation, date, date_fin, etat,
                                                null, tarif);

                                        BigDecimal entrer = BigDecimal.valueOf(0.0);

                                        Portefeuille portefeuille = new Portefeuille(client, entrer, prixParking, date, 1);

                                        reservationRepo.save(reservation);
                                        portefeuilleRepo.save(portefeuille);
                                        // parkingRepo.updateParking(true, parking);
                                        System.out.println("tokony makany @fiche");

                                        redirectAttributes.addFlashAttribute("succes", true);
                                        redirectAttributes.addFlashAttribute("deduit", prixParking);
                                        redirectViews = new RedirectView("/ficheReservation", true);

                                    } else {
                                        System.out.println("Tsy ampy volany");
                                        redirectAttributes.addFlashAttribute("inssufisance", true);
                                    // BigDecimal manquant = prixParking;
                                        redirectViews = new RedirectView("/", true);
                                    }

                                }catch(Exception e) {
                                    System.out.println("Tsy ampy volany");
                                    redirectAttributes.addFlashAttribute("inssufisance", true);
                                    //BigDecimal manquant = prixParking;
                                    redirectViews = new RedirectView("/", true);
                                }
                        }else{
                            System.out.println("Tsy possible io");
                            redirectAttributes.addFlashAttribute("nonpossibilite", true);
                            redirectViews = new RedirectView("/", true);
                        }   
                    }else{
                        System.out.println("Tsy disponible io");
						redirectAttributes.addFlashAttribute("nondisponible", true);
						redirectViews = new RedirectView("/", true);
                    }
			} else {
				System.out.println("Ndana aloa miconnecte");
				redirectViews = new RedirectView("/login-client", true);
			}

		} catch (Exception e) {
			System.out.println("MAnao catch reservation");
		}
		final RedirectView redirectView = redirectViews;
		return redirectView;
	}

	@RequestMapping(value = "/ficheReservation")
	public ModelAndView ficheReservation(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Date ladate = datenowRepo.getNow();

		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm");
		String strDate = dateFormat.format(ladate);
		mv.addObject("date", ladate);
		mv.setViewName("ficheReservation");
		return mv;
	}

	@RequestMapping(value = "/ficheReservationClient")
	public RedirectView ficheReservationClient(HttpSession session, RedirectAttributes redirectAttributes) {
		RedirectView redirectView = new RedirectView("/", true);
		try {
			Object idclient = session.getAttribute("idclient");
			if (idclient != null) {
				int client = (int) idclient;
				List<V_reservation_en_cours> reservation = reservationRepo.findReservationClient(client);
				int size = reservation.size();
				if (size != 0) {
					redirectAttributes.addFlashAttribute("reservation", reservation);
					redirectAttributes.addFlashAttribute("monreservation", true);
				} else {
					redirectAttributes.addFlashAttribute("nullReservation", true);
				}
				redirectView = new RedirectView("/ficheReservation", true);
			} else {
				redirectView = new RedirectView("/login-client", true);
			}
		} catch (Exception e) {
			System.out.println("nanao catch");
			System.out.println(e.getMessage());
			System.out.println(session.getAttribute("idclient"));
		}
		final RedirectView redirectViews = redirectView;
		return redirectViews;
	}

	@RequestMapping(value = "/quitterParking")
	public RedirectView quitterParking(@RequestParam("idReservation") String idReservation,
			@RequestParam("datefin") String datefin,
			RedirectAttributes redirectAttributes) {
		int reservation = Integer.valueOf(idReservation);
		RedirectView redirectView = new RedirectView();
		try {
			Reservation reservations = reservationRepo.findReservationById(reservation);
			//
			int idParking = reservations.getId_parking();
			Date debut = reservations.getDate_debut();
			// parkingRepo.updateParking(false, idParking);
			java.util.Date date = new Date(Timestamp.valueOf(LocalDateTime.parse(datefin)).getTime());
			if(debut.before(date)) {
				System.out.println("nety le date quitte");
				reservationRepo.quitterParking(date, reservation);
				redirectView = new RedirectView("/", true);
			}else {
				System.out.println("Tsy mety date quitte");
				redirectAttributes.addFlashAttribute("erreurDate", true);
				redirectView = new RedirectView("/ficheReservation", true);
			}
			
		} catch (Exception e) {
			System.out.println("Manao catch ny quitter");
		}
		final RedirectView redirectViews = redirectView;
		return redirectViews;
	}

	@RequestMapping(value = "/amende")
	public ModelAndView voirAmende(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("amende");
		return mv;
	}

	@RequestMapping(value = "/depassement")
	public RedirectView voirDepassement(HttpSession session, RedirectAttributes redirectAttributes) {
		RedirectView redirectViews = new RedirectView("/ficheReservation", true);
		try {
			Object idclient = session.getAttribute("idclient");
			if (idclient != null) {
				int client = (int) idclient;
				List<Reservation_depasse> depassement = reservationRepo.findReservation_depasse(client);
				int size = depassement.size();
				if (size != 0) {
					redirectAttributes.addFlashAttribute("listeAmende", depassement);
					redirectAttributes.addFlashAttribute("amende", true);

				} else {
					redirectAttributes.addFlashAttribute("nullite", true);
				}
				redirectViews = new RedirectView("/amende", true);
			} else {
				redirectViews = new RedirectView("/login-client", true);
			}
		} catch (Exception e) {

		}
		final RedirectView redirectView = redirectViews;
		return redirectView;
	}

	@RequestMapping(value = "/formulaireHeure")
	public ModelAndView formulaireRecherche() {
		ModelAndView mv = new ModelAndView();
		System.out.println("ato @ formulaire zay");

		Date dates = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm");
		String strDate = dateFormat.format(dates);

		System.out.println("la date " + strDate);
		mv.addObject("date", strDate);
		mv.setViewName("admin/listeParkingHeure");
		System.out.println("tokony mety ");
		return mv;
	}

	@RequestMapping(value = "/reponseRecherche")
	public RedirectView listeResrvation(@RequestParam("datedesaisi") String dateSaisi,
			RedirectAttributes redirectAttributes) {
		java.util.Date dateDeSaisie = new Date(Timestamp.valueOf(LocalDateTime.parse(dateSaisi)).getTime());
		System.out.println("ilay date " + dateDeSaisie);
		try {

			List<V_parking_heure> liste = reservationRepo.findReservationByDate(dateDeSaisie);
			redirectAttributes.addFlashAttribute("listeParking", liste);
			redirectAttributes.addFlashAttribute("reponse", true);
			System.out.println("nanao requete ");
		} catch (Exception e) {
			System.out.println("nanao catch ");
		}
		final RedirectView redirectViews = new RedirectView("/formulaireHeure", true);
		return redirectViews;

	}
}
