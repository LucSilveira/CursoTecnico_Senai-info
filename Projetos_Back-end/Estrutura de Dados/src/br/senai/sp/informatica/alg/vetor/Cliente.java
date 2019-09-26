package br.senai.sp.informatica.alg.vetor;

public class Cliente {
	
	/*
	 * Propiedades
	 */
	private String nome;
	
	/*
	 * Getters & Setters
	 */
	public String getNome() {
		return nome;
	}

	public Cliente(String nome) {
		super();
		this.nome = nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
	@Override
	public String toString() {
		return "Cliente [nome=" + nome + "]";
	}
	

}
