
/*
Este programa converte temperaturas
e as temperaturas são determinadas em graus ºC, ºK e ºF
@Author Lucas Silveira Portal
*/
import javax.swing.JOptionPane;

public class Conversor {
	public static void main(String[] args) {
		int recalcular;
		// retorno do programa
		do {
			// menu de conversao
			// O usuario seleciona o tipo de unidade que ele deseja conveter
			String opcao = JOptionPane.showInputDialog(null,
					" Conversor de Temperaturas \n\tSenaiSp(C)\n\n[C] Converter de ºC para ºF e ºK\n[F] Converter de ºF para ºC e ºK\n[K] Converter ºK para ºF e ºC",
					"Menu de Conversao de unidades", JOptionPane.INFORMATION_MESSAGE);
			// int opcao = Integer.parseInt(Op);,

			// corpo do trabalho
			switch (opcao) {
			case "c":
			case "C":
			case "Celsius":
			case "celsius":
				// chama a funcao de Celsius
				ConverteC();
				break;
			case "f":
			case "F":
			case "Fahrenheit":
			case "fahrenheit":
				// chama a funcao de Fahrenheit
				ConverteF();
				break;
			case "k":
			case "K":
			case "Kelvin":
			case "kelvin":
				// chama a funcao de Kelvin
				ConverteK();
				break;
			default:
				// mensagem de erro do programa
				JOptionPane.showMessageDialog(null, "Opcao nao encontrada", "Erro", JOptionPane.ERROR_MESSAGE);
				break;
			}
			recalcular = JOptionPane.showConfirmDialog(null, "Deseja Calcular novamente?", "Novo calculo",
					JOptionPane.YES_NO_OPTION);
		} while (recalcular == 0);
	}

	// funcao de converter Celsius
	public static void ConverteC() {
		String resposta = JOptionPane.showInputDialog(null, "Informe um valor em Celsius (ºC): ", "Informe",
				JOptionPane.QUESTION_MESSAGE);
		double celsius = Double.parseDouble(resposta);
		double fahrenheit = (celsius * 1.8) + 32;
		double kelvin = celsius + 273.15;
		JOptionPane.showMessageDialog(null,
				"Valor fornecido em Celsius (ºC): " + celsius + "\n\nResultado em Fahrenheit (ºF):\nFahrenheit = ("
						+ celsius + " * 1.8) + 32\nFehrenehit: " + fahrenheit + "º\n"
						+ "\nResultado em Kelvin (ºK):\nKelvin = " + celsius + " + 273.15\nKelvin: " + kelvin + "º",
				"Conversao", JOptionPane.INFORMATION_MESSAGE);
	}

	// funcao de converter Fahrenheint
	public static void ConverteF() {
		String resposta = JOptionPane.showInputDialog(null, "Informe um valor em Fahrenheit (ºF): ", "Informe",
				JOptionPane.QUESTION_MESSAGE);
		double fahrenheit = Double.parseDouble(resposta);
		double celsius = (fahrenheit - 32) / 1.8;
		double kelvin = (fahrenheit - 32) / 1.8 + 273.15;
		JOptionPane.showMessageDialog(null,
				"Valor fornecido em Fahrenheint (ºF): " + fahrenheit + "\n\nResultado em Celsius (ºC):\nCelsius = ("
						+ fahrenheit + " - 32) / 1.8\nCelsius: " + celsius + "º\n"
						+ "\nResultado em Kelvin (ºK):\nKelvin = (" + fahrenheit + " - 32) / 1.8 + 273.15\nKelvin: "
						+ kelvin + "º",
				"Conversao", JOptionPane.INFORMATION_MESSAGE);
	}

	// funcao de converter Kelvin
	public static void ConverteK() {
		String resposta = JOptionPane.showInputDialog(null, "Informe um valor em Kelvin (ºK): ", "Informe",
				JOptionPane.QUESTION_MESSAGE);
		double kelvin = Double.parseDouble(resposta);
		double fahrenheit = 1.8 * (kelvin - 273.15) + 32;
		double celsius = kelvin - 273.15;
		JOptionPane.showMessageDialog(null,
				"Valor fornecido em Kelvin (ºK): " + kelvin + "\n\nResultado em Fahrenheit (ºF):\nFahrenheit = 1.8 * ("
						+ kelvin + " - 273.15) + 32\nFahrenheit: " + fahrenheit + "º\n"
						+ "\nResultado em Celsius (ºC):\nCelsius =" + kelvin + " - 273.15\nCelsius: " + celsius + "º",
				"Conversao", JOptionPane.INFORMATION_MESSAGE);
	}
}
