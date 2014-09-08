package br.com.oficinaivan.model.dao;

import org.springframework.stereotype.Repository;

import br.com.oficinaivan.model.Marca;
import br.com.oficinaivan.model.exception.DaoException;

@Repository("marcaDAO")
public class MarcaDAOImpl extends GenericDAOImpl<Marca> implements MarcaDAO {

	@Override
	protected Class<Marca> getClazz() {
		return Marca.class;
	}
	
	public boolean verificarMarcaDuplicada(String nome) throws DaoException{
		return super.getSession().getNamedQuery("verificarMarcaDuplicada").setString("nome", nome).uniqueResult() != null ? true : false;
	}
	
	public boolean verificarMarcaDuplicadaAlteracao(String nome, Long id) throws DaoException{
		return super.getSession().getNamedQuery("verificarMarcaDuplicadaAlteracao").setString("nome", nome).setLong("id", id).uniqueResult() != null ? true : false;
	}
}
