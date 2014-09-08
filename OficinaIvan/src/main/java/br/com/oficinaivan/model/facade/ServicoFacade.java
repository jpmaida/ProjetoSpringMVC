package br.com.oficinaivan.model.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.oficinaivan.model.Servico;
import br.com.oficinaivan.model.exception.BusinessException;
import br.com.oficinaivan.model.service.ServicoService;

@Component("servicoFacade")
public class ServicoFacade extends GenericFacade<Servico> {
	
	private ServicoService servicoService;
	
	@Autowired
	public ServicoFacade(ServicoService servicoService) {
		this.servicoService = servicoService;
		super.setGenericService(servicoService);
	}
}
