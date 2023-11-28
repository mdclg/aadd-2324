package editorial.web;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import editorial.dao.LibroDAO;
import editorial.modelo.Libro;



@Named
@ViewScoped
public class LibroWeb implements Serializable{
	
	protected Integer id;
	private Libro libro;
	@Inject
	FacesContext facesContext;
	
	public void buscarLibro() {
		if(id == null)
			return;
		//CUIDADO, DEBERÍA SER LibroDTO y deberíamos utilizar una capa de servicios (que a su vez use un repositorio)
				// Este código solo ilustra el uso de JSF
		libro = LibroDAO.getLibroDAO().findById(id);
	}

	public void goToCrear() {
		try {
			facesContext.getExternalContext().redirect("crearlibro.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}
}
