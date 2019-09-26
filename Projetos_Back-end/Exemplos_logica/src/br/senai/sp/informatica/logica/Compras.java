package br.senai.sp.informatica.logica;
import java.util.ArrayList;
import java.util.Scanner;
public class Compras {
 public static void main(String[] args){
	ArrayList<String> produtos = new ArrayList<String>();
	Scanner teclado = new Scanner(System.in);
	String msg = "Faca a sua lista de compra : caso não queira fazer digite FIM ";
	System.out.println(msg);
	
	String produto;
	//enquanto fim n for = ao q o usuer digitar 
	while(!"FIM".equals(produto = teclado.nextLine())){
		
		//add o produto na lista
	produtos.add(produto);
	}
	System.out.println(produto.toString());
	}
}//string = classe wrapper 