package br.senai.sp.informatica.logica;
import java.util.Arrays;
public class ArraySimples {
public static void main(String[] args){
//cria um array de string
String[] paises = {"Brasil", "Russia", "India", "China"};
//recupera o primeiro pais
System.out.println(paises[0]);
//recupera o quarto pais
System.out.println(paises[3]);

//intens no array
System.out.println(paises.length); 
//exibe todos os elementos de um array
System.out.println(Arrays.toString(paises));
//pesquisa um elementos
int indice = Arrays.binarySearch(paises, "Russia");
System.out.println("indice: " + indice);
//exibe o nome do elementos
System.out.println(paises[indice]);

//ordena o Arrey parametros array, inicio, fim
Arrays.sort(paises, 0, paises.length);
System.out.println(Arrays.toString(paises));

Double[] valores = {12.35, 3456.3456, 19.12};
//acessa um elemento do array
System.out.println(valores[0]);
/*
declara um array de int
para 5 elementos
*/
int[] impares = new int[5];
//inicializando
impares[0] = 1;
impares[1] = 3;
impares[2] = 5;
impares[3] = 7;
impares[4] = 9;

System.out.println(impares[3]);


}
} 