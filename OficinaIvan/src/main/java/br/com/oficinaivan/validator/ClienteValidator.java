package br.com.oficinaivan.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.oficinaivan.model.Cliente;

@Component("clienteValidator")
public class ClienteValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Cliente.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "campo.obrigatorio", new String[]{"Nome"});
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endereco.logradouro", "campo.obrigatorio", new String[]{"Logradouro"});
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endereco.numero", "campo.obrigatorio", new String[]{"Número"});
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endereco.bairro", "campo.obrigatorio", new String[]{"Bairro"});
	}
}