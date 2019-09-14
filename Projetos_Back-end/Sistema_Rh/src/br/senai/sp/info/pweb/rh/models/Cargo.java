package br.senai.sp.info.pweb.rh.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cargo {

	@Id
	private Long id;
	
	@Column(length = 30, nullable = false, unique = true)
	private String nome;
	
	@Column(nullable = false, unique = false, precision = 12, scale = 2)
	private Double salario;

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

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}
}
