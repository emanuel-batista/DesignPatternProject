package com.designpattern.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner; // Import necessário

import com.designpattern.adapter.ProcessarAlbunsLegacyAdapter;
import com.designpattern.factory.CriadorAlbuns;
import com.designpattern.legacy.ProcessarAlbunsLegacy;
import com.designpattern.model.Album; // Import específico para a exceção
import com.designpattern.services.ProcessadorCsvPadrao;
import com.designpattern.services.ProcessadorDeAlbuns;

public class Menu {

    private final Scanner scanner;
    private final ProcessadorDeAlbuns processadorCsv;
    private final ProcessadorDeAlbuns processadorLegacy; // <-- Dica de boa prática!

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.processadorCsv = new ProcessadorCsvPadrao();

        // Inicializamos o sistema legado e o "embrulhamos" no adaptador.
        ProcessarAlbunsLegacy sistemaLegado = new ProcessarAlbunsLegacy();
        this.processadorLegacy = new ProcessarAlbunsLegacyAdapter(sistemaLegado);
    }

    // O método exibir() continua igual...
    public void exibir() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- MENU DE OPÇÕES ---");
            System.out.println("1. Gravar álbum no sistema novo (CSV)");
            System.out.println("2. Gravar álbum no sistema legado (TXT)");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Erro: Por favor, digite um número válido.");
                opcao = -1; // Reseta a opção para evitar loop infinito se o próximo input falhar
                continue;
            }

            switch (opcao) {
                case 1 -> criarEProcessarAlbum();
                case 2 -> criarAlbumLegacy();
                case 0 -> System.out.println("Saindo do programa...");
                default -> System.out.println("Opção inválida!");
            }
        }
        scanner.close();
    }

    /**
     * NOVO MÉTODO AUXILIAR: Centraliza a lógica de input e criação do objeto Album.
     * @return O objeto Album criado com os dados do utilizador, ou null se ocorrer um erro.
     */
    private Album criarAlbumComInputDoUsuario() {
        System.out.print("Digite o nome do álbum: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o nome do artista: ");
        String artista = scanner.nextLine();

        System.out.print("Digite a data de lançamento (formato: yyyy-MM-dd): ");
        String dataInput = scanner.nextLine();

        SimpleDateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dataLancamento;
        try {
            // Se o utilizador não digitar nada, usamos uma data padrão.
            if (dataInput.isBlank()) {
                System.out.println("Nenhuma data inserida, usando data padrão.");
                dataLancamento = new java.util.Date(0); // 1970-01-01
            } else {
                dataLancamento = formatoData.parse(dataInput);
            }
        } catch (ParseException e) {
            System.out.println("Formato de data inválido! A usar data padrão.");
            dataLancamento = new java.util.Date(0); // 1970-01-01
        }

        // Usa a factory para criar o objeto e o retorna.
        return CriadorAlbuns.criarAlbum(nome, artista, dataLancamento);
    }

    /**
     * MÉTODO REATORIZADO: Agora apenas chama o método auxiliar e o processador CSV.
     */
    private void criarEProcessarAlbum() {
        System.out.println("\n--- A gravar no sistema novo (CSV) ---");
        Album novoAlbum = criarAlbumComInputDoUsuario();
        if (novoAlbum != null) {
            processadorCsv.processarAlbum(novoAlbum);
            System.out.println("Álbum processado com sucesso!");
        }
    }

    /**
     * MÉTODO REATORIZADO: Agora apenas chama o método auxiliar e o processador adaptado.
     */
    private void criarAlbumLegacy() {
        System.out.println("\n--- A gravar no sistema legado (via Adaptador) ---");
        Album novoAlbum = criarAlbumComInputDoUsuario();
        if (novoAlbum != null) {
            processadorLegacy.processarAlbum(novoAlbum);
            System.out.println("Álbum processado com sucesso!");
        }
    }
}
