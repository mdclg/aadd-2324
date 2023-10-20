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
		if (id == null || id.isEmpty())
			throw new IllegalArgumentException("ISSN: no debe ser nulo ni vacio");

		if (nombre == null || nombre.isEmpty())
			throw new IllegalArgumentException("nombre: no debe ser nula");

		if (fechaFundacion == null) {
			throw new IllegalArgumentException("fechaFundacion: no debe ser nula");
		}
		Revista revista = new Revista(id, nombre, descripcion, fechaFundacion);
		String idRevista = repositorio.add(revista);
		return idRevista;
	}

	@Override
	public void addEdicion(String issn, Integer volumen, LocalDate fechaEdicion, LocalDate fechaFinEnvios,
			List<String[]> temas) throws RepositorioException, EntidadNoEncontrada {
		if (issn == null || issn.isEmpty())
			throw new IllegalArgumentException("ISSN: no debe ser nulo ni vacio");

		if (volumen == null)
			throw new IllegalArgumentException("volumen: no debe ser nula");

		if (fechaEdicion == null) {
			throw new IllegalArgumentException("fechaEdicion: no debe ser nula");
		}
		if (fechaFinEnvios == null) {
			throw new IllegalArgumentException("fechaFinEnvios: no debe ser nula");
		}
		if (fechaEdicion.isBefore(fechaEdicion)) {
			throw new IllegalArgumentException("La fecha de edición debe ser posterior a la fecha de fin de envíos");
		}

		Revista revista = repositorio.getById(issn);
		if (revista != null) {
			revista.addEdicion(volumen, fechaEdicion, fechaFinEnvios, temas);
		}
		repositorio.update(revista);

	}

}
