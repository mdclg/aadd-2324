package journals.servicio;

import java.time.LocalDate;
import java.util.List;

import journals.modelo.Revista;
import repositorio.EntidadNoEncontrada;
import repositorio.FactoriaRepositorios;
import repositorio.Repositorio;
import repositorio.RepositorioException;

public class ServicioJournal implements IServicioJournal {

	private Repositorio<Revista, String> repositorio = FactoriaRepositorios.getRepositorio(Revista.class);

	@Override
	public String crear(String id, String nombre, String descripcion, LocalDate fechaFundacion)
			throws RepositorioException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addEdicion(String issn, Integer volumen, LocalDate fechaEdicion, LocalDate fechaFinEnvios,
			List<String[]> temas) throws RepositorioException, EntidadNoEncontrada {
		// TODO Auto-generated method stub
		
	}

	

	
}
