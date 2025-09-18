package com.designpattern.services;

import java.io.FileWriter;
import java.io.IOException;

import com.designpattern.model.Album;
import com.opencsv.CSVWriter;

public class ProcessadorCsvPadrao implements ProcessadorDeAlbuns {

    @Override
    public void processarAlbum(Album album) {
        System.out.println("Adicionando ao arquivo CSV: ");
        String caminhoArquivo = "src/main/resources/dados/albuns_novos.csv";

        String[] linhaCsv = {
            String.valueOf(album.getId()),
            album.getNome(),
            album.getNomeArtista(),
            album.getDataLancamento().toString(),
            "Pop"
        };

        try (FileWriter fileWriter = new FileWriter(caminhoArquivo, true);
             CSVWriter writer = new CSVWriter(fileWriter)) {

            writer.writeNext(linhaCsv, true);
            System.out.println("Linha adicionada: " + String.join(",", linhaCsv));

    } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo CSV: " + e.getMessage());
        }
    }
}