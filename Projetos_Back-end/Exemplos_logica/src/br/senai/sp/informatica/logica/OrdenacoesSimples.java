package br.senai.sp.informatica.logica;
import java.util.Random;
public class OrdenacoesSimples {
	public static void main(String[] args){
		// int[] vetor = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0, -1, -2};
		bubbleSort(geraVetor(50000));
		insertSort(geraVetor(50000));
		selectSort(geraVetor(50000));
	}
	
	public static void bomdia(){
		System.out.println("Que fase !");
	}
	
	public static int[] geraVetor(int qntd){
		//Novo vetor com uma certa quantidade
		int[] novoVetor = new int[qntd];
		//aleatorio RANDOM
		Random aleatorio = new Random();
		//aplica um valor nos elementos do vetor
		for(int i = 0; i < qntd; i++){
			novoVetor[i] = aleatorio.nextInt(50000);
		}
		return novoVetor;
	}
	public static void imprimirVetor(int[] vetor){
		for (int i = 0; i < vetor.length; i++){
			System.out.println(vetor[i]);
		}
	}
	
	public static void bubbleSort(int[] vetor){
		System.out.println("____Bubble Sort: Loading___");
		long interacoes = 0; // quantos lacos foram usados
		long trocas = 0;  // quantas trocas ocorreram
		long tempoInicio = System.currentTimeMillis(); // quanto tempo demorou
		for(int i = 0; i < vetor.length - 1; i++){
			interacoes++;
			for(int j = 0; j < vetor.length - i - 1; j++){
				interacoes++;
				//Comparando se atual é maior que o proximo
				if(vetor[j] > vetor[j+1]){
					trocas++;
					//trocando
					int aux = vetor[j];
					vetor[j] = vetor[j+1];
					vetor[j+1] = aux;
					
				}
			}
		}
		
		for(int i = 0; i < vetor.length; i++){
			System.out.println(vetor[i]);
		}
		
		long tempoFim = System.currentTimeMillis(); // quanto tempo demorou
		System.out.println("___Relatorio:___");
		System.out.println("Quantos elementos: " + vetor.length);
		System.out.println("Interacoes: " + interacoes);
		System.out.println("Trocas: " + trocas);
		System.out.println("Tempo usado: " + (tempoFim - tempoInicio));
		System.out.println("____Bubble Sort: Ending__");
		tempo1 = tempoFim - tempoInicio;
	}
		
	public static void insertSort(int[] vetor){
		long interacoes = 0; // quantos lacos foram usados
		long trocas = 0;  // quantas trocas ocorreram
		long tempoInicio = System.currentTimeMillis(); // quanto tempo demorou
		for(int i = 1; i < vetor.length - 1; i++){
			interacoes++;
			for(int j = i; j < vetor.length - i - 1; j++){
				while(vetor[j-1] > vetor[j]){
					interacoes++;
					//realiza a troca
					trocas++;
					int aux = vetor[j];
					vetor[j] = vetor[j-1];
					vetor[j-1] = aux;
					//o j vai para o anterior
					vetor[j] = vetor[j - 1];
					//se o j for o 1º significa que ele não tem anterior e buga
					if(j == 0){
						break;
					}
				}
			}
		}
		long tempoFim = System.currentTimeMillis(); // quanto tempo demorou
		System.out.println("");
		System.out.println("___Relatorio Insertion:___");
		System.out.println("Quantos elementos: " + vetor.length);
		System.out.println("Interacoes: " + interacoes);
		System.out.println("Trocas: " + trocas);
		System.out.println("Tempo usado: " + (tempoFim - tempoInicio));
		System.out.println("____Bubble Sort: Ending__");
		tempo2 = tempoFim - tempoInicio;
	}
	public static void selectSort(int[] vetor){
		long interacoes = 0; // quantos lacos foram usados
		long trocas = 0;  // quantas trocas ocorreram
		long tempoInicio = System.currentTimeMillis(); // quanto tempo demorou
		int min = 0;
		for(int j = 1; j < vetor.length - 1; j++){
			vetor[min] = vetor[j];
			interacoes++;
			for(int i = j + 1; i < vetor.length - 1; i++){
				interacoes++;
				if(vetor[i] < vetor[min]){
					vetor[min] = vetor[i];
				}
			}
			if(vetor[min] != vetor[j]){
				trocas++;
				int aux = vetor[j];
				vetor[j] = vetor[min];
				vetor[min] = aux;
			}
		}
		long tempoFim = System.currentTimeMillis(); // quanto tempo demorou
		System.out.println("");
		System.out.println("___Relatorio Selection:___");
		System.out.println("Quantos elementos: " + vetor.length);
		System.out.println("Interacoes: " + interacoes);
		System.out.println("Trocas: " + trocas);
		System.out.println("Tempo usado: " + (tempoFim - tempoInicio));
		System.out.println("____Bubble Sort: Ending__");
		tempo3 = tempoFim - tempoIncio;
	}
	for
	}

