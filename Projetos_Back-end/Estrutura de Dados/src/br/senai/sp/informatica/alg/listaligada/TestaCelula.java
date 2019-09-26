package br.senai.sp.informatica.alg.listaligada;

public class TestaCelula {
	
	public static void main(String[] args) {
		
		Produto p1 = new Produto();
		Produto p2 = new Produto();
		Produto p3 = new Produto();
		
		p1.setNome("Arroz Namorados");
		p2.setNome("Biscoito Oreo");
		p3.setNome("Cebola da Feira");
		
		Celula c1 = new Celula(p1);
		Celula c2 = new Celula(p2);
		Celula c3 = new Celula(p3);
		
		//criando a lista de produtos
		ListaLigada listaDeProdutos = new ListaLigada();
		//listaDeProdutos.addNoComeco(p1);
		listaDeProdutos.addNoComeco(p2);
		//listaDeProdutos.addNoComeco(p3);
		//listaDeProdutos.addNoFim(p1);
		//listaDeProdutos.addNoFim(p2);
		listaDeProdutos.addNoFim(p3);
		
		//Associando as células com os valores
		//c3.setProximo(c2);
		//c2.setProximo(c1);
		
		System.out.println(listaDeProdutos.pegar(0).getNome());
		System.out.println(listaDeProdutos.pegar(2).getNome());
		System.out.println(listaDeProdutos.pegar(1).getNome());
		
	}
}