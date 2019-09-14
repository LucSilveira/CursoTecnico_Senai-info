package br.senai.sp.info.pweb.ianes.models;

import java.util.Date;

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
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "patrimonio")
public class Patrimonio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NonNull
	@Size(min = 1 , max = 40)
	@Column(length = 40, nullable = false, unique = true)
	private String nome;
	
	@Column(nullable = false, unique = false)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date data_cadastro;
	
	@ManyToOne // n -> 1
	@JoinColumn(nullable = false, name = "cod_categoria")
	private Categoria obtem;
	
	@ManyToOne // n -> 1
	@JoinColumn(nullable = false, name = "cod_usuario")
	private Usuario cadastrador;
	
	@Transient
	private String caminhoFoto;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(Date data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Usuario getCadastrador() {
		return cadastrador;
	}

	public void setCadastrador(Usuario cadastrador) {
		this.cadastrador = cadastrador;
	}

	public Categoria getObtem() {
		return obtem;
	}

	public void setObtem(Categoria obtem) {
		this.obtem = obtem;
	}
	
	@Override
	public String toString() {
		return "Patrimonio [id=" + id + ", nome=" + nome + ", data_cadastro=" + data_cadastro + ", obtem=" + obtem
				+ ", cadastrador=" + cadastrador + ", caminhoFoto=" + caminhoFoto + "]";
	}

	public String getCaminhoFoto() {
		return caminhoFoto;
	}

	public void setCaminhoFoto(String caminhoFoto) {
		this.caminhoFoto = caminhoFoto;
	}

}