package br.senai.sp.informatica.alg.listaligada;

public class Celula {
	
	public Celula(Produto valor){
		this.valor = valor;
	}
	
	private Produto valor;
	
	public Produto getValor() {
		return valor;
	}

	public void setValor(Produto valor) {
		this.valor = valor;
	}

	public Celula getProximo() {
		return proximo;
	}

	public void setProximo(Celula proximo) {
		this.proximo = proximo;
	}

	private Celula proximo;

}
