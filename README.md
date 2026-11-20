<p align="center">
  <img src="docs/banner.png" alt="Liso Luxo Hair - Sistema de agendamentos" width="100%">
</p>

<h1 align="center">💇‍♀️ Liso Luxo Hair — Sistema de Agendamentos</h1>

<p align="center">
  Back-end em <b>Spring Boot</b> para o salão <b>Liso Luxo Hair</b>: cadastro de clientes, profissionais e serviços,
  agendamentos e telas por perfil (cliente, profissional e gerente), com autenticação via <b>JWT</b>.
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 21">
  <img src="https://img.shields.io/badge/Spring%20Boot-3.2-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" alt="Spring Boot 3.2">
  <img src="https://img.shields.io/badge/Spring%20Security-JWT-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white" alt="Spring Security + JWT">
  <img src="https://img.shields.io/badge/PostgreSQL-15-4169E1?style=for-the-badge&logo=postgresql&logoColor=white" alt="PostgreSQL">
  <img src="https://img.shields.io/badge/Thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white" alt="Thymeleaf">
  <img src="https://img.shields.io/badge/Docker-Compose-2496ED?style=for-the-badge&logo=docker&logoColor=white" alt="Docker Compose">
</p>

<p align="center">
  <a href="#-sobre-o-projeto">Sobre</a> •
  <a href="#-funcionalidades">Funcionalidades</a> •
  <a href="#-endpoints-da-api">Endpoints</a> •
  <a href="#-regras-de-negócio">Regras de negócio</a> •
  <a href="#-como-executar">Como executar</a> •
  <a href="#-estrutura-do-projeto">Estrutura</a>
</p>

---

## 📌 Sobre o projeto

O objetivo é substituir a agenda de papel do salão por um sistema de agendamentos online: a cliente escolhe o
serviço e o horário, a profissional vê a sua agenda e a gerência acompanha todos os atendimentos e cadastra a equipe.

A aplicação segue o padrão em camadas do Spring (`controller` → `Service` → `repository` → `model`), usa
**Spring Data JPA** com **PostgreSQL** e tem as telas em HTML (Thymeleaf + Tailwind via CDN) servidas pela própria
aplicação.

> 🚧 **Projeto em desenvolvimento.** Hoje o `mvn package` **não compila**: algumas classes e templates ainda têm
> **marcadores de conflito de merge** (`<<<<<<<`, `=======`, `>>>>>>>`) e a biblioteca de JWT usada pelo
> `TokenService` (`com.auth0:java-jwt`) ainda não foi declarada no `pom.xml`. As regras de negócio do documento
> [`docs/Regras-de-negocio.docx`](docs/Regras-de-negocio.docx) são o planejamento do sistema e ainda não estão
> implementadas no código.

## ✨ Funcionalidades

| | Recurso | Descrição |
|---|---|---|
| 👤 | **Clientes** | Cadastro e listagem de clientes (nome, telefone e e-mail) |
| 💇 | **Profissionais** | Cadastro e listagem de profissionais e suas especialidades |
| ✂️ | **Serviços** | Cadastro de serviços com duração estimada e preço |
| 📅 | **Agendamentos** | Liga cliente, profissional, serviço, data/hora e status |
| 🔐 | **Autenticação JWT** | Tokens assinados com HMAC256 e validade de 2 horas (`TokenService`) |
| 🧑‍💼 | **Perfis de acesso** | `ADMIN`, `FUNC` (profissional) e `USER` (cliente) |
| 🖥️ | **Telas por perfil** | Página inicial, login, área da cliente, área técnica, recepção e painel do gerente |
| 🐳 | **Docker** | `Dockerfile` multi-stage e `docker-compose.yml` com PostgreSQL |

## 🔌 Endpoints da API

### Admin (`/admin`)
* **`GET /admin/clientes`**: lista todos os clientes.
* **`POST /admin/clientes`**: cadastra um novo cliente.
* **`GET /admin/servicos`**: lista todos os serviços.
* **`POST /admin/servicos`**: cadastra um novo serviço.

### Gerente (`/gerente`)
* **`POST /gerente/funcionarios`**: cadastra um usuário da equipe com a role enviada (`FUNC` por padrão).
* **`GET /gerente/funcionarios`**: lista os usuários com role `FUNC`.
* **`GET /gerente/agendamentos`**: lista todos os agendamentos (visão do gerente).

### Agendamentos (`/agendamentos`)
* **`GET /agendamentos`**: lista todos os agendamentos.
* **`POST /agendamentos`**: cria um agendamento (cliente, profissional, serviço e data/hora).

### Clientes, profissionais e serviços
* **`GET` / `POST /clientes`**: lista e cadastra clientes.
* **`GET` / `POST /profissionais`**: lista e cadastra profissionais.
* **`GET` / `POST /servicos`**: lista e cadastra serviços.

### Telas (views)

| Rota | Template |
|---|---|
| `/` | `index.html` (página inicial do salão) |
| `/login` | `login.html` |
| `/cliente` | `cliente.html` (meus agendamentos) |
| `/funcionario` | `dashboard-funcionaria.html` (área técnica) |
| `/gerente` | `dashboard-gerente.html` (painel do gerente) |
| `/dashboard` | `dashboard.html` |

### Próximos passos (TODOs mapeados no código)
* Fazer o `AgendamentoController` receber e devolver DTOs em vez das entidades.
* Permitir que a cliente veja apenas os próprios agendamentos.

## 📋 Regras de negócio

O documento [`docs/Regras-de-negocio.docx`](docs/Regras-de-negocio.docx) define as regras planejadas para a agenda:

- **Disponibilidade:** sem choque de horários para a mesma profissional, agenda aberta para os próximos 60 dias e
  1 hora de intervalo bloqueada depois de atendimentos de 4 horas.
- **Status do agendamento:** Pendente, Confirmado, Cancelado e No-Show (a falta gera taxa de 20%).
- **Cancelamento:** gratuito até 5 horas antes, multa sobre o sinal em cancelamentos tardios e no máximo
  2 reagendamentos por mês.
- **Notificações:** confirmação imediata e lembretes 24h e 2h antes do horário.

## 🚀 Como executar

> ⚠️ Estes são os comandos indicados pelos arquivos do projeto (`Dockerfile`, `docker-compose.yml` e
> `application.properties`). Eles **não foram executados com sucesso** nesta revisão, porque a compilação falha
> pelos motivos citados em [Sobre o projeto](#-sobre-o-projeto).

**Pré-requisitos:** JDK 21 e Maven (ou o `mvnw` incluso) com um PostgreSQL, ou então o Docker.

### Com Docker Compose (aplicação + PostgreSQL)

```bash
git clone https://github.com/kauaxp77/LuxoHair.git
cd LuxoHair
docker compose up -d --build
```

A aplicação fica em `http://localhost:8082` e o PostgreSQL na porta `5432` (banco `agendamentos_db`).

### Localmente

1. Crie o banco `agendamentos_db` no PostgreSQL e ajuste usuário e senha em
   `src/main/resources/application.properties`.
2. Defina a propriedade `api.security.token.secret`, usada pelo `TokenService` para assinar os tokens JWT
   (por exemplo, com a variável de ambiente `API_SECURITY_TOKEN_SECRET`).
3. Rode:

```bash
./mvnw spring-boot:run
```

A aplicação sobe na porta `8081`. As tabelas são criadas pelo Hibernate (`ddl-auto=update`).

## 📁 Estrutura do projeto

```
LuxoHair/
├── src/main/java/com/example/ProjetoBack/
│   ├── controller/          # Endpoints REST e controllers das telas
│   ├── Service/             # Regras de negócio
│   ├── repository/          # Spring Data JPA
│   ├── model/               # Entidades: Agendamento, Cliente, Profissional, Servico e User
│   ├── dto/                 # DTOs de requisição e resposta
│   ├── infra/security/      # JWT: TokenService, SecurityFilter e SecurityConfiguration
│   └── config/              # Configurações web e de segurança
├── src/main/resources/
│   ├── templates/           # Telas HTML (Thymeleaf)
│   ├── static/              # JavaScript das telas
│   └── application.properties
├── docs/
│   ├── Regras-de-negocio.docx  # Regras de negócio planejadas
│   └── banner.png
├── Dockerfile               # Build multi-stage (Maven + JDK 21)
├── docker-compose.yml       # Aplicação + PostgreSQL 15
└── pom.xml
```

## 🛠️ Tecnologias

- **Java 21** e **Spring Boot 3.2** (Web, Data JPA, Validation, Security, Thymeleaf)
- **Spring Security** com **JWT**
- **PostgreSQL 15**
- **Thymeleaf** e **Tailwind CSS** (CDN) nas telas
- **Lombok**
- **Docker** e **Docker Compose**

## 👨‍💻 Autor

Desenvolvido por **[kauaxp77](https://github.com/kauaxp77)**

<p align="center">⭐ Se curtiu o projeto, deixe uma estrela!</p>
