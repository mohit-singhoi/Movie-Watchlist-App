package com.example.mohit.watchlist.entity.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PriorityAnnotationLogic implements ConstraintValidator<Priority, String> {

//	@Override
//	public boolean isValid(String value, ConstraintValidatorContext context) {
//		// TODO Auto-generated method stub
//		return value.trim().length() == 1 && "LHM".contains(value.trim());
//	}
	
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

	    if (value == null || value.trim().isEmpty()) {
	        return false;
	    }

	    value = value.trim().toLowerCase();

	    return value.equals("l") ||
	           value.equals("low") ||

	           value.equals("m") ||
	           value.equals("med") ||
	           value.equals("medium") ||

	           value.equals("h") ||
	           value.equals("high");
	}

}
