package com.amedigital.amestarwars.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.amedigital.amestarwars.model.Planeta;
import com.amedigital.amestarwars.repository.PlanetaRepository;
import com.amedigital.amestarwars.swapi.model.Planet;

@RestController
@RequestMapping(value = "/api/planeta")
@CrossOrigin("*")
public class PlanetaController {

	@Autowired
	private PlanetaRepository planetaRepository;

	@GetMapping(value = "/carregaMassa")
	@CrossOrigin("*")
	public HttpStatus carregaMassa() throws Exception {

		for (int idPlaneta = 1; idPlaneta < 62; idPlaneta++) {

			RestTemplate restTemplate = new RestTemplate();
			Planet planet = restTemplate.getForObject("https://swapi.co/api/planets/" + idPlaneta, Planet.class);

			planetaRepository.add(planet.planetToPlaneta());
		}

		return HttpStatus.OK;
	}

	@GetMapping(value = "/listar")
	@CrossOrigin("*")
	public ResponseEntity<List<Planeta>> listar() throws Exception {
		List<Planeta> planetas = planetaRepository.findAll();
		return new ResponseEntity<List<Planeta>>(planetas, HttpStatus.OK);
	}

	@GetMapping(value = "/listar/nome/{nome}")
	@CrossOrigin("*")
	public ResponseEntity<Planeta> buscaPorNome(@PathVariable String nome) throws Exception {
		Planeta planeta = planetaRepository.findByName(nome);
		if (planeta == null) {
			return new ResponseEntity<Planeta>(planeta, HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<Planeta>(planeta, HttpStatus.FOUND);
	}

	@GetMapping(value = "/listar/id/{id}")
	@CrossOrigin("*")
	public ResponseEntity<Planeta> buscarPorId(@PathVariable String id) throws Exception {

		Planeta planeta = planetaRepository.findById(Long.parseLong(id));

		if (planeta == null) {
			return new ResponseEntity<Planeta>(planeta, HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<Planeta>(planeta, HttpStatus.FOUND);
	}

	@PostMapping(value = "/adicionar")
	@CrossOrigin("*")
	public ResponseEntity<Planeta> adicionar(@RequestBody Planeta planeta) throws Exception {
		planetaRepository.add(planeta);
		return new ResponseEntity<Planeta>(planeta, HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/deletar/{id}")
	@CrossOrigin("*")
	public HttpStatus remover(@PathVariable String id) throws NumberFormatException, Exception {
		Planeta planeta = planetaRepository.findById(Long.parseLong(id));
		if (planeta == null)
			return HttpStatus.NO_CONTENT;
		
		planetaRepository.remove(planeta);
		return HttpStatus.ACCEPTED;
	}
}
