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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Movimentacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = false)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date data_movimentacao;

	@ManyToOne // [n] ocorrencia <-> [1] usuairo
	@JoinColumn(nullable = false, name = "cod_usuario")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Usuario movimentou;

	@ManyToOne // [n] ocorrencia <-> [1] usuairo
	@JoinColumn(nullable = false, name = "cod_ambiente_origem")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Ambiente origem;

	@ManyToOne // [n] ocorrencia <-> [1] usuairo
	@JoinColumn(nullable = false, name = "cod_ambiente_destino")
	private Ambiente destino;
	
	@ManyToOne // [n] ocorrencia <-> [1] usuairo
	@JoinColumn(nullable = false, name = "identificacao")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Item identificacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData_movimentacao() {
		return data_movimentacao;
	}

	public void setData_movimentacao(Date data_movimentacao) {
		this.data_movimentacao = data_movimentacao;
	}

	public Usuario getMovimentou() {
		return movimentou;
	}

	public void setMovimentou(Usuario movimentou) {
		this.movimentou = movimentou;
	}

	public Ambiente getOrigem() {
		return origem;
	}

	public void setOrigem(Ambiente origem) {
		this.origem = origem;
	}

	public Ambiente getDestino() {
		return destino;
	}

	public void setDestino(Ambiente destino) {
		this.destino = destino;
	}

	public Item getIdentificacao() {
		return identificacao;
	}

	public void setIdentificacao(Item identificacao) {
		this.identificacao = identificacao;
	}

	@Override
	public String toString() {
		return "Movimentacao [id=" + id + ", data_movimentacao=" + data_movimentacao + ", movimentou=" + movimentou
				+ ", origem=" + origem + ", destino=" + destino + ", identificacao=" + identificacao + "]";
	}

}
