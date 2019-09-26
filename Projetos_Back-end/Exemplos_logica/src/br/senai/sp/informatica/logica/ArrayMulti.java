package br.senai.sp.informatica.logica;
public class ArrayMulti{
	public static void main(String[] args){
		
	String[] vetor = {"Seiya", "Hyoga", "Shiryu", "Ikki"};
	//System.out.println(vetor[0]);
	//System.out.println(vetor.length);
	String[][] matriz = {      
		{"Seiya", "M", "Pegaso"}, //0
		{"Yoga", "M", "Cisne"},  //1 
		{"Shiryu", "M", "Dragao"}, //2
		{"Ikki", "M", "Fenix"} //3
	};
	//matriz[L][C]
	System.out.println("");
	System.out.println("\tNome: " + matriz[0] [0] + ",");
	System.out.println("\tSexo: " + matriz[0] [1] + ",");
	System.out.println("\tContelacao de " + matriz[0] [2] + "\n");
	///////////////////////////////////////////////////////
	System.out.println("\tNome: " + matriz[1] [0] + ",");
	System.out.println("\tSexo: " + matriz[1] [1] + ",");
	System.out.println("\tContelacao de " + matriz[1] [2] + "\n");
	///////////////////////////////////////////////////////
	System.out.println("\tNome: " + matriz[2] [0] + ",");
	System.out.println("\tSexo: " + matriz[2] [1] + ",");
	System.out.println("\tContelacao de " + matriz[2] [2] + "\n");
	///////////////////////////////////////////////////////
	System.out.println("\tNome: " + matriz[3] [0] + ",");
	System.out.println("\tSexo: " + matriz[3] [1] + ",");
	System.out.println("\tContelacao de " + matriz[3] [2] + "\n");
	//////////////////////////////////////////////////////////////
	//tamanho da matriz
	System.out.println(matriz.length);
	System.out.println(matriz[0].length);
	System.out.println(matriz[1].length);
	
	}
}