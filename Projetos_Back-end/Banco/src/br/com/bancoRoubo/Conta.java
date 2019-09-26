package br.com.bancoRoubo;

public class Conta {
	public static double saldoBanco;
	private int numero;
	private TipoConta tipoConta;
	private double saldo;
	private Cliente cliente;
	
	public Conta(int numero, TipoConta tipoConta, double saldo, Cliente cliente) {
		super();
		this.numero = numero;
		this.tipoConta = tipoConta;
		this.saldo = saldo;
		this.cliente = cliente;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public TipoConta getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(TipoConta tipoConta) {
		this.tipoConta = tipoConta;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void exibeSaldo() {
		System.out.println("Saldo: " + this.saldo);
	}

	public void saca(double valor) {
		saldo -= valor;
	}

	public void tranfPara(Conta destino, double valor) {
		this.saca(valor);
		destino.deposita(valor);
	}

	public void deposita(double valor) {
		saldo += valor;
		Conta.saldoBanco += valor;
	}
}
