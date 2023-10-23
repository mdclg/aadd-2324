package journals.repositorio;

import java.util.List;

import journals.modelo.Revista;
import repositorio.RepositorioException;
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

	@Override
	public List<Revista> getPublishedByTema(List<Integer> temas) throws RepositorioException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Revista> getByAceptaEnvios() throws RepositorioException {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
