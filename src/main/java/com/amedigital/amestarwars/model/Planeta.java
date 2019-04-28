package com.amedigital.amestarwars.model;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "planetas")
public class Planeta {

	@Id
	private Long id;

	@Indexed(name = "nome", unique = true)
	@TextIndexed
	@NotNull(message = "Nome não pode ser nulo")
	private String nome;
	@NotNull(message = "Clima não pode ser nulo")
	private String clima;
	@NotNull(message = "Terreno não pode ser nulo")
	private String terreno;
	private Integer quantidadeAparicoes;

	public Planeta() {
	}

	public Planeta(Long id, String nome, String clima, String terreno, Integer quantidadeAparicoes) {
		this.id = id;
		this.nome = nome;
		this.clima = clima;
		this.terreno = terreno;
		this.quantidadeAparicoes = quantidadeAparicoes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getClima() {
		return clima;
	}

	public void setClima(String clima) {
		this.clima = clima;
	}

	public String getTerreno() {
		return terreno;
	}

	public void setTerreno(String terreno) {
		this.terreno = terreno;
	}

	public Integer getQuantidadeAparicoes() {
		return quantidadeAparicoes;
	}

	public void setQuantidadeAparicoes(Integer quantidadeAparicoes) {
		this.quantidadeAparicoes = quantidadeAparicoes;
	}

	@Override
	public String toString() {
		return "Planeta [id=" + id + ", nome=" + nome + ", clima=" + clima + ", terreno=" + terreno
				+ ", quantidadeAparicoes=" + quantidadeAparicoes + "]";
	}

}
