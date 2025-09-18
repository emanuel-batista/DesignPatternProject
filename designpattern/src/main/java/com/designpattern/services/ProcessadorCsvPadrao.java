package com.designpattern.services;

import java.io.FileWriter;
import java.io.IOException; // <-- 1. IMPORT CORRETO

import com.designpattern.model.Album;   // <-- Adicionado para escrever no arquivo
import com.opencsv.CSVWriter;  // <-- Adicionado para o catch de erro

public class ProcessadorCsvPadrao implements ProcessadorDeAlbuns {

    @Override
    public void processarAlbum(Album album) {
        System.out.println("Adicionando ao arquivo CSV: ");
        String caminhoArquivo = "src/main/resources/dados/albuns_novos.csv";

        // É uma prática melhor usar um array de Strings com o método writeNext()
        // Ele lida automaticamente com caracteres especiais, como vírgulas no nome.
        String[] linhaCsv = {
            String.valueOf(album.getId()),
            album.getNome(),
            album.getNomeArtista(),
            album.getDataLancamento().toString(),
            "Pop"
        };

        // 2. USO CORRETO COM TRY-WITH-RESOURCES
        // O FileWriter é aberto em modo "append" (true) para não apagar o conteúdo anterior.
        try (FileWriter fileWriter = new FileWriter(caminhoArquivo, true);
             CSVWriter writer = new CSVWriter(fileWriter)) {

            writer.writeNext(linhaCsv, true); // Escreve a linha no arquivo CSV
            System.out.println("Linha adicionada: " + String.join(",", linhaCsv));

        } catch (IOException e) { // Usar uma exceção mais específica é uma boa prática
            System.err.println("Erro ao escrever no arquivo CSV: " + e.getMessage());
        }
    }
}