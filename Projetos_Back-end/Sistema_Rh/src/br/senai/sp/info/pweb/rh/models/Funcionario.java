package br.senai.sp.info.pweb.rh.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Funcionario {

	@Id
	private Long id;
	
	@Column(length = 80, nullable = false, unique = false)
	private String nome;
	
	
	@OneToOne
	@JoinColumn(name = "endereco_id", nullable = false)
	private Endereco endereco;
	
	@ManyToOne
	@JoinColumn(name = "cargo_id", nullable = false)
	private Cargo cargo;
	
	@ManyToMany
	@JoinTable(
			name = "alocacoes", joinColumns = @JoinColumn(name = "funcionario_id", nullable = false),
			inverseJoinColumns = @JoinColumn(name = "setor_id", nullable = false))
	private List<Setor> setores;
	
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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public List<Setor> getSetores() {
		return setores;
	}

	public void setSetores(List<Setor> setores) {
		this.setores = setores;
	}

}
