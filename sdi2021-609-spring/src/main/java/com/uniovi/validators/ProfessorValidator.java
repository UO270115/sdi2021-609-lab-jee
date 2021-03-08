package com.uniovi.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniovi.entities.Professor;

@Component
public class ProfessorValidator implements Validator {

	@Override
	public boolean supports(Class<?> aClass) {
		return Professor.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Professor professor = (Professor) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dni", "Error.empty");
		String dni = professor.getDni();
		if(dni.length() != 9) {
			errors.rejectValue("dni", "Error.professor.dni.length");
		}
		String caracters = dni.substring(0, dni.length() - 2);
		if (!caracters.matches(".*[a-z].*")) {
			errors.rejectValue("dni", "Error.professor.dni.caracters");
		}
		String letra = dni.substring(dni.length() - 1);
		try {
			Integer.parseInt(letra);
		} catch (NumberFormatException nfe) {
			errors.rejectValue("dni", "Error.professor.dni.letter");
		}
		String nombre = professor.getNombre();
		if(nombre.length() < 5 || nombre.length() > 24) {
			errors.rejectValue("nombre", "Error.professor.name.length");
		}
		String categoria = professor.getCategoria();
		if(categoria.length() < 5 || categoria.length() > 24) {
			errors.rejectValue("categoria", "Error.professor.categoria.length");
		}
	}

}
