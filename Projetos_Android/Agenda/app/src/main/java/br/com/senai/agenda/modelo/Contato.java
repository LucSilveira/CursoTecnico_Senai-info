package br.com.senai.agenda.modelo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by adminLocal on 23/02/2018.
 */

public class Contato implements Serializable {
    @SerializedName("idContato")
    private Long id;
    private String nome;
    private String telefone;
    private String endereco;
    private String email;
    @SerializedName("linkFacebook")
    private String face;
    @SerializedName("ratingBar")
    private Double classificacao;
    private String caminhoFoto;



    public String getCaminhoFoto() {
        return caminhoFoto;
    }

    public void setCaminhoFoto(String caminhoFoto) {
        this.caminhoFoto = caminhoFoto;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public Double getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(Double classificacao) {
        this.classificacao = classificacao;
    }

    @Override
    public String toString() {
        return getNome();
    }

}
