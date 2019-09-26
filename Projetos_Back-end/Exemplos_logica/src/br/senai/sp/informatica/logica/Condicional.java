package br.senai.sp.informatica.logica;
public class Condicional {
	public static void main(String[] args) {
		
	int idade = 10;
	if(idade < 11){
		System.out.println("Crianca");
	}	
	boolean passou = true;
	if(passou = true) {
		System.out.println("Contratado");
	}
	//verifica se não passou
	if(!passou){
		System.out.println("Nao Contratado");
	}
	//verifica se o numero é par
	int numero = 10;
	//calcula o resto da divisao de numero par por 2
	int resto = numero % 2;
	if(resto == 0){
		System.out.println("Par");
	} else {
		System.out.println("Impar");
	}
	
	}
}