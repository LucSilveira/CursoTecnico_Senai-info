package br.com.bancoRoubo;

public enum TipoConta {
	CORRENTE("Conta Corrente"),
	SALARIO("Conta Salario"),
	POUPANCA("Conta Poupanca");
	
	public String titulo;
	TipoConta(String titulo){
		this.titulo = titulo;
	}
}
