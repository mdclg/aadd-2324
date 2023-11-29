package journals.web.revista;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import journals.dto.RevistaDTO;
import journals.servicio.IServicioJournal;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;
import servicio.FactoriaServicios;
import servicio.ServicioException;

@Named
@ViewScoped
public class RevistaDetailWeb implements Serializable{  

    private String idRevista;   

    private IServicioJournal servicioJournals;  

    private RevistaDTO revista; 
    
    private Integer volumen;
    
    private LocalDate fechaEdicion;
    
    private LocalDate fechaFinEnvios;

    @Inject

    protected FacesContext facesContext;

        

    public RevistaDetailWeb() {     

        servicioJournals = FactoriaServicios.getServicio(IServicioJournal.class);        

    }

    public void load() {

        try {

            revista = servicioJournals.getByIssn(idRevista);

        } catch (ServicioException e) {

            facesContext.addMessage(null,

                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", e.getMessage()));

        }

    }
    
    public void addEdicion() {
        try {
            servicioJournals.addEdicion(revista.getIssn(), volumen, fechaEdicion, fechaFinEnvios, new ArrayList<String[]>() ); 
            facesContext.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "","Edición añadida correctamente"));
            
            load();
        } catch (RepositorioException | EntidadNoEncontrada e) {

            facesContext.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", e.getMessage()));

            e.printStackTrace();
        }
        catch(IllegalArgumentException e) {
            facesContext.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "", e.getMessage()));
            e.printStackTrace();
        }
    }


    public String getIdRevista() {

        return idRevista;

    }


    public void setIdRevista(String idRevista) {

        this.idRevista = idRevista;

    }

    public RevistaDTO getRevista() {

        return revista;

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



}
