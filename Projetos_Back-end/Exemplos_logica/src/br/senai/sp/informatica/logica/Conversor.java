package br.senai.sp.informatica.logica;

//  Este programa converte temperaturas
//  e as temperaturas são determinadas em graus ºC, ºK e ºF
//  @Author Lucas Silveira Portal
import javax.swing.JOptionPane;

public class Conversor {
	public static void main(String[] args) {
		int rec;
		// retorno do programa
		do {
			// menu de conversao
			// O usuario seleciona o tipo de unidade que ele deseja conveter
			String Op = JOptionPane.showInputDialog(null,
					" Conversor de Temperaturas \n\tSenaiSp(C)\n\n[C] Converter de ºC para ºF e ºK\n[F] Converter de ºF para ºC e ºK\n[K] Converter ºK para ºF e ºC",
					"Menu de Conversao de unidades", JOptionPane.INFORMATION_MESSAGE);
			// int opcao = Integer.parseInt(Op);,

			// corpo do trabalho
			switch (Op) {
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
			rec = JOptionPane.showConfirmDialog(null, "Deseja Calcular novamente?", "Novo calculo",
					JOptionPane.YES_NO_OPTION);
		} while (rec == 0);
	}

	// funcao de converter Celsius
	public static void ConverteC() {
		String res = JOptionPane.showInputDialog(null, "Informe um valor em Celsius (ºC): ", "Informe",
				JOptionPane.QUESTION_MESSAGE);
		double Celsius = Double.parseDouble(res);
		double Fahrenheit = (Celsius * 1.8) + 32;
		double Kelvin = Celsius + 273.15;
		JOptionPane.showMessageDialog(null,
				"Valor fornecido em Celsius (ºC): " + Celsius + "\n\nResultado em Fahrenheit (ºF):\n Fahrenheit = ("
						+ Celsius + " * 1.8) + 32\nFehrenehit: " + Fahrenheit + "º\n"
						+ "\nResultado em Kelvin (ºK):\nKelvin = " + Celsius + " + 273.15\nKelvin: " + Kelvin + "º",
				"Conversao", JOptionPane.INFORMATION_MESSAGE);
	}

	// funcao de converter Fahrenheint
	public static void ConverteF() {
		String res = JOptionPane.showInputDialog(null, "Informe um valor em Fahrenheit (ºF): ", "Informe",
				JOptionPane.QUESTION_MESSAGE);
		double Fahrenheit = Double.parseDouble(res);
		double Celsius = (Fahrenheit - 32) / 1.8;
		double Kelvin = (Fahrenheit - 32) / 1.8 + 273.15;
		JOptionPane.showMessageDialog(null,
				"Valor fornecido em Fahrenheint (ºF): " + Fahrenheit + "\n\nResultado em Celsius (ºC):\nCelsius = ("
						+ Fahrenheit + " - 32) / 1.8\nCelsius: " + Celsius + "º\n"
						+ "\nResultado em Kelvin (ºK):\nKelvin = (" + Fahrenheit + " - 32) / 1.8 + 273.15\nKelvin: "
						+ Kelvin + "º",
				"Conversao", JOptionPane.INFORMATION_MESSAGE);
	}

	// funcao de converter Kelvin
	public static void ConverteK() {
		String res = JOptionPane.showInputDialog(null, "Informe um valor em Kelvin (ºK): ", "Informe",
				JOptionPane.QUESTION_MESSAGE);
		double Kelvin = Double.parseDouble(res);
		double Fahrenheit = 1.8 * (Kelvin - 273.15) + 32;
		double Celsius = Kelvin - 273.15;
		JOptionPane.showMessageDialog(null,
				"Valor fornecido em Kelvin (ºK): " + Kelvin + "\n\nResultado em Fahrenheit (ºF):\nFahrenheit = 1.8 * ("
						+ Kelvin + " - 273.15) + 32\nFahrenheit: " + Fahrenheit + "º\n"
						+ "\nResultado em Celsius (ºC):\nCelsius =" + Kelvin + " - 273.15\nCelsius: " + Celsius + "º",
				"Conversao", JOptionPane.INFORMATION_MESSAGE);

	}
}