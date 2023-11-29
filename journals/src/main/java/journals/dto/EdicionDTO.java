package journals.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class EdicionDTO implements Serializable {
	private Integer id;
	private Integer volumen;
	private LocalDate fechaFinEnvios;

	private ArrayList<String> temas;

	public EdicionDTO(Integer id, Integer volumen, LocalDate fechaFinEnvios, ArrayList<String> temas) {
		super();
		this.id = id;
		this.volumen = volumen;
		this.fechaFinEnvios = fechaFinEnvios;
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

	public LocalDate getFechaFinEnvios() {
		return fechaFinEnvios;
	}

	public void setFechaFinEnvios(LocalDate fechaFinEnvios) {
		this.fechaFinEnvios = fechaFinEnvios;
	}

	public ArrayList<String> getTemas() {
		return temas;
	}

	public void setTemas(ArrayList<String> temas) {
		this.temas = temas;
	}
}