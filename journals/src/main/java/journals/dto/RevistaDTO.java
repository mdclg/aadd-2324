package journals.dto;

import java.time.LocalDate;
import java.util.ArrayList;

public class RevistaDTO {

	private String issn;
	private String nombre;
	private String descripcion;
	private LocalDate fechaFundacion;

	private ArrayList<EdicionDTO> ediciones;

	public RevistaDTO(String issn, String nombre, String descripcion, LocalDate fechaFundacion) {
		super();
		this.issn = issn;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fechaFundacion = fechaFundacion;
	}

	public String getIssn() {
		return issn;
	}

	public void setIssn(String issn) {
		this.issn = issn;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public LocalDate getFechaFundacion() {
		return fechaFundacion;
	}

	public void setFechaFundacion(LocalDate fechaFundacion) {
		this.fechaFundacion = fechaFundacion;
	}

	public void addEdicion(Integer id, Integer volumen, LocalDate fechaFinEnvios, ArrayList<String> temas) {
		if (ediciones == null)
			ediciones = new ArrayList<EdicionDTO>();
		ediciones.add(new EdicionDTO(id, volumen, fechaFinEnvios, temas));

	}

	public ArrayList<EdicionDTO> getEdiciones() {
		return ediciones;
	}

}
