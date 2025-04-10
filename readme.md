# Desafio Técnico - API de Consulta de Créditos

Este repositório contém a implementação de uma aplicação fullstack dividida em dois projetos:
- **`creditos-api`**: Back-end em Java com Spring Boot
- **`creditos-web`**: Front-end em Angular

---

## 📅 Objetivo
Desenvolver uma API RESTful para consulta de créditos constituídos e uma interface web para consumo dessa API.

---

## ⚙️ Tecnologias Utilizadas

### Back-end (`creditos-api`)
- Java 17
- Spring Boot 3.4.3
- Spring Data JPA + Hibernate
- PostgreSQL
- Flyway (versionamento de banco de dados)
- Kafka (mensageria)
- MapStruct (DTO mapping)
- SpringDoc OpenAPI (Swagger)
- JUnit, Mockito (testes)
- Docker

### Front-end (`creditos-web`)
- Angular 19
- Angular Material
- RxJS
- TypeScript 5
- Docker

### Infraestrutura
- Docker Compose
- Kafka UI (visualização de mensagens Kafka)

---

## 🚀 Como Rodar o Projeto

### 1. Clone o repositório:
```bash
git clone https://github.com/rafaelluciodeveloper/consulta-credito
cd consulta-credito
```

### 2. Suba todos os serviços com Docker Compose:
```bash
docker-compose up --build
```

Esse comando irá subir:
- Banco de dados PostgreSQL
- Kafka e Zookeeper
- Kafka UI
- API Spring Boot (`creditos-api`)
- Aplicativo Angular (`creditos-web`)


---

## 🌐 Acessos da Aplicação

### ✈️ Front-end Angular:
- http://localhost:4200

### ⚖️ Swagger da API:
- http://localhost:8080/swagger-ui/index.html

### 📈 Kafka UI:
- http://localhost:8085

---

## 💡 Funcionalidades
- Consulta por Número da NFS-e ou Número do Crédito
- Listagem dos créditos em tabela responsiva
- Mensageria via Kafka: notificação no Kafka quando uma consulta é realizada
- Swagger UI para exploração da API

---

## 🔍 Endpoints da API

### GET `/api/creditos/{numeroNfse}`
Consulta lista de créditos por NFS-e.

### GET `/api/creditos/credito/{numeroCredito}`
Consulta detalhes de um crédito por número do crédito.

---

## 🌍 Estrutura de Pastas

```
creditos-api
├── controller
├── service
├── repository
├── model
├── dto
├── mapper
└── config

creditos-web
├── src
│   └── app
│       ├── components
│       ├── services
│       └── models
│       └── pages        
```

---

## 📊 Testes
- Os testes unitários estão localizados no módulo `creditos-api`
- Utilizam `JUnit 5`, `Mockito` e `Spring Boot Test`

---

## 💼 Critérios de Avaliação Atendidos
- [x] Estrutura limpa e bem organizada (Clean Architecture)
- [x] API REST com Spring Boot 3
- [x] Banco de dados com Flyway + PostgreSQL
- [x] Front-end Angular responsivo
- [x] Integração com Kafka
- [x] Documentação via Swagger
- [x] Testes unitários com JUnit
- [x] Docker Compose com todos os serviços

---

## 🚪 Autor
**Rafael Lucio**  
LinkedIn: [https://www.linkedin.com/in/rafael-lucio-5b72a5103](https://www.linkedin.com/in/rafael-lucio-5b72a5103)  
Email: rafaellucio.developer@gmail.com

