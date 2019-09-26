package br.senai.sp.informatica.logica;
/*
programa que calcula o IMC e escreve na tela se o usuario 
está dentro ou não do peso idea
@Author Lucas Silveira
*/
public class ImcPrototipo {
public static void main(String[] args){
//calculo do imc
System.out.println(" Imc: peso / altura^2");
//peso
double pesoKg = 70;
//altura
double alturaMetros = 1.55;
//imc
double imc = pesoKg / Math.pow(alturaMetros, 2);
System.out.println("Imc: " + imc); 

//classificação
//peso ideal 20-25
String msg = imc >= 20 && imc <=25 ? "Peso ideal" : "Fora do peso ideal";
System.out.println("Diagnostico: " + msg);
}
}