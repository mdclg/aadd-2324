package journals.modelo;

import java.util.Date;
import java.util.List;

public class Articulo{
	
	private Integer id;
	private String titulo;
	private String contenido;
	private String editor;
	private Date fechaPublicacion;
	private EstadoArticulo estado;
	
	private Tema tema;
	private Edicion edicion;
	
	public Edicion getEdicion() {
		return edicion;
	}
	public void setEdicion(Edicion edicion) {
		this.edicion = edicion;
	}
	private List<Autor> autores;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public Tema getTema() {
		return tema;
	}
	public void setTema(Tema tema) {
		this.tema = tema;
	}
	public List<Autor> getAutores() {
		return autores;
	}
	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}
	public String getEditor() {
		return editor;
	}
	public void setEditor(String editor) {
		this.editor = editor;
	}
	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}
	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}
	public EstadoArticulo getEstado() {
		return estado;
	}
	public void setEstado(EstadoArticulo estado) {
		this.estado = estado;
	}
	
	

}
