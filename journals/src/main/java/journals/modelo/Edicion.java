package journals.modelo;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


public class Edicion {
	
	
	private Integer id;
	private Integer volumen;
	private LocalDate fechaEdicion;
	private LocalDate fechaFinEnvios;
	
	private Revista revista;
	private ArrayList<Tema> temas;
	
	public Edicion() {
		
	}
	public Edicion(Integer volumen, LocalDate fechaEdicion, LocalDate fechaFinEnvios, Revista revista,
			ArrayList<Tema> temas) {
		this.volumen = volumen;
		this.fechaEdicion = fechaEdicion;
		this.fechaFinEnvios = fechaFinEnvios;
		this.revista = revista;
		this.temas = temas;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getVolumen() {
		return volumen;
	}
	public void setVolumen(Integer volumen) {
		this.volumen = volumen;
	}
	public LocalDate getFechaEdicion() {
		return fechaEdicion;
	}
	public void setFechaEdicion(LocalDate fechaEdicion) {
		this.fechaEdicion = fechaEdicion;
	}
	public LocalDate getFechaFinEnvios() {
		return fechaFinEnvios;
	}
	public void setFechaFinEnvios(LocalDate fechaFinEnvios) {
		this.fechaFinEnvios = fechaFinEnvios;
	}
	public ArrayList<Tema> getTemas() {
		return temas;
	}
	public void setTemas(ArrayList<Tema> temas) {
		this.temas = temas;
	}
	public Revista getRevista() {
		return revista;
	}
	public void setRevista(Revista revista) {
		this.revista = revista;
	}
	
	

}
