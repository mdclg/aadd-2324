package journals.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class ActiveLocale implements Serializable {

	private Locale actual;
	private List<Locale> localesDisponibles;
	@Inject
	private FacesContext context;

	@PostConstruct
	public void init() {
		Application app = context.getApplication();
		actual = app.getViewHandler().calculateLocale(context);
		localesDisponibles = new ArrayList<>();
		localesDisponibles.add(app.getDefaultLocale());
		Iterator<Locale> supported = app.getSupportedLocales();
		while(supported.hasNext()) {
			localesDisponibles.add(supported.next());
		}			
	}

	public void reload() {
		context.getPartialViewContext().getEvalScripts().add("location.replace(location)");
	}

	public Locale getActual() {
		return actual;
	}

	public String getLanguageTag() {
		return actual.toLanguageTag();
	}

	public void setLanguageTag(String languageTag) {
		actual = Locale.forLanguageTag(languageTag);
	}

	public List<Locale> getLocalesDisponibles() {
		return localesDisponibles;
	}
}
