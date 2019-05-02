package com.amedigital.amestarwars.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amedigital.amestarwars.model.Planeta;
import com.amedigital.amestarwars.service.PlanetaService;
import com.amedigital.amestarwars.service.exception.CamposNulosException;
import com.amedigital.amestarwars.service.exception.NomeJaExistenteException;
import com.amedigital.amestarwars.service.exception.PlanetaNaoEncontradoException;
import com.amedigital.amestarwars.service.exception.ValorNaoNumericoException;

@RestController
@RequestMapping(value = "/api/planeta")
@CrossOrigin("*")
public class PlanetaResource {

	@Autowired
	private PlanetaService planetaService;

	@PostMapping(value = "/adicionar")
	public ResponseEntity<Planeta> adicionar(@RequestBody Planeta planeta) throws Exception {
		planeta = planetaService.add(planeta);
		return new ResponseEntity<Planeta>(planeta, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/listar")
	public ResponseEntity<List<Planeta>> listar() throws Exception {
		List<Planeta> planetas = planetaService.findAll();
		return new ResponseEntity<List<Planeta>>(planetas, HttpStatus.OK);
	}

	@GetMapping(value = "/listar/nome/{nome}")
	public ResponseEntity<Planeta> buscaPorNome(@PathVariable String nome) throws Exception {
		Planeta planeta = planetaService.findByName(nome);
		return new ResponseEntity<Planeta>(planeta, HttpStatus.FOUND);
	}

	@GetMapping(value = "/listar/id/{id}")
	public ResponseEntity<Planeta> buscarPorId(@PathVariable String id) throws Exception {
		Planeta planeta = planetaService.findById(id);
		return new ResponseEntity<Planeta>(planeta, HttpStatus.FOUND);
	}

	@DeleteMapping(value = "/deletar/{id}")
	public HttpStatus remover(@PathVariable String id) throws NumberFormatException, Exception {
		planetaService.remover(id);
		return HttpStatus.ACCEPTED;
	}
	
	@ExceptionHandler({NomeJaExistenteException.class, CamposNulosException.class})
	public ResponseEntity<Erro> handleBadRequestException(Exception e) {
		return new ResponseEntity<>(new Erro(e.getMessage()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({PlanetaNaoEncontradoException.class})
	public ResponseEntity<Erro> handlePlanetaNaoEncontradoException(PlanetaNaoEncontradoException e) {
		return new ResponseEntity<>(new Erro(e.getMessage()), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({ValorNaoNumericoException.class})
	public ResponseEntity<Erro> handleValorNaoNumericoException(ValorNaoNumericoException e) {
		return new ResponseEntity<>(new Erro(e.getMessage()), HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping(value = "/carregaMassa")
	public HttpStatus carregaMassa() throws Exception {
		planetaService.carregarMassa();
		return HttpStatus.OK;
	}
}

class Erro {
	
	private final String erro;

	public Erro(String erro) {
		this.erro = erro;
	}

	public String getErro() {
		return erro;
	}
	
}
