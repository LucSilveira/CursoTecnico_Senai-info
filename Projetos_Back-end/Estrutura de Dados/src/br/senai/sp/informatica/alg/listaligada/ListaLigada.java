package br.senai.sp.informatica.alg.listaligada;

public class ListaLigada {
	
	/**
	 * Armazena o primeiro valor
	 */
	private Celula primeiro = null;
	
	/**
	 * Armazena o ultimo valor
	 */
	private Celula ultimo = null;
	
	/**
	 * Armazena o tamanho
	 */
	private int tamanho = 0;
	
	/**
	 * Adiciona um novo valor no começo da lista
	 * @param valor - O valor adicionado
	 */
	
	public void addNoComeco(Produto valor) {
		//Criando a celula do elemento add
		Celula novo = new Celula(valor);
		
		//falando q o proximo elemento será o antigo 1º
		novo.setProximo(this.primeiro);
		
		//definindo que o novo elemento é o 1º
		this.primeiro = novo;
		
		//se na lista não tem ninguem o novo elemento será tbm o ultimo
		if(this.tamanho == 0) {
			this.ultimo = novo;
		}
		
		//Aumenta o tamanho
		this.tamanho++;
		
	}
	
	public void addNoFim(Produto valor) {
		//Criando a celula do elemento add
		Celula novo = new Celula(valor);
		
		//falando q o proximo elemento será o antigo 1º
		novo.setProximo(this.ultimo);
		
		//definindo que o novo elemento é o 1º
		this.ultimo = novo;
		
		//se na lista não tem ninguem o novo elemento será tbm o ultimo
		if(this.tamanho == 0) {
			this.primeiro = this.ultimo;
		}
		
		//Aumenta o tamanho
		this.tamanho++;
		
	}
	
	/**
	 * Pega um elemento de cada posicao
	 * @param posicao
	 * @return
	 */
	public Produto pegar(int posicao) {
		
		//cria a celula que ira interar o laco
		//comecando do primeiro
		Celula interadora = this.primeiro;
		
		//pega o proximo elemento até a posicao
		for(int i = 1; i <= posicao; i++) {
			interadora = interadora.getProximo();
		}
		return interadora.getValor();
	}
	
	
	public int tamanho() {
		return this.tamanho;
	}

}
