package journals.repositorio;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import journals.modelo.Revista;
import repositorio.RepositorioException;
import repositorio.RepositorioJPA;
import utils.EntityManagerHelper;

public class RepositorioJournalAdHocJPA extends RepositorioJPA<Revista> implements RepositorioJournalAdHoc{

	@Override
	public Class<Revista> getClase() {
		return Revista.class;
		
	}

	@Override
	public String getNombre() {
		return Revista.class.getName().substring(Revista.class.getName().lastIndexOf(".") + 1);
	}
	
	public List<Revista> getByNombre(String keyword) throws RepositorioException{
		EntityManager em = EntityManagerHelper.getEntityManager();
		try {
		Query query = em.createNamedQuery("Revista.getByname");
		
		query.setParameter("keyword", "%"+keyword+"%");
		
		query.setHint(QueryHints.REFRESH, HintValues.TRUE);
		
		return query.getResultList();
		}catch(RuntimeException ru) {
			throw new RepositorioException("Error buscando revistas por palabra clave", ru);
		}
		
	}

	@Override
	public List<Revista> getPublishedByTema(List<Integer> temas) throws RepositorioException {
		EntityManager em = EntityManagerHelper.getEntityManager();
		try {
		
			String queryString = "SELECT DISTINCT r "
					+ " FROM Revista r "
					+ " INNER JOIN r.ediciones e "
					+ " INNER JOIN e.temas t ON t.id in :temas "
					+ " WHERE e.fechaEdicion < :fecha ";
			
			Query query = em.createQuery(queryString);
			query.setParameter("temas", temas);
			query.setParameter("fecha", LocalDate.now());						
			query.setHint(QueryHints.REFRESH, HintValues.TRUE);
		
			return query.getResultList();
		}catch(RuntimeException ru) {
			throw new RepositorioException("Error buscando revistas publicadas por tema", ru);
		}
	}

	@Override
	public List<Revista> getByAceptaEnvios() throws RepositorioException {
		
		EntityManager em = EntityManagerHelper.getEntityManager();
		try {
		Query query = em.createNamedQuery("Revista.getByAceptaEnvios");
		
		query.setParameter("fecha", LocalDate.now());
		
		
		query.setHint(QueryHints.REFRESH, HintValues.TRUE);
		
		return query.getResultList();
		
		}catch(RuntimeException ru) {
			throw new RepositorioException("Error buscando revistas por palabra clave", ru);
		}
	}
	
	public List<Object[]> getByAceptaEnviosNativa() throws RepositorioException {
		EntityManager em = EntityManagerHelper.getEntityManager();
		try {
			
			String queryString = " SELECT r.id, r.nombre, r.descripcion, r.fecha_fundacion "
					+ " FROM revista r, edicion e "
					+ " WHERE r.id = e.revista_fk and e.fecha_fin_envios > ?1 ";
			
			Query query = em.createNativeQuery(queryString);
			
			query.setParameter(1, LocalDate.now());
			
			return query.getResultList();
			
			
		}catch(RuntimeException ru) {
			throw new RepositorioException("Error buscando revistas por palabra clave", ru);
		}
	}

	
	

}
