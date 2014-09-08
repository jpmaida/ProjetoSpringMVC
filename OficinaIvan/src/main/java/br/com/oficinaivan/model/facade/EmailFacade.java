package br.com.oficinaivan.model.facade;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.oficinaivan.model.exception.BusinessException;
import br.com.oficinaivan.model.service.EmailService;

public class EmailFacade {
	
	@Autowired
	private EmailService emailService;
	
	public void enviarEmailAniversario(String to, String aniversariante) throws BusinessException{
		emailService.enviarEmailAniversario(to, aniversariante);
	}

	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}
}
