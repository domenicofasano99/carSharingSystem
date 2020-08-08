package Database.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import Database.dao.factory.JpaDAOfactory;
import Database.dao.interfaces.CategoriaDAO;
import model.Categoria;

public class JpaCategoriaDAO implements CategoriaDAO {
	public static JpaCategoriaDAO instance;

	private JpaCategoriaDAO() {
	}

	public static JpaCategoriaDAO getInstance() {
		if (instance == null)
			instance = new JpaCategoriaDAO();
		return instance;
	}

	@Override
	public void aggiungiCategoria(Categoria categoria) {
		EntityManager manager = JpaDAOfactory.getManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		manager.persist(categoria);
		transaction.commit();
	}

	@Override
	public long getIdCategoria(String nomeCategoria) {
		// TODO fammi sapere
		Query query = JpaDAOfactory.getManager()
				.createQuery("select c from Categoria c where c.nomeCategoria=:nomeCategoria");
		query.setParameter("nomeCategoria", nomeCategoria);
		return ((Categoria) query.getSingleResult()).getIdCategoria();
	}

	@Override
	public List<Categoria> getCategorie() {
		Query query = JpaDAOfactory.getManager().createNamedQuery("Categoria.findAll", Categoria.class);
		List<Categoria> categorie = query.getResultList();
		return categorie;
	}

	@Override
	public Categoria getCategoriaById(int idCategoria) {
		Query query = JpaDAOfactory.getManager()
				.createQuery("select c from Categoria c where c.idCategoria=:idCategoria");
		query.setParameter("idCategoria", idCategoria);
		return (Categoria) query.getSingleResult();
	}

}
