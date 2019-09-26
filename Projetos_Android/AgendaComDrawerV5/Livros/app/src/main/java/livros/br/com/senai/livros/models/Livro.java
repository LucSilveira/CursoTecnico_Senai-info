package livros.br.com.senai.livros.models;

import java.io.Serializable;

/**
 * Created by 46923597811 on 04/04/2018.
 */

public class Livro implements Serializable{

    private Long id;
    private String nome;
    private String autor;
    private String genero;
    private String editora;
    private String ano_produção;
    private String foto;

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

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getAno_produção() {
        return ano_produção;
    }

    public void setAno_produção(String ano_produção) {
        this.ano_produção = ano_produção;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
