package editorial.web;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import editorial.dao.LibroDAO;
import editorial.modelo.Libro;

@Named
@ViewScoped
public class LibroBuscador extends BuscadorWeb<Libro> implements Serializable {

	private List<Libro> libros;

	public LibroBuscador() {
		buscar();
	}

	@Override

	public List<Libro> getColeccion() {
		return libros;
	}

	@Override

	public void accion(Libro item) {
		System.out.println("Libro borrado " + item.getTitulo());
	}

	@Override

	public String getEtiqueta(Libro item) {
		return item.getIsbn() + " - " + item.getTitulo();

	}

	@Override

	public String getIdentificador(Libro item) {
		return item.getId().toString();

	}

	@Override
	public void buscar() { //ojo esto debería usar la capa de servicios que a su vez use un repositorio
						// ejercicio simplificado para ilustrar los componentes por composición
		libros = LibroDAO.getLibroDAO().findLibrosByTitulo(keyword);
	}

}