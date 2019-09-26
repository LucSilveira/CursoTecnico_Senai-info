package br.senai.sp.informatica.oop;
public class PessoaTestDrive {
	public static void main(String[] args) {
		Pessoa lucas = new Pessoa();
		lucas.idade = 16;
		lucas.nome = "Lucas";
		lucas.fazerNiver();
		
		Pessoa victor = new Pessoa();
		victor.idade = 15;
		victor.nome = "Victor";
		victor.fazerNiver();
		
		int dif = lucas.idade - victor.idade;
		
		System.out.println("A diferença de idade entre " + lucas.nome + " e " + victor.nome + " e de " + dif  +" anos");
	}
}
