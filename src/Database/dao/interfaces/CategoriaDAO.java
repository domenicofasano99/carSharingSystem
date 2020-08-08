package Database.dao.interfaces;

import java.util.HashMap;
import java.util.List;

import model.Categoria;

public interface CategoriaDAO {

	public long getIdCategoria(String nomeCategoria);
	public Categoria getCategoriaById(int idCategoria);
	public List<Categoria> getCategorie();
	public void aggiungiCategoria(Categoria categoria) throws Exception;
}
