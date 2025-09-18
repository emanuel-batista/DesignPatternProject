
# Padrão de Projeto Adapter: Exemplo com Sistema de Álbuns

Este projeto é uma demonstração prática em Java do Padrão de Projeto Estrutural **Adapter**. O objetivo é ilustrar como integrar um componente legado a um sistema novo através de um "adaptador", permitindo que interfaces incompatíveis trabalhem juntas de forma harmoniosa.

## O Cenário
Imagine que estamos modernizando um sistema de catalogação de álbuns musicais. Temos dois requisitos de funcionalidade:

1. **O Novo Sistema:** Deve salvar os dados de novos álbuns num ficheiro moderno e estruturado, como um **CSV**. A lógica para isso é implementada através de uma interface clara e moderna ```ProcessadorDeAlbuns```.

2. **O Sistema Legado:** Por razões de compatibilidade ou migração gradual, ainda precisamos de gravar os dados num sistema antigo, que salva as informações num ficheiro **.txt** com um formato muito específico ex: ```artista, titulo, ano;```. A classe que faz isso ```GravadorAlbunsTxtLegado``` é antiga, monolítica e tem uma interface totalmente diferente.

**O problema:** Como podemos fazer com que o nosso novo código, que só "entende" a interface ```ProcessadorDeAlbuns```, consiga também gravar dados no formato legado sem que ele precise de conhecer os detalhes sujos do sistema antigo?
## A Solução: O Padrão Adapter

O padrão Adapter funciona como um tradutor. Ele "embrulha" o componente incompatível (o nosso gravador de ```.txt```) numa classe que implementa a interface que o nosso sistema espera.

Neste projeto, os papéis são distribuídos da seguinte forma:

| **Papel no Padrão**     | **Classe/Interface no Projeto**            | **Descrição**                                                                                                        |
|-------------------------|--------------------------------------------|----------------------------------------------------------------------------------------------------------------------|
| **Alvo (Target)**       | ```services.ProcessadorDeAlbuns```         | A interface que o nosso novo sistema (o ```Menu```) espera e entende.                                                |
| **Adaptado (Adaptee)**  | ```legacy.GravadorAlbunsTxtLegado```       | A classe legada, com a lógica antiga e a interface incompatível, que queremos reutilizar.                            |
| **Adaptador (Adapter)** | ```adapter.ProcessarAlbunsLegacyAdapter``` | A ponte. Implementa a interface ```ProcessadorDeAlbuns```, mas, internamente, chama o ```GravadorAlbunsTxtLegado```. |
| **Cliente (Client)**    | ```ui.Menu```                              | O nosso sistema, que utiliza a interface ```ProcessadorDeAlbuns``` sem saber qual implementação está por trás.       |

Desta forma, o Menu pode tratar tanto o ```ProcessadorCsvPadrao``` quanto o ```ProcessarAlbunsLegacyAdapter``` da mesma maneira, tornando o código limpo, desacoplado e extensível.


## Estrutura do Projeto

O projeto utiliza a estrutura padrão do Maven para uma organização clara do código-fonte, recursos e testes.

```
.
├── pom.xml
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── designpattern
    │   │           ├── Main.java               // Ponto de entrada que inicia o Menu
    │   │           ├── adapter/
    │   │           ├── factory/
    │   │           ├── legacy/
    │   │           ├── model/
    │   │           ├── services/
    │   │           └── ui/
    │   └── resources
    │       └── dados
    │           └── albuns_novos.csv            // Ficheiro de saída do novo sistema
    └── test
        └── java
            └── com
                └── designpattern
                    └── legacy
                        └── ProcessarAlbunsLegacyTest.java // Teste unitário do sistema legado
```


## Tecnologias Utilizadas

* **Java** (configurado para a versão 17 ou superior)
* **Maven** (para gestão de dependências e build do projeto)
* **JUnit 5** (para os testes unitários da classe legada)
* **OpenCSV** (para facilitar a escrita no ficheiro CSV)
## Como Executar

Para compilar e executar o projeto, precisa de ter o JDK (Java Development Kit) e o Maven instalados.

1. **Clone o repositório:**
```
git clone "https://github.com/emanuel-batista/DesignPatternProject"

```
2. **Navegue até o diretório do projeto:**
```
cd DesignPatternProject

```
3. **Compile e execute os testes:**
```
mvn test

```
4. **Execute a aplicação principal (o Menu):**
O comando abaixo irá compilar o código e executar a classe ```Main.java```, que por sua vez inicia o ```Menu```.
```
mvn compile exec:java -Dexec.mainClass="com.designpattern.Main"


```

