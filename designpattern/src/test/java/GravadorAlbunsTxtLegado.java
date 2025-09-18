import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GravadorAlbunsTxtLegadoTest {

    private static final String CAMINHO_DO_ARQUIVO = "output/albuns_antigos.txt";

    @BeforeEach
    void limparArquivoAntes() throws IOException {
        Path caminho = Paths.get(CAMINHO_DO_ARQUIVO);
        if (Files.exists(caminho)) {
            Files.delete(caminho);
        }
        Files.createDirectories(caminho.getParent());
    }

    void gravarAlbum(String artista, String titulo, int ano) {
        String registro = String.format("%s, %s, %d;%n", artista, titulo, ano);
        try (FileWriter fileWriter = new FileWriter(CAMINHO_DO_ARQUIVO, true);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.printf(registro);
        } catch (IOException e) {
            fail("Erro ao gravar álbum: " + e.getMessage());
        }
    }

    @Test
    void testGravarAlbumCriaArquivoComConteudoCorreto() throws IOException {
        String artista = "Adele";
        String titulo = "21";
        int ano = 2011;
        gravarAlbum(artista, titulo, ano);

        String conteudo = Files.readString(Paths.get(CAMINHO_DO_ARQUIVO));
        assertEquals(String.format("%s, %s, %d;%n", artista, titulo, ano), conteudo);
    }

    @Test
    void testGravarAlbumAppend() throws IOException {
        gravarAlbum("Taylor Swift", "Midnights", 2022);
        gravarAlbum("Beyoncé", "Renaissance", 2022);

        String conteudo = Files.readString(Paths.get(CAMINHO_DO_ARQUIVO));
        String esperado = String.format("Taylor Swift, Midnights, 2022;%nBeyoncé, Renaissance, 2022;%n");
        String[] linhas = conteudo.split("\\r?\\n");
        assertEquals("Taylor Swift, Midnights, 2022;", linhas[0]);
        assertEquals("Beyoncé, Renaissance, 2022;", linhas[1]);
    }

    @AfterEach
    void limparArquivoDepois() throws IOException {
        Path caminho = Paths.get(CAMINHO_DO_ARQUIVO);
        if (Files.exists(caminho)) {
            Files.delete(caminho);
        }
    }
}

