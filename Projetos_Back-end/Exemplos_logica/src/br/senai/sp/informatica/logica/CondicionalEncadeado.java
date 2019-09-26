package br.senai.sp.informatica.logica;
public class CondicionalEncadeado {
	public static void main(String[] args) {
    /*
	ate 11 kid
	entre 12 e 18 jovem
	entre 19 e 50 adulto
	acima de 50 coroa
	*/	
	int idade = 15;
	
	if(idade <= 11) {
		System.out.println("Kid");
	} else if (idade >= 12 && idade <= 18) {
		System.out.println("Teen");
	} else if (idade >= 19 && idade <= 50){
	} else {
		System.out.println("Coroa");
	}	
	
	int nota = 6;
	if(nota >= 7) {
		System.out.println("Congradulation");
	} else {
		System.out.println("Crl, eu sou um merda");
		if(nota >= 6) {
			System.out.println("Rlx, faz recu");
		}
	}
	
	
	}
}