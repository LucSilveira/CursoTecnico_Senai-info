package br.senai.sp.informatica.logica;
public class Variaveis {
	public static void main(String[] args) {
			// declaração de variáveis
			String nome  = "Lucas";
			int idade = 16;
			char sexo = 'M'; //unicode
			boolean namorando = false;
			double sorvete = 9.99;
			// tipos inteiros ↓↓↓
			// byte b = 127; // cem
			// short s = 32767;// 32 mil
			// int  i = 2_000_000_000; // 2 bilhoes
			// long 1 = 9_000_000_000_000_000_000; // 9 quintilhoes
			
			// ponto flutuante (casas decimais)
			// double d = 1.7976931348623157e+308D; //IEEE 754
			//float f = 123.85F;
		System.out.println("Nome: "+ nome);
		System.out.println("Idade: "+ idade);
		System.out.println("Sexo: "+ sexo);
		System.out.println("Estou namorando: "+ namorando);
		System.out.println("Quanto custa o sorvete: R$"+sorvete);
		
		// 1 Byte = 8 bits
		//byte bb = 0b01010101
		
		// 2 Byte = 16 bits
		// short ss = 0b0101010101010101
		
		// 3 Byte = 32 bits
		// int ii = 0b01010101010101010101010101010101
		
		//int meuInt = 32;
		//short meuShort = 80;
		//meuInt = meuShort;
		//System.out.println(meuInt);
		 
		// int meuInt2 = 32;
		//long meuLong = 2_000_000;
		//meuInt2 = (int) meuLong;
		//System.out.println(meuInt2);
	}
}