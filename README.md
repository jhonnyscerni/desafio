# Desafio API

## Descrição do Projeto

Este projeto é uma API para gerenciamento de compras e recomendações de vinhos. A API permite listar compras, obter a maior compra por ano e obter recomendações de vinhos com base no CPF do cliente e Listar os clientes fieis. O projeto utiliza Spring Boot e está documentado com OpenAPI 3.

## Documentação da API

A documentação completa da API pode ser acessada através do Swagger UI, disponível no seguinte endereço após iniciar a aplicação:

```
http://localhost:8080/swagger-ui.html
```

![swagger.png](src%2Fmain%2Fresources%2Fdoc%2Fswagger.png)

## Tecnologias Utilizadas

- **Java**: Linguagem de programação utilizada.
- **Spring Boot**: Framework para criação de aplicações Java.
- **Maven**: Ferramenta de automação de compilação e gerenciamento de dependências.
- **OpenAPI 3**: Especificação para descrever APIs RESTful.
- **Lombok**: Biblioteca Java que reduz o código boilerplate, como getters, setters e construtores.
- **Spring Data JPA**: Abstração sobre JPA (Java Persistence API) que facilita a implementação de repositórios de dados.
- **Mockito**: Framework para criação de mocks em testes unitários.
- **Scheduled**: Anotação do Spring utilizada para agendar tarefas.
- **Postgres**: Banco de dados

## Arquitetura e Solução do Problema

A aplicação foi desenvolvida com uma arquitetura limpa e modular, utilizando os princípios SOLID e boas práticas. A combinação de Feign Clients para consumo de APIs externas, um scheduler para sincronizar dados com a base de dados, e um design em camadas garante alta resiliência, desempenho e facilidade de manutenção. Essa abordagem torna a aplicação preparada para escalabilidade e futuras extensões de funcionalidade.

####  Modulos
- **Domain**: A camada Domínio fica no centro da Arquitetura Limpa. É o coração de seu aplicativo e responsável por seus modelos principais. Aqui definimos coisas como: models, Gateways de interfaces, etc.
- **Application**: A camada Application fica logo acima da camada Domain. Ele atua como um orquestrador para a camada de Domain, contendo os casos de uso mais importantes em sua aplicação.
- **Infrastructure**: A camada de infraestrutura contém implementações para serviços externos. O que se enquadraria nesta categoria?
Databases como PostgreSQL e clients para serviços externos como Feign Client.

###  Solução Implementada

![arquitetura.png](src%2Fmain%2Fresources%2Fdoc%2Farquitetura.png)

- **Feign Client**: Utilizado para consumir dados de APIs externas em formato JSON. O Feign simplifica a criação de clientes HTTP, permitindo a definição de interfaces que representam os endpoints das APIs externas.
- **Schedule**: Utilizado para agendar tarefas que consomem dados das APIs externas e os cadastram em uma base de dados própria. O agendamento é configurado para rodar em intervalos regulares, garantindo que os dados estejam sempre atualizados.

#### Benefícios da Abordagem

1. Arquitetura Modular e Escalável
   A separação de responsabilidades em camadas e a independência de frameworks permitem que a aplicação seja escalável, mantendo a lógica de negócios desacoplada das dependências externas.
2. Resiliência e Performance
   O uso de uma base de dados, sincronizada periodicamente com os serviços externos, reduz a dependência direta de APIs externas e melhora o desempenho ao evitar requisições desnecessárias, alem de garantir a disponibilidade dos dados mesmo em caso de falhas nos serviços externos.
3. Manutenção e Evolução
   A implementação dos princípios SOLID facilita a manutenção e expansão do sistema, permitindo que novos casos de uso ou funcionalidades sejam adicionados sem comprometer o código existente.

## Endpoints

### Listar Compras Ordenadas

- **Descrição**: Retorna uma lista de compras ordenadas pelo valor total.
- **Método HTTP**: GET
- **URL**: `/compras`
- **Resposta**:
    - **Código 200**: Lista de compras retornada com sucesso.
    - **Código 500**: Erro interno do servidor.
  - **Exemplo de Resposta**:
    ```json
    [
      {
         "clienteNome": "Hadassa Daniela Sales",
          "clienteCpf": "1051252612",
          "produtoCodigo": 12,
          "produtoTipoVinho": "Branco",
          "produtoPreco": 106.5,
          "produtoSafra": "2018",
          "produtoAnoCompra": 2019,
          "quantidade": 2,
          "valorTotal": 213 
      }
    ]
    ```

### Obter Maior Compra por Ano

- **Descrição**: Retorna a maior compra realizada no ano especificado.
- **Método HTTP**: GET
- **URL**: `/maior-compra/{ano}`
- **Parâmetros**:
    - **ano**: Ano para o qual se deseja obter a maior compra.
- **Resposta**:
    - **Código 200**: Maior compra retornada com sucesso.
    - **Código 500**: Erro interno do servidor.
- **Exemplo de Resposta**:
  ```json
  {
    "clienteNome": "Ian Joaquim Giovanni Santos",
    "clienteCpf": "96718391344",
    "produtoCodigo": 3,
    "produtoTipoVinho": "Rosé",
    "produtoPreco": 121.75,
    "produtoSafra": "2019",
    "produtoAnoCompra": 2020,
    "quantidade": 20,
    "valorTotal": 2435
  }
  ```

### Obter Recomendação de Vinho

- **Descrição**: Retorna recomendações de vinhos com base no CPF do cliente.
- **Método HTTP**: GET
- **URL**: `/recomendacao-vinho/{cpf}`
- **Parâmetros**:
    - **cpf**: CPF do cliente para o qual se deseja obter recomendações.
- **Resposta**:
    - **Código 200**: Recomendações de vinhos retornadas com sucesso.
    - **Código 404**: Cliente não encontrado.
    - **Código 500**: Erro interno do servidor.
- **Exemplo de Resposta**:
  ```json
    {
      "tipoVinho": "Rosé",
      "safra": "2019",
      "preco": 121.75,
      "quantidadeTotal": 2
    }
  ```
### Obter Clientes Fiéis

Retorna o Top 3 dos clientes mais fiéis.
- **Método HTTP**: GET
- **URL**: `/clientes-fieis`
- **Resposta**:
  - **Código 200**: Lista de clientes fiéis retornada com sucesso.
  - **Código 500**: Erro interno do servidor.
- **Exemplo de Resposta**:
  ```json
    [
      {
        "nome": "Ian Joaquim Giovanni Santos",
        "cpf": "96718391344"
      },
      {
        "nome": "Geraldo Pedro Julio Nascimento",
        "cpf": "05870189179"
      },
      {
        "nome": "Andreia Emanuelly da Mata",
        "cpf": "27737287426"
      }
    ]
  ```
## Testes Unitários
Os testes unitários foram criados para garantir a funcionalidade correta dos casos de uso e dos controladores da aplicação. Utilizamos o framework Mockito para criar mocks e simular o comportamento das dependências externas. A seguir estão alguns exemplos de testes unitários implementados:
![Testes Unitarios.png](src%2Fmain%2Fresources%2Fdoc%2FTestes%20Unitarios.png)