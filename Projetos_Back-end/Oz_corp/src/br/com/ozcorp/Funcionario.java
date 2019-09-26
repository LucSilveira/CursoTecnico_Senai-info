package br.com.ozcorp;

import br.com.ozcorp.Sexo;
import br.com.ozcorp.TipoSanguineo;

public class Funcionario {

	// atributos
	private String nome;
	private String rg;
	private int cpf;
	private Encargo encargo;
	private String senha;
	private int inscricao;
	private String email;
	private Sexo sexo;
	private TipoSanguineo tipoSanguineo;

	public Funcionario(String nome, String rg, int cpf, int inscricao, String email, Encargo encargo, String senha,
			Sexo sexo, TipoSanguineo tipoSanguineo) {
		super();
		this.nome = nome;
		this.rg = rg;
		this.encargo = encargo;
		this.senha = senha;
		this.sexo = sexo;
		this.cpf = cpf;
		this.inscricao = inscricao;
		this.email = email;

		this.tipoSanguineo = tipoSanguineo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public int getCpf() {
		return cpf;
	}

	public void setCpf(int cpf) {
		this.cpf = cpf;
	}

	public int getInscricao() {
		return inscricao;
	}

	public void setInscricao(int inscricao) {
		this.inscricao = inscricao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Encargo getCargo() {
		return encargo;
	}

	public void setCargo(Encargo cargo) {
		this.encargo = cargo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public TipoSanguineo getTipoSanguineo() {
		return tipoSanguineo;
	}

	public void setTipoSanguineo(TipoSanguineo tipoSanguineo) {
		this.tipoSanguineo = tipoSanguineo;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
}