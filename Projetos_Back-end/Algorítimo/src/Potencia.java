
public class Potencia {

	public static void main(String[] args) {
		
		double base = 2;
		double expoente = -1;
		System.out.println(potencia(base, expoente));
		
	}
	
	public static double potencia(double base, double expoente){
		
		if(expoente == 0){
			return 1;
			
		}else if (expoente > 0) {
			return base * potencia (base, expoente - 1);
			
		}else{ 
			return 1 / base * potencia (base, expoente + 1);
		}
	}

}
