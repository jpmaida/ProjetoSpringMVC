package br.com.oficinaivan.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.oficinaivan.model.Carro;

@Component("carroValidator")
public class CarroValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Carro.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "placa", "campo.obrigatorio", new String[]{"Placa"});
	}
}
