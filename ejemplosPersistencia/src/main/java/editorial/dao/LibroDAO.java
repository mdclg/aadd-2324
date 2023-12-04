package editorial.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import editorial.modelo.Libro;
import utils.EntityManagerHelper;

public class LibroDAO {

	public static LibroDAO libro;

	public static LibroDAO getLibroDAO() {
		if (libro == null) {
			libro = new LibroDAO();
		}
		return libro;
	}

	public Libro findById(Integer id) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		return em.find(Libro.class, id);
	}

	public Libro saveLibro(Date fecha, String formato, String isbn, Integer numPaginas, String titulo, Float precio) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		try {
			em.getTransaction().begin();
			Libro l = new Libro();
			l.setFechaPublicacion(fecha);
			l.setFormato(formato);
			l.setIsbn(isbn);
			l.setNumPaginas(numPaginas);
			l.setTitulo(titulo);
			l.setPrecio(precio);
			em.persist(l);
			em.getTransaction().commit();
			return l;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}

	}

	public List<Libro> findAllLibros() {
		EntityManager em = EntityManagerHelper.getEntityManager();
		return em.createNamedQuery("findAllLibro", Libro.class).getResultList();
	}

	public List<Libro> findLibrosByTitulo(String palabraClave) {

		try {

			String queryString = " SELECT l FROM Libro l where l.id is not null ";

			if (palabraClave != null) {
				queryString += " and l.titulo like :palabraClave ";
			}

			Query query = EntityManagerHelper.getEntityManager().createQuery(queryString);

			if (palabraClave != null) {
				query.setParameter("palabraClave", "%" + palabraClave + "%");
			}

			query.setHint(QueryHints.REFRESH, HintValues.TRUE);

			return query.getResultList();

		} catch (RuntimeException re) {

			throw re;

		}

	}

	public boolean updateLibro(Libro l) {

		EntityManager em = EntityManagerHelper.getEntityManager();

		try {

			em.getTransaction().begin();

			l = em.merge(l);

			em.getTransaction().commit();

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;

		} finally {

			if (em.getTransaction().isActive()) {

				em.getTransaction().rollback();

			}

			em.close();

		}

	}

}
