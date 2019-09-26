package br.senai.sp.informatica.logica;
import java.util.ArrayList;
public class FluxoForeach{
	public static void main(String[] args){
	
	int[] pares = {2, 4, 6, 8};
	//foreach
	for(int par : pares){
		System.out.println(par);
	}
	String[] nome = {"Lucas", "Victor", "Samantha", "Iris"};
	for(String n : nome) {
		System.out.println(n);
	}
	ArrayList<Integer> lista = new ArrayList<Integer>();
	//add 10 elementos
	for(int i = 0; i <= 10; i++){
		lista.add(i);
	}
	//imprimi o arrqays
	for(int valor : lista){
		System.out.println(valor);
	}
	
	}
}