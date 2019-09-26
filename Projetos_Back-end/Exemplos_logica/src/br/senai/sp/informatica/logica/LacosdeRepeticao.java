package br.senai.sp.informatica.logica;
public class LacosdeRepeticao {
	public static void main(String[] args){
	
	//for 
	for(int i = 0; i < 3; i++){
		System.out.println(i);
	}
	System.out.println("");
	for(int i = 10; i >= 0; i--){
		System.out.println(i);
	}
	System.out.println("");
	for(int i = 0; i <= 20; i++){
		if(i % 2 == 0){
			System.out.println(i);
		}
	}
	for (int castigo = 0; castigo <= 1000; castigo++){
		System.out.println("Não devo bater no victor");
	}
	for(int x = 0; x <= 5; x++){
		System.out.println(x);
	}
	// imprimi o quadrado na trla
	//tamanho do cubo
	int tamanho = 5;
	//imprime as linhas           
	for (int i = 0; i <= tamanho; i++){
		//escreve * 5vz
		for(int y = 0; y <= tamanho; y++){
			System.out.println("*");
		}
		//pula uma linha
		System.out.println();
	}
	
	}
}