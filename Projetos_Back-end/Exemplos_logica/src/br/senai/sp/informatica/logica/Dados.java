package br.senai.sp.informatica.logica;
/*
Jogo de dados
Sorteia um número do dado e o objetivo do jogo 
é fazer o úsuario acertar o número sorteado

@Author Lucas Silveira Portal
MT2
*/
import java.util.Random;
import javax.swing.JOptionPane;
public class Dados {
	public static void main(String[] args) {
	
		int[] dado = { 1, 2, 3, 4, 5, 6};
		//variavel de entrada
		Random random = new Random();
		int indiceDado = random.nextInt(dado.length);
		int resultado = dado[indiceDado];
		//variavel de entrada
		//declarou certo (JOp... retorna so em string
		String palpite = JOptionPane.showInputDialog(null, "Qual o seu palpite ?", "Palpite", JOptionPane.QUESTION_MESSAGE);
		//parseInt so retorna em int
		int chute = Integer.parseInt(palpite);
		//método de comparação
		if(chute > 6 || chute < 1 ){
			JOptionPane.showMessageDialog(null, "Numero nao encontrado, escolha apenas numeros presentes em um dado", "ERRO", JOptionPane.WARNING_MESSAGE);
		} else {
			if(chute == resultado){
				JOptionPane.showMessageDialog(null, "Voce acertou, meus parabens\n \nSeu Palpite foi o numero:  " + palpite + "\nO numero sorteado foi:  " + resultado, "Resultado", JOptionPane.INFORMATION_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(null, "Hum, que pena nao foi dessa vez, mais nao desista\n \nSeu Palpite foi o numero:   " + palpite + "\nO numero sorteado foi:  " + resultado, "Resultado", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}