package br.senai.sp.informatica.algoritmo.strings;

public class MetodosStringTest {
public static void main(String[] args) {
	char letra = MetodosString.caracNaPosicao("Homem de ferro lixo!", 9);
	System.out.println("Caractere na posicao: " + letra);
	
	System.out.println("Indice: " + MetodosString.indiceDe("pao-de-mel",  "mel"));
}
}
