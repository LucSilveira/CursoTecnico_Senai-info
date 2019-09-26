package br.senai.sp.informatica.logica;
public class Wrapper {

	public static void main(String[] args) {
	
	// cria uma variavel do tipo Double
	Double preco = new Double("12.45");
	System.out.println(preco);
	
	//converter Double para double
	double valor = preco.doubleValue();
	System.out.println(valor);
	
	//converter Double para int
	int valorInt = preco.intValue();
	System.out.println(valorInt);
	
	//converte Double para byte
	byte valorByte = preco.byteValue();
	System.out.println(valorByte);
	
	Boolean casado = new Boolean("true");
	
	// conversão estatica 
	String valorTxt = "123.45";
	double myDouble = Double.parseDouble(valorTxt);
	System.out.println(valorTxt);
	
	// Converte de String para int
	String intTxt = "123"; 
	int myInt = Integer.parseInt(intTxt);
	
	//converte de String para float
	String floatTxt = "3.14F";
	float myFloat = Float.parseFloat(floatTxt);
	
	int binario = Integer.valueOf("101011");
	System.out.println(binario);
	
	int decimal = Integer.valueOf("101011", 2);
	System.out.println(decimal);
	

	}
}