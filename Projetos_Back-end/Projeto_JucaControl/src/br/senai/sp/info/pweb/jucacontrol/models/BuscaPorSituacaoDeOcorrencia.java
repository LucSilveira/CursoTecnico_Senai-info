package br.senai.sp.info.pweb.jucacontrol.models;

public enum BuscaPorSituacaoDeOcorrencia {
	
	TODOS("Todos"),
	
	/**
	 * Busca ocorr�ncias que n�o possuem um t�cnico
	 */
	ABERTO("Aberto"),
	
	/**
	 * Busca ocorr�ncias que n�o possuem data de conclus�o do emissor
	 */
	EM_ATENDIMENTO("Em atendimento"),
	
	/**
	 * Busca ocorr�ncias que possuem data de conclus�o de emissor
	 */
	ENCERRADAS ("Encerrados");
	
	String descricao;
	
	private BuscaPorSituacaoDeOcorrencia(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
