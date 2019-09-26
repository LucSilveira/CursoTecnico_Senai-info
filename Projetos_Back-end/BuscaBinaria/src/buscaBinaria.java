import java.util.Random;

public class buscaBinaria {

	public static void main(String[] args) {
		
		//criando o vetor aleatorio
		int[] vetorDeBusca = geraVetor(500_000_000);
		int valorBuscado = 1500000000;
		//checando desempenho do busca
		long tempoAntes = System.currentTimeMillis();
		System.out.println("O valor buscado esta no indice: " + buscaLinear(vetorDeBusca, valorBuscado) );
		long tempoDepois = System.currentTimeMillis();
		//exibe o relatorio
		System.out.println("Tempo utilizado: " + (tempoDepois - tempoAntes));
	}
	
	public static int buscaLinear (int[] vetor, int procurado) {
		//Laco de procura do vetor 
		for(int i = 0; i < vetor.length; i++ ) {
			//Se o valor do vetor for igual ao procurado
			if(vetor[i] == procurado) {
				return i; 
			}
		}
		//caso o avlor n entre ertor -1
		return -1;
	}
		public static int[] geraVetor(int qntd){
			//Novo vetor com uma certa quantidade
			int[] novoVetor = new int[qntd];
			
			//aleatorio RANDOM
			Random aleatorio = new Random();
			
			//aplica um valor nos elementos do vetor
			for(int i = 0; i < qntd; i++){
				novoVetor[i] = aleatorio.nextInt(50000);
			}
			return novoVetor;
	}
}