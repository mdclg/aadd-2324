package editorial.web;

import java.io.Serializable;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import editorial.dao.LibroDAO;
import editorial.modelo.Libro;

@Named
@ViewScoped
public class LibroEditWeb implements Serializable {

	private String titulo;

	private String formato;

	private Date fecha;

	private Integer numPaginas;

	private Float precio;

	private String isbn;

	private Libro libro;

	private Integer libroId;

	public void loadLibro() {

		libro = LibroDAO.getLibroDAO().findById(libroId); // debería ser un DTO y a través de la capa de servicios, pero
															// simplificamos código

		titulo = libro.getTitulo();

		formato = libro.getFormato();

		fecha = libro.getFechaPublicacion();

		numPaginas = libro.getNumPaginas();

		precio = libro.getPrecio();

		isbn = libro.getIsbn();

	}

	public void editarLibro() {

		// ojo, debería hacerse a través de un servicio

		libro.setFechaPublicacion(fecha);

		libro.setFormato(formato);

		libro.setIsbn(isbn);

		libro.setNumPaginas(numPaginas);

		libro.setPrecio(precio);

		libro.setTitulo(titulo);

		LibroDAO.getLibroDAO().updateLibro(libro);

		FacesContext.getCurrentInstance().addMessage(null,

				new FacesMessage(FacesMessage.SEVERITY_INFO, "Libro Editado", ""));

		PrimeFaces.current().dialog().closeDynamic(null);

	}

	public void cerrar() {

		PrimeFaces.current().dialog().closeDynamic(null);

	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getNumPaginas() {
		return numPaginas;
	}

	public void setNumPaginas(Integer numPaginas) {
		this.numPaginas = numPaginas;
	}

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Integer getLibroId() {
		return libroId;
	}

	public void setLibroId(Integer libroId) {
		this.libroId = libroId;
	}

	public Libro getLibro() {
		return libro;
	}
	
	public void setLibro(Libro libro) {
		this.libro = libro;
		
		titulo = libro.getTitulo();

		formato = libro.getFormato();

		fecha = libro.getFechaPublicacion();

		numPaginas = libro.getNumPaginas();

		precio = libro.getPrecio();

		isbn = libro.getIsbn();
	}
	

}