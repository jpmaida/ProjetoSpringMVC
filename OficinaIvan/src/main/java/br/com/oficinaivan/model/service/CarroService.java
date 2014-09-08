package br.com.oficinaivan.model.service;

import java.util.List;

import br.com.oficinaivan.model.Carro;
import br.com.oficinaivan.model.Cliente;
import br.com.oficinaivan.model.Servico;
import br.com.oficinaivan.model.exception.BusinessException;

public interface CarroService extends GenericService<Carro> {
	void verificarPlacaDuplicada(Carro carro) throws BusinessException;
	
	Cliente obterClientePorCarro(Long idCarro) throws BusinessException;
	
	List<Carro> listarCarroComServico() throws BusinessException;
	
	List<Servico> listaServicosPorCarro(String placa) throws BusinessException;
}
