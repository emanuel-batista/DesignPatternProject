                                                                                                                                                        └── service/
                                                                                                                                                                            ├── ProcessadorDeAlbuns.java
                                                                                                                                                                                                └── ProcessadorCsvPadrao.java

# Projeto Agregador de Divas (Exemplo do Padrão Adapter)

Este é um projeto de demonstração em Java e Maven criado para ilustrar o uso do **Padrão de Projeto Estrutural Adapter**. O objetivo é mostrar como duas interfaces incompatíveis podem trabalhar juntas, permitindo a integração de um código legado a um sistema novo sem a necessidade de alterar o código-fonte existente.

---

## 📖 O Problema

Imagine um sistema que está sendo modernizado. Temos dois cenários de processamento de dados:

- **O Sistema Legado:** lia dados de artistas a partir de um ficheiro `.txt`. Ele processava o conteúdo inteiro do ficheiro como uma única string, esperando que cada registro fosse separado por um ponto e vírgula (`;`).
- **O Novo Sistema:** foi projetado para ser mais robusto e agora espera ler ficheiros no formato CSV, processando-os linha por linha.

**A questão:** como fazer o novo sistema, que é projetado para ler linha por linha, processar os dados do antigo formato `.txt` sem criar uma lógica complexa de if/else no seu núcleo?

---

## ✨ A Solução: O Padrão Adapter

O padrão Adapter atua como um "tradutor" ou um "adaptador de tomada" entre duas interfaces diferentes. Ele cria uma classe intermediária (o Adapter) que implementa a interface que o novo sistema espera, mas que, internamente, chama e traduz os dados do sistema legado.

### Componentes do padrão neste projeto:

| Papel no Padrão | Classe/Interface no Projeto | Descrição |
|-----------------|-----------------------------|-----------|
| Alvo (Target)   | service/ProcessadorDeAlbuns.java | A interface que o novo sistema (Cliente) espera e entende |
| Adaptado (Adaptee) | legacy/LeitorLegadoTxt.java | A classe legada, com a lógica antiga e interface incompatível, que queremos reutilizar |
| Adaptador (Adapter) | adapter/AdaptadorLeitorLegado.java | A ponte. Implementa a interface ProcessadorDeAlbuns, mas usa o LeitorLegadoTxt para fazer o trabalho |
| Cliente (Client) | Main.java | O sistema, que utiliza a interface ProcessadorDeAlbuns sem saber qual implementação está por trás |

---

## 📂 Estrutura do Projeto

O projeto utiliza a estrutura padrão do Maven para facilitar a organização do código.

```text
agregador-divas-adapter/
├── pom.xml
└── src
    └── main
        └── java
            └── br/com/agregadordivas
                ├── Main.java
                ├── adapter/
                │   └── AdaptadorLeitorLegado.java
                ├── legacy/
                │   └── LeitorLegadoTxt.java
                ├── model/
                │   └── Album.java
                └── service/
                    ├── ProcessadorDeAlbuns.java
                    └── ProcessadorCsvPadrao.java
```

---

## 🛠️ Tecnologias Utilizadas

- Java (configurado para a versão 11 ou superior)
- Maven (para gestão de dependências e build)

---

## 🚀 Como Executar

Para compilar e executar o projeto, você precisará ter o **JDK (Java Development Kit)** e o **Maven** instalados na sua máquina.

1. **Clone o repositório:**
   ```bash
   git clone <url-do-seu-repositorio>
   ```
2. **Navegue até o diretório do projeto:**
   ```bash
   cd agregador-divas-adapter
   ```
3. **Compile e execute o projeto via Maven:**
   O comando abaixo irá compilar o código e executar a classe `Main.java`.
   ```bash
   mvn compile exec:java -Dexec.mainClass="br.com.agregadordivas.Main"
   ```

---

## 📄 Saída Esperada

Ao executar o projeto, verá na consola a saída dos dois cenários, mostrando que tanto o processador padrão quanto o adaptador produziram uma lista de objetos `Album`, apesar de suas fontes de dados e lógicas serem completamente diferentes.

### CENÁRIO 1: Processando um ficheiro novo (CSV)

```
>>> Processador NOVO (CSV) em ação...
Resultado do processador CSV:
Album{artista='Taylor Swift', titulo='Midnights', ano=2022}
Album{artista='Beyoncé', titulo='Renaissance', ano=2022}
```

---

### CENÁRIO 2: Processando um ficheiro legado (TXT) com o Adaptador

```
--- Adaptador em ação! ---
Adaptador: convertendo 'linha a linha' para 'conteúdo completo' para o sistema legado.
>>> Leitor LEGADO em ação: processando conteúdo como uma única String...
Adaptador: convertendo a saída legada (String[][]) para o formato novo (List<Album>).
--- Adaptação concluída! ---
Resultado do processador adaptado:
Album{artista='ariana grande', titulo='yours truly', ano=2013}
Album{artista='lady gaga', titulo='artpop', ano=2013}
Album{artista='katy perry', titulo='prism', ano=2013}
