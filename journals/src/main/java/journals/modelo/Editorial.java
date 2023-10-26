package journals.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.bson.types.ObjectId;


public class Editorial implements Serializable{

	
    private ObjectId id;
    private String nombre; 
    private LocalDate fechaFundacion; 
    private String editorJefe;
    private List<String> paisesPublicacion; 
	
    private Direccion sede;
    
    public Editorial() {
    	
    }
    
    
    public Editorial(String nombre, LocalDate fechaFundacion, String editorJefe,
			List<String> paisesPublicacion, Direccion sede) {
		this.nombre = nombre;
		this.fechaFundacion = fechaFundacion;
		this.editorJefe = editorJefe;
		this.paisesPublicacion = paisesPublicacion;
		this.sede = sede;
	}
    
    


	public ObjectId getId() {
		return id;
	}





	public void setId(ObjectId id) {
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





	public String getEditorJefe() {
		return editorJefe;
	}





	public void setEditorJefe(String editorJefe) {
		this.editorJefe = editorJefe;
	}





	public List<String> getPaisesPublicacion() {
		return paisesPublicacion;
	}





	public void setPaisesPublicacion(List<String> paisesPublicacion) {
		this.paisesPublicacion = paisesPublicacion;
	}





	public Direccion getSede() {
		return sede;
	}





	public void setSede(Direccion sede) {
		this.sede = sede;
	}





	@Override
	public String toString() {
		return "Editorial [ID=" + id + ", nombre=" + nombre + "]";
	}
    

}
