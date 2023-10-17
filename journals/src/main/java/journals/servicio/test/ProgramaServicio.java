package journals.servicio.test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import journals.servicio.IServicioJournal;
import servicio.FactoriaServicios;

public class ProgramaServicio {
	
	private static LocalDate parseDate(String date) {       

	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    LocalDate.parse(date, formatter);
	    try {
	        return LocalDate.parse(date, formatter);
	    } catch (DateTimeParseException e) {
	        return null;
	    }
	}

	public static void main(String[] args) throws Exception {
		
		IServicioJournal servicio = FactoriaServicios.getServicio(IServicioJournal.class);
		String id = servicio.crear("223-234-5567", "Nature", "Lorem ipsum dolor sit amet, consectetur adipiscing elit...", LocalDate.now());
		
		
		String [] tema = new String[2];
		tema[0] = "Ciencias de la materia";
		tema[1] = "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum";
		
		String [] tema2 = new String[2];
		tema2[0] = "Ciencias de la energía";
		tema2[1] = "Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur?";
		
		servicio.addEdicion("223-234-5567", 230,parseDate("01/03/2024"), parseDate("01/12/2023"), Arrays.asList(tema, tema2 ));		
		

	}

}
