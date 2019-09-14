package br.senai.sp.info.ex04.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Produto {
	
	private Long id;
	private String nome;
	private float preco;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dataCadastro;
	private Usuario usuario;
	
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
	public float getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date data) {
		this.dataCadastro = data;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
