package com.designpattern.model;

import java.util.Date;

public class Album {
    private int id;
    private String nome;
    private String nomeArtista;
    private Date dataLancamento;

    public Album(int id, String nome, String nomeArtista, Date dataLancamento) {
        this.id = id;
        this.nome = nome;
        this.nomeArtista = nomeArtista;
        this.dataLancamento = dataLancamento;
    }

    public Album(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeArtista() {
        return nomeArtista;
    }

    public void setNomeArtista(String nomeArtista) {
        this.nomeArtista = nomeArtista;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    
}
