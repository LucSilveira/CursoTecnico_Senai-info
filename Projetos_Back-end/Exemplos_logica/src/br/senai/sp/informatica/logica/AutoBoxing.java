package br.senai.sp.informatica.logica;
public class AutoBoxing {
	public static void main(String[] args){
	//boxing - empacotador
	Integer x = new Integer(555);
	x++;
	System.out.println(x);
	///////////////////////////////////////////
	//ate a versão 5
	//recuperar o valor de x
	//unboxing - desempacotador
	//int a = x.intValue();
	//x = 555 + 1 incrementar
	//a++;
	//re-empacotador
	//x = new Integer(a);
	///////////////////////////////////////////
	}
}