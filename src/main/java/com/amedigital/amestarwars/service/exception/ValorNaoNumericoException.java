package com.amedigital.amestarwars.service.exception;

@SuppressWarnings("serial")
public class ValorNaoNumericoException extends Exception {

	public ValorNaoNumericoException() {
		super("Valor passado não é numérico");
	}

}
