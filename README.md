# Transaction Statistics API

> Desafio técnico de programação - API REST para processamento de transações e cálculo de estatísticas em tempo real

## Sobre

Este projeto foi desenvolvido como exercício de estudo a partir de um desafio técnico do Itaú Unibanco, divulgado publicamente.

A implementação foi realizada acompanhando um conteúdo educacional do [Canal Javanauta no YouTube](https://www.youtube.com/watch?v=9xrx1pxZEGU), com o objetivo de compreender a proposta do desafio, aprender boas práticas de desenvolvimento backend e praticar a criação de APIs REST com Spring Boot.

---

## Referências

- [Desafio Original - Itaú Unibanco](https://github.com/rafaellins-itau/desafio-itau-vaga-99-junior)
- [Canal Javanauta](https://www.youtube.com/watch?v=9xrx1pxZEGU) - Resolvendo desafio técnico para desenvolvedor Júnior do Itaú

---

## Desafio
Criar uma API REST que:
- Recebe transações financeiras via POST
- Calcula estatísticas (count, sum, avg, min, max) dos últimos 60 segundos
- Armazena dados apenas em memória
- Validação de transações (sem valores negativos, sem datas futuras)

---

## Como Configurar o Projeto
1. Clone o Repositório

2. Compile o Projeto

```shell
  mvn clean install
```

3. Execute o Projeto

```shell
  mvn spring-boot:run
```

---

## Destaques da Implementação
- **Records**: Uso de Java Records para DTOs imutáveis
- **Documentação**: API documentada com [Scalar/OpenAPI]
- **Logs estruturados**: Sistema de logging para rastreabilidade
- **Armazenamento em memória**: Processamento de transações considerando apenas os últimos 60 segundos

---

## Tecnologias

- Java 21
- Spring Boot 4
- Springdoc OpenAPI / Scalar
- Maven
- Lombok

---

## Documentação da API
A API possui os seguintes endpoints conforme especificação do desafio:

### POST `/transacao`
Adiciona uma nova transação ao sistema.

**Request Body:**
```json
{
    "valor": 123.45,
    "dataHora": "2020-08-07T12:34:56.789-03:00"
}
```
**Validações:**
- Valor deve ser maior ou igual a zero
- Data/hora não pode ser no futuro
- Campos obrigatórios

**Respostas:**
- `201 Created` - Transação registrada com sucesso
- `422 Unprocessable Entity` - Validação falhou
- `400 Bad Request` - JSON inválido

---

### GET `/estatistica`
Retorna estatísticas das transações dos últimos 60 segundos.

**Query Parameters:**
- `intervaloBusca` (opcional, padrão: 60) - Intervalo em segundos para cálculo

**Response:**
```json
{
    "count": 10,
    "sum": 1234.56,
    "avg": 123.456,
    "min": 12.34,
    "max": 123.56
}
```
**Respostas:**
- `200 OK` - Estatísticas calculadas com sucesso

---

### DELETE `/transacao`
Remove todas as transações armazenadas em memória.

**Respostas:**
- `200 OK` - Transações deletadas com sucesso

---

## Documentação Interativa

A API está documentada com **Scalar/OpenAPI**. Após iniciar a aplicação, acesse:
```
http://localhost:8080/scalar.html
```

Ou visualize a especificação OpenAPI em:
```
http://localhost:8080/api-docs
```

> **Dica:** Use a interface Scalar para testar os endpoints diretamente pelo navegador!

---
## Contato
Caso queira conversar sobre o projeto ou trocar ideias sobre desenvolvimento backend, fique à vontade para entrar em contato:

- LinkedIn: [Jamilly Ferreira](https://www.linkedin.com/in/jamillyferreira)
- Gmail: [jamillyferreira039@gmail.com](mailto:jamillyferreira039@gmail.com)
