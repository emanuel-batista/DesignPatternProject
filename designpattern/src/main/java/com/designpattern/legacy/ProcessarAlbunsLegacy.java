package com.designpattern.legacy;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ProcessarAlbunsLegacy {

    private static final String CAMINHO_DO_ARQUIVO = "src/main/resources/dados/albuns_antigos.txt";

    public void gravarAlbum(String artista, String titulo, int ano) {
        System.out.println(">>> [LEGADO] A processar o pedido para gravar um novo álbum...");
        String registro = String.format("%s, %s, %d;%n", artista, titulo, ano);
        try {
            Path caminhoDoDiretorio = Paths.get("output");
            Files.createDirectories(caminhoDoDiretorio);
            try (FileWriter fileWriter = new FileWriter(CAMINHO_DO_ARQUIVO, true);
                 PrintWriter printWriter = new PrintWriter(fileWriter)) {
                printWriter.printf(registro);
            }
            System.out.println(">>> [LEGADO] Álbum gravado com sucesso em '" + CAMINHO_DO_ARQUIVO + "'");
        } catch (IOException e) {
            System.err.println(">>> [LEGADO] Ocorreu um erro ao tentar gravar no ficheiro: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
