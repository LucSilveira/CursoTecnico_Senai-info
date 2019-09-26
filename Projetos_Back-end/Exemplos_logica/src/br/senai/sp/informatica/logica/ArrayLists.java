package br.senai.sp.informatica.logica;
import java.util.ArrayList;
public class ArrayLists {
	public static void main(String[] args) {
	//declara um Array
	ArrayList<String> cores = new ArrayList<String>();
	//adiciona items ao ArrayList
	cores.add("Branco");
	cores.add(0, "Vermelho");
	cores.add("Amarelo");
	cores.add("Azul");
	
	//exibe todos os itens do ArrayList
	System.out.println(cores.toString());
	//mostra quantos elementos tem no array
	System.out.println(cores.size());
	//seleciona um item do array
	System.out.println(cores.get(2));
	//remove um item do ArrayList
	cores.remove("Branco");
	System.out.println(cores.toString());
	//verifica se á a cores
	System.out.println(cores.contains("Azul"));
	System.out.println(cores.contains("Verde"));
	//verifica se nao tem a cor
	System.out.println(!cores.contains("Vermelho"));
	
	
	}
}