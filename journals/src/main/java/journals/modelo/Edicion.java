package journals.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="edicion")
public class Edicion {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="volumen")
	private Integer volumen;
	@Column(name = "fecha_edicion", columnDefinition = "DATE")
	private LocalDate fechaEdicion;
	@Column(name = "fecha_fin_envios", columnDefinition = "DATE")
	private LocalDate fechaFinEnvios;
	
	@ManyToOne
	@JoinColumn(name = "revista_fk")
	private Revista revista;
	
	@OneToMany(mappedBy = "edicion", cascade = CascadeType.ALL)
	private ArrayList<Tema> temas;
	
	public Edicion() {
		
	}
	
	public Edicion(Integer volumen, LocalDate fechaEdicion, LocalDate fechaFinEnvios, Revista revista,
			List<String[]> temasTexto) {
		
		ArrayList<Tema> temas = new ArrayList<Tema>();
		for(String[] t:temasTexto) {
			temas.add(new Tema(t[0], t[1], this));
		}
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
