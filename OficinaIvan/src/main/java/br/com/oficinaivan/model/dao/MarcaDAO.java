package br.com.oficinaivan.model.dao;

import br.com.oficinaivan.model.Marca;
import br.com.oficinaivan.model.exception.DaoException;

public interface MarcaDAO extends GenericDAO<Marca> {
	boolean verificarMarcaDuplicada(String nome) throws DaoException;
	
	boolean verificarMarcaDuplicadaAlteracao(String nome, Long id) throws DaoException;
}
