import com.designpattern.model.Album;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeEach;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

import com.designpattern.services.ProcessadorDeAlbuns;

package com.designpattern.services;



class ProcessadorCsvPadraoTest {

    private static final String CSV_PATH = "src/main/resources/dados/albuns_novos.csv";

    @BeforeEach
    void cleanUpCsv() throws IOException {
        // Limpa o arquivo antes de cada teste
        Files.deleteIfExists(Paths.get(CSV_PATH));
        Files.createDirectories(Paths.get(CSV_PATH).getParent());
        Files.createFile(Paths.get(CSV_PATH));
    }

    @Test
    void testProcessarAlbumAdicionaLinhaNoCsv() throws IOException {
        Album album = new Album(1, "Nome do Álbum", "Artista", java.sql.Date.valueOf(LocalDate.of(2024, 6, 1)));
        ProcessadorDeAlbuns processador = new ProcessadorCsvPadrao();

        processador.processarAlbum(album);

        List<String> linhas = Files.readAllLines(Paths.get(CSV_PATH));
        assertEquals(1, linhas.size());
        String[] campos = linhas.get(0).split(",");
        assertEquals("1", campos[0]);
        assertEquals("Nome do Álbum", campos[1]);
        assertEquals("Artista", campos[2]);
        assertEquals("2024-06-01", campos[3]);
        assertEquals("Pop", campos[4]);
    }

    @Test
    void testProcessarAlbumAppendNoCsv() throws IOException {
        Album album1 = new Album(2L, "Primeiro", "Artista1", LocalDate.of(2020, 1, 1));
        Album album2 = new Album(3L, "Segundo", "Artista2", LocalDate.of(2021, 2, 2));
        ProcessadorDeAlbuns processador = new ProcessadorCsvPadrao();

        processador.processarAlbum(album1);
        processador.processarAlbum(album2);

        List<String> linhas = Files.readAllLines(Paths.get(CSV_PATH));
        assertEquals(2, linhas.size());
        assertTrue(linhas.get(0).contains("Primeiro"));
        assertTrue(linhas.get(1).contains("Segundo"));
    }

    @Test
    void testProcessarAlbumComCaracteresEspeciais() throws IOException {
        Album album = new Album(4L, "Álbum, com vírgula", "Artista \"Especial\"", LocalDate.of(2022, 3, 3));
        ProcessadorDeAlbuns processador = new ProcessadorCsvPadrao();

        processador.processarAlbum(album);

        List<String> linhas = Files.readAllLines(Paths.get(CSV_PATH));
        assertEquals(1, linhas.size());
        // O OpenCSV coloca aspas em campos com vírgula ou aspas
        assertTrue(linhas.get(0).contains("\"Álbum, com vírgula\""));
        assertTrue(linhas.get(0).contains("\"Artista \"\"Especial\"\"\""));
    }
}

// Recomendamos instalar uma extensão para executar testes java.