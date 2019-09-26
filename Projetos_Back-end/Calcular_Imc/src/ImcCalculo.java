
/*
 * Este programa calcula o Imc do paciente e o classifica
 * e para isso o usuario devera informa o seu peso(kg)
 * e sua altura (M)
 * @Atuthor Lucas Silveira Portal
 */
import javax.swing.JOptionPane;

public class ImcCalculo {
	public static void main(String[] args) {
		// variavel do retorno
		int recalcular;
		// retorno do programa
		do {
			// Pedindo para o usuario informar o seu peso
			String pesoKg = JOptionPane.showInputDialog(null, "Informe o seu peso(Kg): ", "Informe o Peso",
					JOptionPane.QUESTION_MESSAGE);
			double peso = Double.parseDouble(pesoKg);
			// Pedindo parao usuario informar a sua altura
			String alturaM = JOptionPane.showInputDialog(null, "Informe a sua altura(M): ", "Informe sua Altura",
					JOptionPane.QUESTION_MESSAGE).replace(",", ".");
			double altura = Double.parseDouble(alturaM);
			// calculando o imc
			double imc = peso / Math.pow(altura, 2);
			// Classificando o Imc do usuario
			// Caso o usuario digite peso/altura igual a zero
			if (imc <= 0 || altura <= 0) {
				JOptionPane.showMessageDialog(null, "Inpossivel identificar valores!", "Erro",
						JOptionPane.ERROR_MESSAGE);
				// Abaixo do peso
			} else if (imc < 18.5) {
				JOptionPane.showMessageDialog(null,
						"Diagnostico do paciente!\nClassificacao:  Abaixo do peso normal\nResultado do Imc: " + imc,
						"Diagnostico", JOptionPane.INFORMATION_MESSAGE);
				// Peso normal
			} else if (imc >= 18.5 && imc <= 24.9) {
				JOptionPane.showMessageDialog(null,
						"Diagnostico do paciente!\nClassificacao:  Peso normal\nResultado do Imc: " + imc,
						"Diagnostico", JOptionPane.INFORMATION_MESSAGE);
				// Excesso de peso
			} else if (imc >= 25 && imc <= 29.9) {
				JOptionPane.showMessageDialog(null,
						"Diagnostico do paciente!\nClassificacao:  Excesso de peso\nResultado do Imc: " + imc,
						"Diagnostico", JOptionPane.INFORMATION_MESSAGE);
				// Obesidade grau 1
			} else if (imc >= 30 && imc <= 34.9) {
				JOptionPane.showMessageDialog(null,
						"Diagnostico do paciente!\nClassificacao:  Obesidade classe |\nResultado do Imc: " + imc,
						"Diagnostico", JOptionPane.INFORMATION_MESSAGE);
				// Obesidade grau 2
			} else if (imc >= 35 && imc <= 39.9) {
				JOptionPane.showMessageDialog(null,
						"Diagnostico do paciente!\nClassificacao:  Obesidade classe ||\nResultado do Imc: " + imc,
						"Diagnostico", JOptionPane.INFORMATION_MESSAGE);
				// Obesidade grau 3
			} else {
				JOptionPane.showMessageDialog(null,
						"Diagnostico do paciente!\nClassificacao:  Obesidade classe |||\nResultado do Imc: " + imc,
						"Diagnostico", JOptionPane.INFORMATION_MESSAGE);
			}
			recalcular = JOptionPane.showConfirmDialog(null, "Deseja Calcular novamente?", "Novo calculo",
					JOptionPane.YES_NO_OPTION);
		} while (recalcular == 0);
	}
}