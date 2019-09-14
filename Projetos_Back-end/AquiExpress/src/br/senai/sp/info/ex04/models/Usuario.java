package br.senai.sp.info.ex04.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.DigestUtils;

public class Usuario {
	
	private Long id;
	private String nomeCompleto;
	private String usuario;
	private String email;
	private String senha;
	private Date dataNascimento;
	
	//Criptografar Senha
		public void hashearSenha() {
			
			try {
				this.senha = DigestUtils.md5DigestAsHex(this.senha.getBytes("UTF-8"));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nomeCompleto=" + nomeCompleto + ", usuario=" + usuario + ", email=" + email
				+ ", senha=" + senha + ", dataNascimento=" + dataNascimento + "]";
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
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

}
