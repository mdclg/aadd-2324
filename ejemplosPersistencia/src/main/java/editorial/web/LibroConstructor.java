package editorial.web;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import editorial.dao.LibroDAO;
import editorial.modelo.Libro;


@Named
@ViewScoped
public class LibroConstructor implements Serializable{
	
	protected String titulo;
	protected String formato;
	protected Date fecha;
	protected Integer numPaginas;
	protected Float precio;
	protected String isbn;
	
	@Inject
	FacesContext facesContext;
		
	public void crearLibro() {
		
		//CUIDADO, DEBERÍA SER LibroDTO y deberíamos utilizar una capa de servicios (que a su vez use un repositorio)
		// Este código solo ilustra el uso de JSF
		Libro nuevoLibro = LibroDAO.getLibroDAO().saveLibro(fecha, formato, isbn, numPaginas, titulo, precio);
		if (nuevoLibro != null) {
			facesContext.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Libro creado",
					"El libro " + titulo + " ha sido creado"));		
		}
		else {
			facesContext.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "Libro no creado",
					"El libro " + titulo + " no ha sido creado"));
		}
	}
	
	public List<Libro> getLibros(){
		return LibroDAO.getLibroDAO().findAllLibros();
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

}
