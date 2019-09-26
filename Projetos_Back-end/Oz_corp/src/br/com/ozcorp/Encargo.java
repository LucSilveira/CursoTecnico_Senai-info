package br.com.ozcorp;

public class Encargo {

	private Designacao titulo;
	private Setor setor;
	private double salarioBase;
	private int classe;
	private String sigla;

	public Encargo(Designacao titulo, Setor setor, double salarioBase, int classe, String sigla) {
		super();
		this.salarioBase = salarioBase;
		this.titulo = titulo;
		this.setor = setor;
		this.classe = classe;
		this.sigla = sigla;
	}

	public Designacao getTitulo() {
		return titulo;
	}

	public void setTitulo(Designacao titulo) {
		this.titulo = titulo;
	}

	public double getSalarioBase() {
		return salarioBase;
	}

	public void setSalarioBase(double salarioBase) {
		this.salarioBase = salarioBase;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public int getClasse() {
		return classe;
	}

	public void setClasse(int classe) {
		this.classe = classe;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
}
