package br.senai.sp.informatica.algoritmo.strings;

public class MetodoStringEmJava {
	public static void main(String[] args) {
		String senai = "Escola Senai de informática";
		String nomePersongLol = "		darkAnge1667";	
		
		//charAt(int) - Retorna um determinado caractere de uma String
		char letra = senai.charAt(8);
		System.out.println("charAt: " + letra);
		
		//cacontains(String) - verifica se um determinadao texto existe no texto
		System.out.println("contains: "     +    senai.contains("informática"));
		
		//indexOf(String) - Retorna em qual indice se encontra determinada ocorrencia
		System.out.println("indexOf: "     + senai.indexOf("informática"));
		
		//raplace(String,  Sring) - Troca um sub texto por outro
		System.out.println("replace: " + senai.replace("informática", "mecanica"));
		
		//Mudando o estado da String
		senai = senai.replace("informática", "mecatronica");
		System.out.println(senai);
		
		//subsstring(int) - Pega um subtexto  a partir de um determinado indice
		System.out.println("substring(int): " + senai.substring(16));
		
		//substring(int, int) - pega um subtexto de um ponto inicial ate o final
		System.out.println("substring (int, int): " + senai.substring(7,  12));
		
		//toLowerCase() - retorna uma nova string com caracteres em caixa baixa
		System.out.println("toLowerCase: " + nomePersongLol.toLowerCase());
		
		//toUpperCase() - Retorna uma nova string com caracteres em caixa alta
		System.out.println("toUpperCase: " + nomePersongLol.toUpperCase());
		
		//trim() - Elimina os espacos em branco de uma string
		nomePersongLol = nomePersongLol.toUpperCase();
		System.out.println("trim: " + nomePersongLol);
		
	}
}
