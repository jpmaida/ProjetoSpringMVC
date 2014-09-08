package br.com.oficinaivan.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.oficinaivan.model.Servico;

@Component("servicoValidator")
public class ServicoValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Servico.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "descricao", "campo.obrigatorio", new String[]{"Descrição"});
		ValidationUtils.rejectIfEmpty(errors, "carro.id", "campo.obrigatorio", new String[]{"Carro"});
		ValidationUtils.rejectIfEmpty(errors, "cliente.id", "campo.obrigatorio", new String[]{"Cliente"});
	}
}
