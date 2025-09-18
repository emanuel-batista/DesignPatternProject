package com.designpattern.factory;

import com.designpattern.model.Album;

public class CriadorAlbuns {
    private static int contadorId = 1;

    public static Album criarAlbum(String nome, String nomeArtista, java.util.Date dataLancamento) {
        Album album = new Album(contadorId++, nome, nomeArtista, dataLancamento);
        return album;
    }
}
