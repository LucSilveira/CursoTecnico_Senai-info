public class Fibonacci {

	public static void main(String[] args) {

		System.out.println(fibonacci(6));

	}

	public static long fibonacci(int posicao) {
		if (posicao <= 1) {
			return 1;
		} else {
			return fibonacci(posicao - 1) + fibonacci(posicao - 2);
		}
	}

}
