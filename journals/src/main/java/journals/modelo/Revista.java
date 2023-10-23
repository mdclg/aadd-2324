package journals.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import repositorio.Identificable;


@Entity
@Table(name="revista")
public class Revista implements Identificable{

	@Id
	private String id; //El id será el ISSN de la revista
	@Column(name="nombre")
	private String nombre;
	@Lob
	@Column(name="descripcion")
	private String descripcion;
	@Column(name = "fecha_fundacion", columnDefinition = "DATE")
	private LocalDate fechaFundacion;
	
	@OneToMany(mappedBy = "revista", cascade = CascadeType.ALL)
	private ArrayList<Edicion> ediciones;
	
	

	public Revista(String id, String nombre, String descripcion, LocalDate fechaFundacion) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fechaFundacion = fechaFundacion;
	}
	
	public Revista() {
		
	}

	public ArrayList<Edicion> getEdiciones() {
		return ediciones;
	}

	public void setEdiciones(ArrayList<Edicion> ediciones) {
		this.ediciones = ediciones;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;		
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
	
	public void addEdicion(Integer volumen, LocalDate fechaEdicion, LocalDate fechaFinEnvios, List<String[]> temasTexto) {
		
		
		Edicion edicion = new Edicion(volumen,fechaEdicion,fechaFinEnvios,this,temasTexto);
		
		if(ediciones == null) {
			ediciones = new ArrayList<Edicion>();
		}
		ediciones.add(edicion);
	}
	
	@Override
	public String toString() {
		return "Revista [ISSN=" + id + ", nombre=" + nombre + "]";
	}
	
	
	
}
