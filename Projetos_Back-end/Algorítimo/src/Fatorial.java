
public class Fatorial {

	public static void main(String[] args) {

	}
	
	public static int fatorial (int n){
		if (n <= 0){
			return 1;
		}else{
			int resultado = n * fatorial(n - 1);
			return resultado;
		}
	}

}
