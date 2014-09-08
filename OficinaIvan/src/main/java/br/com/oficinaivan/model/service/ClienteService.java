package br.com.oficinaivan.model.service;

import br.com.oficinaivan.model.Cliente;
import br.com.oficinaivan.model.exception.BusinessException;

public interface ClienteService extends GenericService<Cliente>{
	void verificarClienteNomeIgual(Cliente cliente) throws BusinessException;
}
