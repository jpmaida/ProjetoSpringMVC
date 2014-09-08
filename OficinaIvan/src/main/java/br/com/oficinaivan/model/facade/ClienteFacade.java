package br.com.oficinaivan.model.facade;

import java.util.Calendar;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.oficinaivan.model.Cliente;
import br.com.oficinaivan.model.exception.BusinessException;
import br.com.oficinaivan.model.service.ClienteService;
import br.com.oficinaivan.model.service.EmailService;
import br.com.oficinaivan.util.Util;

@Component("clienteFacade")
public class ClienteFacade extends GenericFacade<Cliente>{
	
	private ClienteService clienteService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	public ClienteFacade(ClienteService clienteService) {
		this.clienteService = clienteService;
		super.setGenericService(clienteService);
	}
	
	@Override
	public void persistir(Cliente cliente) throws BusinessException {
		clienteService.verificarClienteNomeIgual(cliente);
		clienteService.persistir(cliente);
		Calendar hoje = Util.obterDiaCorrente();
		if(cliente.getDataNascimento() != null && StringUtils.isNotBlank(cliente.getEmail())){
			Calendar nascimento = Calendar.getInstance();
			nascimento.setTime(cliente.getDataNascimento());
			if(hoje.get(hoje.DAY_OF_MONTH) == nascimento.get(hoje.DAY_OF_MONTH) 
					&& hoje.get(hoje.MONTH) == nascimento.get(hoje.MONTH)){
				emailService.enviarEmailAniversario(cliente.getEmail(), cliente.getNome());
			}
		}
	}

	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}
}
