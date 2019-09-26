package br.senai.sp.informatica.alg.vetor;

public class VetorAtrelado {
	
	//Criando o vetor para armazenar os dados
	private Object[] dados = new Object[10];
	
	//Armazena a quantidade de elementos do vetor
	private int tamanho = 0;
	
	/**
	 * Adiciona um elemento no vetor. Se este ultrapassar a capacidade,
	 * dobrar o tamanho do vetor de armazenamento
	 * @param valor - O elemento que será adicionado
	 * @return - O objeto alocado no vetor
	 */
	public void adicionar(Object valor, int posicao) {
		//Garantindo o tamanho da alocacao
		this.garantirEspaco();
		
		for(int i = this.tamanho; i > posicao; i--) {
			this.dados[i] = this.dados[i-1];
		}
			this.dados[posicao] = valor;
			//Aumenta a quantidade de elementos adicionados
			this.tamanho++;
	}
	
	public Object pegar(int posicao) {
		this.checarPosicao(posicao);
		return this.dados[posicao];
	}
		
	/**
	 * checa se a posição né valida no vetor
	 * Caso não seja dispara um exception
	 * @param posicao - Aposicao verificada
	 */
	private void checarPosicao(int posicao) {
		if(posicao < 0 || posicao >= this.tamanho) {
			throw new RuntimeException("A posicao" + posicao + " é invalida");
		}
	}

	/**
	 * verifica se o tamanho do vetor + 1 ultrapassa o limite do vetor
	 * de alocacao. Caso verdadeiro: Cria um vetor novo com o dobro
	 * de tamanho e passa os elemetnos do vetor antigo para o novo
	 */
	private void garantirEspaco() {
		//Dobramos o tamanho do vetor se o tamanho
		//lógico for igual ao físico
		if(this.dados.length == this.tamanho) {
			//Criando um vetor com o dobro de capacidade
			Object[] novoVetor = new Object[this.tamanho * 2];
			for(int i = 0; i < this.tamanho; i++) {
				novoVetor[i] = this.dados[i];
			}
			//determina que o novo vetor será o vetor 
			this.dados = novoVetor;
		}
	}
	
	public void remover (int posicao) {
		this.checarPosicao(posicao);
		//remover o elemento da posicao
		this.dados[posicao] = null;
		//realcacao para esquerda
		for(int i = posicao; i < this.tamanho - 1; i++) {
			this.dados[i] = this.dados[i+1];
			this.dados[i+1]=null;
		}
		this.tamanho--;
	}
	
	public int tamanho() {
		return this.tamanho;
	}
	/**
	 * Devolve uma flag indicando se um dado objeto existe
	 * dentro do vetor
	 * @param valor - O objeto procurado
	 * @return
	 */
	public boolean contem(Object valor) {
		//percorre cada elemento do vetor e verfica 
		//se é igual ao inserido
		for(int i = 0; i < this.tamanho; i++) {
			if(this.dados[i].equals(valor)) {
				return true;
			}
		}
		return false;
	}
	
	public void addEspecifico(Object valor, int posicao) {
		this.checarPosicao(posicao);
		//selecionar o elemento de um indice
		for(int i = posicao; i < this.tamanho - 1; i++) {
			this.dados[i] = this.dados[i+1];
			this.dados[posicao]=valor;
			
			this.tamanho++;
		}
		
	}
}