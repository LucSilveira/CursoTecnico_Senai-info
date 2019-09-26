package br.senai.sp.informatica.algoritmo.strings;

public class MetodosString {
	/**
	 * Retorna um determinado caractere em determinada posição
	 * @param texto - O texto que será usado na pesquisa
	 * @param posicao - A posição do caractere
	 * @return o cararctere n aposição
	 */
	public static char caracNaPosicao(String texto, int posicao) {
		//Transforma o texto em vetor de caracteres
		char[] letras = texto.toCharArray();
		//Verificando se é uma posição invalida
		if(posicao < 0 || posicao >= letras.length) {
			throw new StringIndexOutOfBoundsException("Voce inseriu um indice invalido");
		}
		return letras[posicao];
	}
	/**
	 * 
	 * @param texto
	 * @param Subtexto
	 * @return
	 */
	public static int indiceDe(String texto, String subtexto) {
		char[] letrasTexto = texto.toCharArray();
		char[] letrasSubtexto = subtexto.toCharArray();
		int contOcorrencias = 0;
	
		//Percorrendo todas as letras da primeira palavra
		for(int i = 0; i < letrasSubtexto.length; i++) {
			for(int j = 0; j < letrasSubtexto.length; j++) {
				if(letrasTexto[i] == letrasSubtexto[j]) {
					System.out.println("Deu match! ");
					i++;//avança o i para ir para o proximo caractere
					contOcorrencias++;
				}else {
					contOcorrencias = 0;
					System.out.println("Nao deu match! ");
					break;//interrompe o laco pq nao deu match em todas letras
				}
				//verifica se encontrou todas as letras
				if(contOcorrencias == letrasSubtexto.length) {
					return (i - contOcorrencias);
				}
			}
		}
		//caso nunca entre no if do retorno retorne -1
		return -1;
	}
}
