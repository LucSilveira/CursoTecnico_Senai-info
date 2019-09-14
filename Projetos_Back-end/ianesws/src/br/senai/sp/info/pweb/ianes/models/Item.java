package br.senai.sp.info.pweb.ianes.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "item")
public class Item {

	@Override
	public String toString() {
		return "Item [identificacao=" + identificacao + ", localizacao=" + localizacao + ", cadastro=" + cadastro
				+ ", associado=" + associado + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long identificacao;
	
	@Column(nullable = true, unique = false, name = "data_movimentou")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date data_movimentou;
	
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne(cascade = CascadeType.MERGE) //[n] ocorrencia <-> [1] usuario
	@JoinColumn(nullable = true, name = "cod_ambiente")
	private Ambiente localizacao; //quem atendeu
	
	
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne(cascade = CascadeType.MERGE) //[n] ocorrencia <-> [1] usuario
	@JoinColumn(nullable = true, name = "cod_usuario")
	private Usuario cadastro; //quem atendeu
	
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne(cascade = CascadeType.MERGE) //[n] ocorrencia <-> [1] usuario
	@JoinColumn(nullable = true, name = "cod_patrimonio")
	private Patrimonio associado; //quem atendeu
	
	@Transient
	private String caminhoFoto;
	
	public Date getData_movimentou() {
		return data_movimentou;
	}

	public void setData_movimentou(Date data_movimentou) {
		this.data_movimentou = data_movimentou;
	}

	public String getCaminhoFoto() {
		return caminhoFoto;
	}

	public void setCaminhoFoto(String caminhoFoto) {
		this.caminhoFoto = caminhoFoto;
	}

	public Long getIdentificacao() {
		return identificacao;
	}

	public void setIdentificacao(Long identificacao) {
		this.identificacao = identificacao;
	}

	public Ambiente getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(Ambiente localizacao) {
		this.localizacao = localizacao;
	}

	public Usuario getCadastro() {
		return cadastro;
	}

	public void setCadastro(Usuario cadastro) {
		this.cadastro = cadastro;
	}

	public Patrimonio getAssociado() {
		return associado;
	}

	public void setAssociado(Patrimonio associado) {
		this.associado = associado;
	}
	
}