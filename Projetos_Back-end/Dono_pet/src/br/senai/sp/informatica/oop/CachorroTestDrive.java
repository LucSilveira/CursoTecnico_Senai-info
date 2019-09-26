package br.senai.sp.informatica.oop;
/**
 * @author Tecnico_Manha
 */
public class CachorroTestDrive {
	public static void main(String[] args) {
		//cria um novo cachorro
		//cria uma distancia de cachorro
		Cachorro pitbull = new Cachorro();
		pitbull.raca = "Pitbull";
		pitbull.tamanho = 45;
		pitbull.latir();
		
		Cachorro chiuauau = new Cachorro();
		chiuauau.raca = "Chiuauau";
		chiuauau.tamanho = 5;
		chiuauau.latir();
	
	}
}
