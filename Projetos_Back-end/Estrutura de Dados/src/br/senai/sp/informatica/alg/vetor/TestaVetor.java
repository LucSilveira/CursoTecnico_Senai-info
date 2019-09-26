package br.senai.sp.informatica.alg.vetor;

public class TestaVetor {
	public static void main(String[] args) {

		Cliente c1 = new Cliente("Victor");
		Cliente c2 = new Cliente("Lucas");
		Cliente c3 = new Cliente("Iris");
		Cliente c4 = new Cliente("Samantha");
		Animal a = new Animal();
		
		Vetor vetorDeClientes = new Vetor();
		vetorDeClientes.adicionar(c1, 0);
		vetorDeClientes.adicionar(c2, 1);
		vetorDeClientes.adicionar(c3, 2);
		vetorDeClientes.adicionar(c4, 3);
		vetorDeClientes.adiconar(a);
		vetorDeClientes.remover(2);
		
		System.out.println(vetorDeClientes.pegar(0));
		System.out.println(vetorDeClientes.pegar(1));
		System.out.println(vetorDeClientes.pegar(2));
		
		System.out.println(vetorDeClientes);
		
		Cliente cliente = (Cliente) vetorDeClientes.pegar(0);
		System.out.println(cliente);
	}
}