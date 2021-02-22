package com.uniovi.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.uniovi.entities.Mark;

@Component
public class MarksValidator implements Validator {

	@Override
	public boolean supports(Class<?> aClass) {
		return Mark.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Mark mark = (Mark) target;
		Double score = mark.getScore();
		if (score < 0 || score > 10) {
			errors.rejectValue("score", "Error.add-edit.mark.score");
		}if(mark.getDescription().length() < 20) {
			errors.rejectValue("description", "Error.add-edit.mark.description");
		}
	}

}
