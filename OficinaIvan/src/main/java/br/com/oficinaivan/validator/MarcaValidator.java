package br.com.oficinaivan.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.oficinaivan.model.Marca;

@Component("marcaValidator")
public class MarcaValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Marca.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "campo.obrigatorio", new String[]{"Nome"});
	}

}
