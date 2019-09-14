package br.senai.sp.info.pweb.somativa.models;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.DigestUtils;

public class Usuario {
	
	private Long id;
	private String nome;
	private String email;
	private String senha;
	private Date dataNascimento;
	private Sexo sexo;
	
	public void hashearSenha() {
		try {
			this.senha = DigestUtils.md5DigestAsHex(this.senha.getBytes("UTF-8"));
		}catch(UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", dataNascimento="
				+ dataNascimento + ", sexo=" + sexo + "]";
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Sexo getSexo() {
		return sexo;
	}
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	
}
