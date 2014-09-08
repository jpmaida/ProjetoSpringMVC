package br.com.oficinaivan.model.service;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.oficinaivan.model.Cliente;
import br.com.oficinaivan.model.dao.ClienteDAO;
import br.com.oficinaivan.model.exception.BusinessException;
import br.com.oficinaivan.model.exception.DaoException;

/**
 * Classe de neg�cio que encapsula todo o neg�cio relacionado a Cliente.
 */
@Service("clienteService")
@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
@Scope(value=ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ClienteServiceImpl extends GenericServiceImpl<Cliente> implements ClienteService{
	
	private ClienteDAO clienteDAO;
	
	@Autowired
	public ClienteServiceImpl(ClienteDAO clienteDAO) {
		this.clienteDAO = clienteDAO;
		super.setGenericDAO(clienteDAO);
	}
	
	public void verificarClienteNomeIgual(Cliente cliente) throws BusinessException {
		try{
			boolean isAlteracao = false;
			if(cliente.getId() != null){
				isAlteracao = true;
			}
			boolean isNomeIgual = isAlteracao ? clienteDAO.verificarClienteNomeIgualAlteracao(cliente.getNome(), cliente.getId()) : clienteDAO.verificarClienteNomeIgual(cliente.getNome());
			if(isNomeIgual){
				throw new BusinessException("cliente.nome.duplicado");
			}
		} catch (DaoException daoe){
			daoe.printStackTrace();
		}
	}

	public void setClienteDAO(ClienteDAO clienteDAO) {
		this.clienteDAO = clienteDAO;
	}
	
	@Override
	public Cliente obter(Long id) throws BusinessException {
		Cliente cliente = super.obter(id);
		Hibernate.initialize(cliente.getEndereco());
		Hibernate.initialize(cliente.getListaCarro());
		for(int i=0; i<cliente.getListaCarro().size(); i++){
			Hibernate.initialize(cliente.getListaCarro().get(i).getMarca());
		}
		return cliente;
	}
}