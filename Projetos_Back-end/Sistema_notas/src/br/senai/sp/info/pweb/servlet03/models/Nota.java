package br.senai.sp.info.pweb.servlet03.models;

import java.util.Date;

public class Nota {
	
	private Long id = null;
	private String titulo;
	private String descricao;
	private Boolean prioridadeAlta;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Boolean getPrioridadeAlta() {
		return prioridadeAlta;
	}
	public void setPrioridadeAlta(Boolean prioridadeAlta) {
		this.prioridadeAlta = prioridadeAlta;
	}
	public Date getDataDeConclusao() {
		return dataDeConclusao;
	}
	public void setDataDeConclusao(Date dataDeConclusao) {
		this.dataDeConclusao = dataDeConclusao;
	}
	private Date dataDeConclusao;

}
