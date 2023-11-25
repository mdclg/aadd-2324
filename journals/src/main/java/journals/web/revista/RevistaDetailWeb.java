package journals.web.revista;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import journals.dto.RevistaDTO;
import journals.servicio.IServicioJournal;
import servicio.FactoriaServicios;
import servicio.ServicioException;

@Named
@ViewScoped
public class RevistaDetailWeb implements Serializable{
	
	private String idRevista;
	
	private IServicioJournal servicioJournals;
	
	private RevistaDTO revista;
	
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

	public String getIdRevista() {
		return idRevista;
	}

	public void setIdRevista(String idRevista) {
		this.idRevista = idRevista;
	}
	public RevistaDTO getRevista() {
		return revista;
	}
	
	
	
	

}
