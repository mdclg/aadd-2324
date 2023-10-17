package journals.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

public class Tema implements Serializable{

	
	private Integer id;
	private String titulo;
	private String descripcion;
	//private Edicion edicion;

	
	
	public Tema(String titulo, String descripcion) {
		this.titulo = titulo;
		this.descripcion = descripcion;
	}
	
	public Tema() {
		
	}
	
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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
