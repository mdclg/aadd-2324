package repositorio;

import java.time.LocalDateTime;
import java.util.Arrays;

import encuestas.modelo.Encuesta;

public class Prueba {

	public static void main(String[] args) throws Exception {
		
		Encuesta encuesta = new Encuesta("Test 1", "Instrucciones", LocalDateTime.now().minusDays(1),
				LocalDateTime.now().plusDays(1), Arrays.asList("Opción 1", "Opción 2"));
		
		Repositorio<Encuesta, String> repositorio = FactoriaRepositorios.getRepositorio(Encuesta.class);
		
		String id = repositorio.add(encuesta);
		
		Encuesta encuesta2 = repositorio.getById(id);
		
		System.out.println(encuesta2.getId());
		
	}
}
