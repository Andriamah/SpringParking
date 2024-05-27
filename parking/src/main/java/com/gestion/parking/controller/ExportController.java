package com.gestion.parking.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.parking.repository.ReservationRepository;
import com.gestion.parking.tools.FileExport;
import com.gestion.parking.view.V_reservation_en_cours;

@RestController
public class ExportController {
	@Autowired
	ReservationRepository reservationRepo;

	@GetMapping("/ticket")
	public void exportTicket(HttpServletResponse response,HttpSession session) throws IOException {
		FileExport export = new FileExport();
		Object idclient = session.getAttribute("idclient");
		int client = (int) idclient;
		List<V_reservation_en_cours> reservation = reservationRepo.findReservationClient(client);
		int size = reservation.size();
		
		export.exportToPdf(reservation.get(size-1), response);
	}
}
