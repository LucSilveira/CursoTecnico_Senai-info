package br.com.senai.agenda.modelo;

import java.io.Serializable;

/**
 * Created by adminLocal on 23/02/2018.
 */

public class Contato implements Serializable{
    private Long id;
    private String nome;
    private String telefone;
    private String endereco;
    private String email;
    private String face;
    private Double classificacao;

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
        return getId()+" - "+getNome()+" - "+ getClassificacao();
    }
}
