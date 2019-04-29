package com.amedigital.amestarwars.service.exception;

import javax.validation.ConstraintViolationException;

@SuppressWarnings("serial")
public class CamposNulosException extends Exception {

	public CamposNulosException(ConstraintViolationException e) {
		super(e.getMessage());
	}
}
