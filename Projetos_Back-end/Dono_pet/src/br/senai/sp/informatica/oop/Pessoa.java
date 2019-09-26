package br.senai.sp.informatica.oop;
/**
 * @author Lucas Silveira
 */
public class Pessoa {
	int idade;
	String nome;
	void fazerNiver() {
		System.out.println("A(O) " + this.nome + " tem " + this.idade + " anos");
		this.idade++;
		System.out.println("proximo ano ela(ele) fara " + this.idade++);
	}
}
