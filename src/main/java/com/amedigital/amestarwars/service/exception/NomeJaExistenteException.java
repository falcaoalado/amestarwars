package com.amedigital.amestarwars.service.exception;

@SuppressWarnings("serial")
public class NomeJaExistenteException extends Exception {

	public NomeJaExistenteException() {
		super("Planeta com mesmo nome já existente.");
	}
	
}
