package br.senai.sp.informatica.logica;
import java.util.Random;
import javax.swing.JOptionPane;
public class CardGame {
	public static void main(String[] args) {
	
	String[] faces = {
	"A", "2", "3", "4", "5",
	"6", "7", "Q", "K", "J",
	};
	String[] naipes = {
	"Espadas", "Zap", "Pica_Fumo", "Copas"
	};
	//random
	Random random = new Random();
	int indiceFace = random.nextInt(faces.length);
	int indiceNaipe = random.nextInt(naipes.length);
	//sorteia uma faces
	String face = faces[indiceFace];
	//sorteia uma naipe
	String naipe = naipes[indiceNaipe];
	//seleciona um indice do vetor facessssss
	String carta = face + " de " + naipe;
	//resultado
	System.out.println("\n\tSua carta e: " + carta);
	//JOptionPane.showMessageDialog(null, carta);
	
	
	}
}