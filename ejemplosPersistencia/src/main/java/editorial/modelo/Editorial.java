package editorial.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="editorial")
public class Editorial implements Serializable{

   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	@Column(name="nombre")
    private String nombre; 
	@Column(name="fecha_fundacion", columnDefinition = "DATE")
    private LocalDate fechaFundacion; 
	@ElementCollection
	@CollectionTable(name="generos")
    private List<String> generos; 
	@Transient
    private long anyos;
    
	@OneToMany(mappedBy = "editorial")
    private ArrayList<Empleado> empleados;
	
	@OneToOne
    private Empleado director;
    
	@ManyToMany
	@JoinTable(name="editorial_distribuidor", joinColumns = {
			@JoinColumn(name="editorial")}, inverseJoinColumns = {
					@JoinColumn(name="distribuidor")})
    private List<Distribuidor> distribuidores;
   

    public long getAnyos() {
        return ChronoUnit.YEARS.between(fechaFundacion, LocalDate.now());
    }


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public LocalDate getFechaFundacion() {
		return fechaFundacion;
	}


	public void setFechaFundacion(LocalDate fechaFundacion) {
		this.fechaFundacion = fechaFundacion;
	}


	public List<String> getGeneros() {
		return generos;
	}


	public void setGeneros(List<String> list) {
		this.generos = list;
	}


	public void setAnyos(long anyos) {
		this.anyos = anyos;
	}


	public ArrayList<Empleado> getEmpleados() {
		return empleados;
	}


	public void setEmpleados(ArrayList<Empleado> empleados) {
		this.empleados = empleados;
	}


	public Empleado getDirector() {
		return director;
	}


	public void setDirector(Empleado director) {
		this.director = director;
	}

	@Override
	public String toString() {
		return "Editorial [ID=" + id + ", nombre=" + nombre + "]";
	}
    

}
