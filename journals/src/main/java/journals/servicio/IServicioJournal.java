package journals.servicio;

import java.time.LocalDate;
import java.util.List;

import journals.dto.RevistaDTO;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;
import servicio.ServicioException;

public interface IServicioJournal {

	String crear(String id, String nombre, String descripcion, LocalDate fechaFundacion) throws RepositorioException;
	
	void addEdicion(String issn, Integer volumen, LocalDate fechaEdicion, LocalDate fechaFinEnvios, List<String[]> temas) throws RepositorioException, EntidadNoEncontrada;
	
	RevistaDTO getByIssn(String issn) throws ServicioException;

	
}
