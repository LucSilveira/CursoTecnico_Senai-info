package br.senai.sp.informatica.logica;
import java.util.Scanner;
public class CalculoCirculo {
public static void main(String[] args){
    //teclado
	Scanner teclado = new Scanner(System.in);
	System.out.println("Qual o valor do raio ?");
	//leia raio, aguarde digitarem um numero 
	//dotipo double e guarda esse valor em raio
	double raio = teclado.nextDouble();
//double raio = 10;
//diametro 
//2r
double diametro = 2 * raio;
System.out.println("Diametro: " + diametro);
//circuferencia
final double PI = Math.PI;
System.out.println("PI: " + PI);
double circuferencia = 2 * Math.PI * raio;
System.out.println("Circuferencia: " + circuferencia);
//área 
//pIr2
double area = Math.PI * Math.pow(raio, 2);
System.out.println("Area: " + area);
}
}