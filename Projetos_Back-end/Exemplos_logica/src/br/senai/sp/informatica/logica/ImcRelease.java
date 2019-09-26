package br.senai.sp.informatica.logica;
/*
programa que calcula o imc de um paciente e exibe um diagnostico informando
se o paciente está fora ou dentro do peso ideal.
O peso ideal é caracterizado por um imc que esteja entre 20 e 25.
@Author Lucas Silveira 
*/
import javax.swing.JOptionPane;
public class ImcRelease {
public static void main(String[] args){
//peso
String peso = JOptionPane.showInputDialog(null, "Qual e o seu peso ? ", "Peso", JOptionPane.QUESTION_MESSAGE);
//converte string para double
double pesoKg = Double.parseDouble(peso);

	//altura
	String altura = JOptionPane.showInputDialog("Qual a sua altura ?");
	//converter string para double
	double alturaMetros = Double.parseDouble(altura); 

	//calculo
	double imc = pesoKg / Math.pow(alturaMetros, 2);

	//faz o dignostico
	String diagnostico = imc >= 20 && imc <= 25 ? "Peso ideal" : "Fora do peso ideal";
	//exibe o imc
	String msg = "Imc: " + imc + "\n" + diagnostico;
	JOptionPane.showMessageDialog(null, msg, "Diagnostico", JOptionPane.INFORMATION_MESSAGE);

}
}