package journals.servicio;

import java.time.LocalDate;
import java.util.List;

import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;

public interface IServicioJournal {

	String crear(String id, String nombre, String descripcion, LocalDate fechaFundacion) throws RepositorioException;
	
	void addEdicion(String issn, Integer volumen, LocalDate fechaEdicion, LocalDate fechaFinEnvios, List<String[]> temas) throws RepositorioException, EntidadNoEncontrada;
	
}
