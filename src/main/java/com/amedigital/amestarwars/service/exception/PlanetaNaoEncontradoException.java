package com.amedigital.amestarwars.service.exception;

@SuppressWarnings("serial")
public class PlanetaNaoEncontradoException extends Exception{

	public PlanetaNaoEncontradoException() {
		super("Planeta não encontrado");
	}
}
