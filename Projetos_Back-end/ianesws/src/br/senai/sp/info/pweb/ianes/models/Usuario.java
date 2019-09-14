package br.senai.sp.info.pweb.ianes.models;

import javax.persistence.Transient;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.DigestUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.internal.NotNull;

import br.senai.sp.info.pweb.ianes.models.Tipo_usuario;

@Entity
@Table(name = "usuario")
public class Usuario implements Authentication{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(nullable = false, unique = false)
	private Tipo_usuario tipo_usuario = Tipo_usuario.COMUM;
	
	@Column(length = 30, nullable = false, unique = false)
	@NotNull
	@Size(min = 1, max = 30)
	private String nome;
	
	@Column(length = 40, nullable = false, unique = false)
	@NotNull
	@Size(min = 1, max = 40)
	private String sobrenome;
	
	@Column(length = 120, nullable = false, unique = true)
	@NotNull
	@Email
	@Size(max = 120)
	private String email;
	
	@Column(length = 32, nullable = false, unique = false)
	@NotNull
	@Size(min = 1, max = 32)
	private String senha;
	
	@Transient
	private String caminhoFoto;
	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", tipo_usuario=" + tipo_usuario + ", nome=" + nome + ", sobrenome=" + sobrenome
				+ ", email=" + email + ", senha=" + senha + ", caminhoFoto=" + caminhoFoto + "]";
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

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
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

	public Tipo_usuario getTipo() {
		return tipo_usuario;
	}
 
	public void setTipo(Tipo_usuario tipo_usuario) {
		this.tipo_usuario = tipo_usuario;
	}
	
	public void hashearSenha() {
		this.senha = DigestUtils.md5DigestAsHex(this.senha.getBytes());
	}

	public String getCaminhoFoto() {
		return caminhoFoto;
	}

	public void setCaminhoFoto(String caminhoFoto) {
		this.caminhoFoto = caminhoFoto;
	}

	/**********************************************************/
	
	@Override
	public String getName() {
		return email;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getDetails() {
		return null;
	}

	@Override
	@JsonIgnore
	public Object getPrincipal() {
		return this;
	}

	@Override
	public boolean isAuthenticated() {
		return true;
	}

	@Override
	public void setAuthenticated(boolean arg0) throws IllegalArgumentException {
	}
	
}