package br.com.ozcorp;

public enum Designacao {
	DIRETOR("Diretor"), SECRETARIO("Secret�rio"), GERENTE("Gerente"), ENGENHEIRO("Engenheiro"), ANALISTA("Analista");

	public String designacao;

	Designacao(String designacao) {
		this.designacao = designacao;
	}

}
