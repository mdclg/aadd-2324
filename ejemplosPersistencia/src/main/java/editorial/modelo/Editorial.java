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
import javax.persistence.Table;
import javax.persistence.Transient;


public class Editorial implements Serializable{

   
	private Integer id;
    private String nombre; 
    private LocalDate fechaFundacion;   
    private List<String> generos; 
    private long anyos;
    
    private ArrayList<Empleado> empleados;
    private Empleado director;
   

    public long getAnyos() {
        LocalDate.now();
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


    

}
