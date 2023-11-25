package journals.dto;

import java.time.LocalDate;


public class RevistaDTO {

	private String issn; 
	private String nombre;	
	private String descripcion;
	private LocalDate fechaFundacion;
	
	
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
	
	
	
}
