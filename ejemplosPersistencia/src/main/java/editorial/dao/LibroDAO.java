package editorial.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

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

}
