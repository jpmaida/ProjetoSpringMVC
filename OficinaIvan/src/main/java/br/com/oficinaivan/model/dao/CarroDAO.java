package br.com.oficinaivan.model.dao;

import java.util.List;

import br.com.oficinaivan.model.Carro;
import br.com.oficinaivan.model.Cliente;
import br.com.oficinaivan.model.Servico;
import br.com.oficinaivan.model.exception.DaoException;

public interface CarroDAO extends GenericDAO<Carro>{
	boolean verificarPlacaDuplicada(String placa) throws DaoException;
	
	boolean verificarPlacaDuplicadaAlteracao(String placa, Long id) throws DaoException;
	
	Cliente obterClientePorCarro(Long idCarro) throws DaoException;
	
	List<Carro> listarCarrosComServico() throws DaoException;
	
	List<Servico> listaServicosPorCarro(String placa) throws DaoException;
}
