package com.amedigital.amestarwars.service;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.amedigital.amestarwars.model.Planeta;
import com.amedigital.amestarwars.resource.PlanetaRepository;
import com.amedigital.amestarwars.service.exception.CamposNulosException;
import com.amedigital.amestarwars.service.exception.NomeJaExistenteException;
import com.amedigital.amestarwars.service.exception.PlanetaNaoEncontradoException;
import com.amedigital.amestarwars.service.exception.ValorNaoNumericoException;
import com.amedigital.amestarwars.swapi.model.Planet;

@Service
public class PlanetaService {

	@Autowired
	private PlanetaRepository planetaRepository;

	public Planeta add(Planeta planeta) throws Exception {

		Planeta planetaAdicionado = new Planeta();

		try {
			planetaAdicionado = planetaRepository.add(planeta);
		} catch (DuplicateKeyException e) {
			throw new NomeJaExistenteException();
		} catch (ConstraintViolationException e) {
			throw new CamposNulosException(e);
		}

		return planetaAdicionado;
	}

	public Planeta findByName(String nome) throws Exception {
		Planeta planeta = new Planeta();
		planeta = planetaRepository.findByName(nome);

		if (planeta == null)
			throw new PlanetaNaoEncontradoException();

		return planeta;
	}

	public Planeta findById(String id) throws Exception {
		Planeta planeta = new Planeta();
		planeta = planetaRepository.findById(retornaValorNumerico(id));

		if (planeta == null)
			throw new PlanetaNaoEncontradoException();

		return planeta;
	}

	public List<Planeta> findAll() throws Exception {
		List<Planeta> planetas = planetaRepository.findAll();
		if (planetas.size() == 0)
			throw new PlanetaNaoEncontradoException();

		return planetas;
	}

	public boolean remover(String id) throws Exception {
		Planeta planeta = this.findById(id);
		planetaRepository.remove(planeta);

		return true;
	}

	public void carregarMassa() throws Exception{
		
		for (int idPlaneta = 1; idPlaneta < 62; idPlaneta++) {

			RestTemplate restTemplate = new RestTemplate();
			Planet planet = restTemplate.getForObject("https://swapi.co/api/planets/" + idPlaneta, Planet.class);

			this.add(planet.planetToPlaneta());
		}
		
	}
	
	private Long retornaValorNumerico(String valor) throws ValorNaoNumericoException {
		try {
			return Long.parseLong(valor);
		} catch (IllegalArgumentException e) {
			throw new ValorNaoNumericoException();
		}
	}
}
