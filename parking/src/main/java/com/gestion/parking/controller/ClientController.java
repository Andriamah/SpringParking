package com.gestion.parking.controller;

import javax.persistence.Entity;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.gestion.parking.modele.Admin;
import com.gestion.parking.modele.Client;
import com.gestion.parking.repository.ClientRepository;

@Controller
public class ClientController {

	@Autowired
	ClientRepository clientRepo;

	@RequestMapping(value = "/login-client")
	public ModelAndView formulaireParking() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		return mv;
	}

	@RequestMapping(value = "/authentification-client", method = { RequestMethod.POST })
	public RedirectView login(@RequestParam("nom") String nom, @RequestParam("mdp") String mdp, HttpSession session,
			RedirectAttributes redirectAttributes) {
		RedirectView redirectView = new RedirectView();
		try {
			Client client = clientRepo.findClient(nom, mdp);
			if (client != null) {
				session.setAttribute("idclient", client.getId());
				System.out.println("hita ianao");
				redirectView = new RedirectView("/", true);
			} else {
				redirectView = new RedirectView("/login-client", true);
			}

		} catch (Exception e) {

		}
		final RedirectView redirectViews = redirectView;
		return redirectViews;

	}

	@RequestMapping(value = "/inscription", method = { RequestMethod.POST })
	public RedirectView inscription(@RequestParam("nom") String nom, @RequestParam("mdp") String mdp,
			@RequestParam("email") String email) {
		RedirectView redirectView = new RedirectView();
		try {
			System.out.println("nom "+nom);
			System.out.println("mdp "+mdp);
			System.out.println("email "+email);
			Client client = new Client(nom, email, mdp);
			clientRepo.save(client);
			redirectView = new RedirectView("/login-client", true);

		} catch (Exception e) {
			System.out.println("Catch inscription");
			System.out.println(e.getMessage());
		}
		final RedirectView redirectViews = new RedirectView("/login-client", true);
		return redirectViews;

	}

	@RequestMapping(value = "/logout")
	public RedirectView logout(HttpSession session) {
		session.removeAttribute("idclient");
		System.out.println("vita ny deconnect");
		final RedirectView redirectView = new RedirectView("/", true);
		return redirectView;
	}
}
