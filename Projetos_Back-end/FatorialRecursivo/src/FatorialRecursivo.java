import java.util.Scanner;
public class FatorialRecursivo {
	public static void main(String[] args) {
		
	//receba do usuario um numero n(>= 0) e mostre pra ele seu fatorial
		Scanner teclado = new Scanner(System.in);
		
		System.out.println("Escolha um número: ");
		
//		String num = teclado.nextLine();
//		int nume = Integer.parseInt(num);
//		int fat = 1;
//	
//		while(nume > 0) {
//			fat *= nume;
//			nume--;
//			System.out.println(nume);
//			}
			System.out.println("Fatorial " + fatorial(5));
		}
	public static int fatorial(int num) {
		//Se o num for 0 retrona 1
		if(num == 0) {
			return 1;
		}else {
			return num * fatorial(num - 1);
		}
	}
}