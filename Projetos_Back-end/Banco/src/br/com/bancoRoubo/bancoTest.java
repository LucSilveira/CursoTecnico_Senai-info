package br.com.bancoRoubo;

public class BancoTest {
public static void main(String[] args) {
	
	Conta conta = new Conta(1, TipoConta.CORRENTE, 1000, new Cliente("Lucas", "551236486", "46923597811"));
	//conta.setNumero(1);
	//conta.setTipo("ContaCorrente: ");
	//conta.setSaldo(1000);
	Conta.saldoBanco = 1_000_000.00;
	conta.saca(100);
	System.out.println("Cofre do banco: R$" + Conta.saldoBanco);
	conta.exibeSaldo();
	conta.deposita(50);
	System.out.println("Cofre do banco: R$ " + Conta.saldoBanco);
	conta.exibeSaldo();
	System.out.println("Dados da conta: ");
	System.out.println("nº: " + conta.getNumero());
	System.out.println("Tipo: " + conta.getTipoConta().titulo);
	System.out.println("Saldo: " + conta.getSaldo());
	System.out.println("Titular: " + conta.getCliente().getNome());
	System.out.println("Cpf: " + conta.getCliente().getCpf());
	System.out.println("Rg: " + conta.getCliente().getRg());
	
}
}
