package com.gestion.parking.tools;

import java.awt.Color;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import com.gestion.parking.view.V_reservation_en_cours;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

public class FileExport {

	public void setResponseHeader(HttpServletResponse response, String contentType, String extension, String prefixe)
			throws IOException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		String timeStamp = dateFormat.format(new Date());
		String fileName = prefixe + timeStamp + extension;
		
		response.setContentType(contentType);
		
		String headerKey = "Content-Disposition";
		String headerValue = "attachement; filename="+fileName;
		response.setHeader(headerKey, headerValue);
	}

	public void exportToPdf(V_reservation_en_cours reservation, HttpServletResponse response) throws IOException {
		setResponseHeader(response, "application/pdf", ".pdf", "Reservation_");
		
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		
		document.open();
		
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.black);
		
		Font font2 = FontFactory.getFont(FontFactory.COURIER);
		font2.setSize(15);
		font2.setColor(Color.black);
		
		Paragraph titre = new Paragraph("Ticket Parking",font);
		titre.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(titre);
		
		Paragraph idReservation = new Paragraph("N°: Ticket"+reservation.getID(),font2);
		idReservation.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(idReservation);

		Paragraph tarif = new Paragraph("Tarif : "+reservation.getDUREE(),font2);
		tarif.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(tarif);

		Paragraph immatriculation = new Paragraph("Immatriculation :"+reservation.getMATRICULATION(),font2);
		immatriculation.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(immatriculation);
		
		Paragraph date = new Paragraph("Date : "+reservation.getDATE_DEBUT(),font2);
		date.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(date);
		
		Paragraph montant = new Paragraph("Montant :"+reservation.getVALEUR(),font2);
		montant.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(montant);
		
		Paragraph merci = new Paragraph("Merci de votre confiance ! ",font2);
		merci.setAlignment(Paragraph.ALIGN_CENTER);
		
		document.add(merci);
		
		
		
		document.close();		
		
	}
}
