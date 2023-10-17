package journals.repositorio;

import journals.modelo.Revista;
import repositorio.RepositorioJPA;

public class RepositorioJournalAdHocJPA extends RepositorioJPA<Revista> implements RepositorioJournalAdHoc{

	@Override
	public Class<Revista> getClase() {
		return Revista.class;
		
	}

	@Override
	public String getNombre() {
		return Revista.class.getName().substring(Revista.class.getName().lastIndexOf(".") + 1);
	}

}
