package br.com.oficinaivan.model.service;

import br.com.oficinaivan.model.exception.BusinessException;

public interface EmailService {
	void enviarEmailAniversario(String to, String aniversariante) throws BusinessException;
}
