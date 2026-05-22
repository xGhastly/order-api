# Order API

Este projeto é uma API RESTful de gerenciamento de pedidos e processamento de entregas desenvolvida com Spring Boot, Java 21 e RabbitMQ.

O sistema foi desenhado sob o conceito de Monolito Modular, onde os domínios de Pedido e Entrega vivem na mesma aplicação, mas são completamente isolados e se comunicam de forma assíncrona orientada a eventos.

## 🚀 Como Executar

A forma mais simples de executar a aplicação é utilizando o Docker Compose, que subirá tanto o broker de mensagens (RabbitMQ) quanto a própria API.

### Pré-requisitos
- Docker instalado
- Docker Compose instalado

### Passo Único
Na raiz do projeto, execute o comando:
```bash
docker-compose up -d --build
```

Após a execução, a API estará disponível em `http://localhost:8080/api/pedidos`.
O Console do H2 (Banco de Dados) pode ser acessado em `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:orderapidb`, User: `sa`, Password: `(vazio)`).
O Painel de Gerenciamento do RabbitMQ pode ser acessado em `http://localhost:15672` (User: `guest`, Password: `guest`).

### Acessos
 - API Base URL: http://localhost:8080/api/pedidos
 - Banco de Dados (H2 Console): http://localhost:8080/h2-console
   - JDBC URL: jdbc:h2:mem:orderapidb | User: sa | Password: (em branco)
 - RabbitMQ Dashboard: http://localhost:15672
   - User: guest | Password: guest

## Como Testar (Payload de Exemplo)

Para criar um novo pedido, envie uma requisição POST para http://localhost:8080/api/pedidos com o seguinte corpo (JSON):

```Json
    {
      "codigoCliente": "43212",
      "valorTotal": 105.50,
      "enderecoEntrega": "Av Tocantins, 213",
      "itens": [
        {
          "nomeProduto": "Ar condicionado",
          "codigoProduto": "6231"
        }
      ]
    }
```

---

## 🛠 Explicação da Solução

A solução foi estruturada para separar as responsabilidades de criação de pedidos e a geração de entregas, utilizando uma arquitetura baseada em eventos para promover o desacoplamento.

1.  **Domínio de Pedido (`pedido`)**: Responsável por receber as requisições via REST, validar os dados, persistir o pedido no banco de dados H2 e publicar um evento `PedidoCriadoEvent` no RabbitMQ.
2.  **Domínio de Entrega (`entrega`)**: Possui um Listener (`PedidoListener`) que consome as mensagens da fila do RabbitMQ. Ao receber um evento de pedido criado, ele gera automaticamente um registro de entrega correspondente.

Essa abordagem garante que o sistema de pedidos não precise esperar o processamento da entrega para responder ao usuário, além de permitir que ambos os módulos evoluam de forma independente.

---

## 📐 Decisões Técnicas

-   **Java 21 & Spring Boot**: Utilização das versões mais recentes para aproveitar recursos modernos de linguagem e performance.
-   **RabbitMQ**: Escolhido como broker de mensagens para garantir a comunicação assíncrona e confiável entre os módulos de pedido e entrega.
-   **H2 Database**: Utilizado como banco de dados em memória para facilitar a execução e testes do projeto sem necessidade de configurar um banco externo.
-   **Factory Pattern**: Aplicado nas classes de Factory para encapsular a lógica de conversão entre DTOs e Entidades, mantendo os serviços limpos.
-   **Tratamento Global de Exceções**: Centralizado na classe `OrderApiExceptionHandler` para garantir respostas de erro padronizadas e amigáveis ao cliente da API.
-   **Validação de Dados**: Uso de `jakarta.validation` para garantir a integridade dos dados de entrada (Bean Validation).


---

## 🔍 Pontos de Atenção

### O que foi implementado:
-   CRUD de pedidos (Criação, Listagem Geral e por ID).
-   Integração completa com RabbitMQ (Produtor e Consumidor).
-   Geração automática de entrega após criação de pedido.
-   Dockerização total da aplicação.
-   Validações de campos obrigatórios e formatos.

### Diferenciais Implementados

- **Pipeline de CI (Continuous Integration):** Configuração de um workflow no GitHub Actions que executa o build e a suíte de testes automaticamente a cada push/pull request.
- **Observabilidade (Spring Boot Actuator):** Endpoints de health check e métricas expostos (/actuator/health e /actuator/metrics).
- **Testes Automatizados:** Cobertura de testes unitários focados nas regras de negócio (Services) e no fluxo do Consumidor (Listener) utilizando JUnit 5 e Mockito.


### O que faria com mais tempo:
- **Busca de Pedidos com Elasticsearch (CQRS):** Atualmente a API possui um endpoint de busca que consulta diretamente o H2. Em um cenário produtivo, eu implementaria o padrão CQRS: o mesmo evento do RabbitMQ seria consumido para indexar o pedido no Elasticsearch, separando a base de gravação (relacional) da base de leitura (NoSQL) para buscas de alta performance.
- **Testes de Integração com Testcontainers:** Para validar o fluxo fim-a-fim subindo instâncias efêmeras reais do RabbitMQ e banco de dados durante os testes.
- **Resiliência (DLQ):** Configuração de uma Dead Letter Queue no RabbitMQ para capturar e tratar mensagens de eventos que falharem durante a conversão em entrega.
- **Persistência Real:** Migração do banco em memória H2 para um PostgreSQL utilizando Migrations (Flyway ou Liquibase).

---
