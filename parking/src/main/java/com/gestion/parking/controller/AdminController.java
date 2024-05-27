package com.gestion.parking.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
import com.gestion.parking.modele.Datenow;
import com.gestion.parking.repository.AdminRepository;
import com.gestion.parking.repository.DatenowRepository;

@Controller
public class AdminController {

	@Autowired
	AdminRepository adminRepo;
	
	@Autowired
	DatenowRepository datenowRepo;
	
	@RequestMapping(value="/login-admin")
	public ModelAndView formulaireParking() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin/login");
		return mv;
	}
	
	@RequestMapping(value="/changeHeure")
	public ModelAndView formulaireNow(HttpSession sessionDate) {
		
		ModelAndView mv = new ModelAndView();
		Date date = datenowRepo.getNow();
		System.out.println("type date :"+date);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm");  
		String strDate = dateFormat.format(date); 
		System.out.println("type String :"+strDate);
		mv.addObject("date", date);
		mv.setViewName("admin/ajoutHeure"); 
		return mv;
	}
	
	@RequestMapping(value="/configurerDate")
	public RedirectView configurerDate(@RequestParam("datedesaisi") String date,
			RedirectAttributes redirectAttributes) throws ParseException {
		System.out.println("Eto hivelany ah ");
		try {
			
			SimpleDateFormat changeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String input = date.replace( "T" , " " ) ;
			input = input+":00";
			System.out.println("inpyt "+input);
			java.util.Date temp = changeFormat.parse(input);
			System.out.println("temp "+temp);
			//java.util.Date temps = changeFormat.parse(input);
			//System.out.println("le changement "+temps);
			datenowRepo.modifieDateNow(temp);
			System.out.println("vita ny update");
			
		}catch (Exception e) {
			System.out.println("nanao cathc");
			System.out.println(e.getMessage());
		}
		
		final RedirectView redirectView = new RedirectView("/changeHeure",true);
		return redirectView;
		}
	
	@RequestMapping(value="/authentification-admin", method = {RequestMethod.POST})
	public RedirectView login(@RequestParam("nom") String nom,@RequestParam("mdp") String mdp,RedirectAttributes redirectAttributes) {
		RedirectView redirectView = new RedirectView();
		try {
			Admin admin = adminRepo.findAdmin(nom, mdp);
			if(admin!=null) {
				redirectView = new RedirectView("/formulaireParking", true);
			}
			else {
				redirectView =	new RedirectView("/login-admin", true);
			}
			
		}catch(Exception e) {
			
		}
		 final RedirectView redirectViews = redirectView;
		 return redirectViews;
	}
	
	
}
