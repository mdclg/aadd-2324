package journals.web.revista;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import journals.servicio.IServicioJournal;
import repositorio.RepositorioException;
import servicio.FactoriaServicios;

@Named
@ViewScoped
public class AltaRevistaWeb implements Serializable {

	private String issn;

	private String nombre;

	private String descripcion;

	private LocalDate fechaFundacion;

	private IServicioJournal servicioJournals;
	
	@Inject
	protected FacesContext facesContext;

	protected ResourceBundle bundle;
	
	public AltaRevistaWeb() {

		servicioJournals = FactoriaServicios.getServicio(IServicioJournal.class);
	}
	
	@PostConstruct
	public void initBundle() {
	    bundle = ResourceBundle.getBundle("i18n.text", facesContext.getViewRoot().getLocale());

	}

	public void altaRevista() {
		try {
	        String resultado = servicioJournals.crear(issn, nombre, descripcion, fechaFundacion);
	        facesContext.addMessage(null,
	                new FacesMessage(FacesMessage.SEVERITY_INFO, "",bundle.getString("exitoRevista")));
	        /*
	        try {

	            facesContext.getExternalContext().redirect("detail.xhtml?id="+resultado);

	        } catch (IOException e) {

	            facesContext.addMessage(null,

	                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se ha podido navegar"));

	            e.printStackTrace();

	        }*/

	    } catch (RepositorioException e) {

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
