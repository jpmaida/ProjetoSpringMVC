package br.com.oficinaivan.model.service;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.com.oficinaivan.model.Servico;
import br.com.oficinaivan.model.dao.ServicoDAO;
import br.com.oficinaivan.model.exception.BusinessException;

@Service("servicoService")
@Scope(value=ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ServicoServiceImpl extends GenericServiceImpl<Servico> implements ServicoService {

	private ServicoDAO servicoDAO;
	
	@Autowired
	public ServicoServiceImpl(ServicoDAO servicoDAO) {
		this.servicoDAO = servicoDAO;
		super.setGenericDAO(servicoDAO);
	}
	
	@Override
	public Servico obter(Long id) throws BusinessException {
		Servico servico = super.obter(id);
		Hibernate.initialize(servico.getCarro());
		Hibernate.initialize(servico.getCliente());
		Hibernate.initialize(servico.getCarro().getMarca());
		return servico;
	}
}
