package br.com.oficinaivan.model.dao;

import br.com.oficinaivan.model.Cliente;
import br.com.oficinaivan.model.exception.DaoException;

public interface ClienteDAO extends GenericDAO<Cliente>{
	
	/**
     * Assinatura de método que verifica se existem clientes com o mesmo nome.
     * @param cliente - Representa o cliente que terá seu nome verificado.
     * @return boolean - Retorna true caso exista um cliente com nome igual ou, false caso não exista.
     * @throws DaoException Representa um erro na camada DAO.
     */
	boolean verificarClienteNomeIgual(String nome) throws DaoException;
	
	boolean verificarClienteNomeIgualAlteracao(String nome, Long id) throws DaoException;
}