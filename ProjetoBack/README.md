# ProjetoBack API

Este documento detalha os endpoints e recursos disponíveis na API do ProjetoBack. Trata-se de uma aplicação Spring Boot desenvolvida em Java.

## Endpoints da API

Abaixo estão os detalhes de todos os endpoints RESTful organizados por Controller.

### Admin (`/admin`)
Endpoints administrativos para gerenciamento geral.
* **`GET /admin/clientes`**: Retorna a lista de todos os clientes.
* **`POST /admin/clientes`**: Cria e adiciona um novo cliente.
* **`GET /admin/servicos`**: Retorna a lista de todos os serviços.
* **`POST /admin/servicos`**: Cria e adiciona um novo serviço.

### Agendamentos (`/agendamentos`)
Endpoints para gerenciamento de agendamentos.
* **`GET /agendamentos`**: Lista todos os agendamentos registrados no sistema.
* **`POST /agendamentos`**: Cria um novo agendamento. Exige os dados do cliente, profissional, serviço e data/hora.

### Clientes (`/clientes`)
Endpoints focados nas operações envolvendo os clientes.
* **`GET /clientes`**: Lista todos os clientes cadastrados.
* **`POST /clientes`**: Cadastra um novo cliente no sistema.

### Profissionais (`/profissionais`)
Endpoints para o gerenciamento de profissionais que executam os serviços.
* **`GET /profissionais`**: Lista todos os profissionais.
* **`POST /profissionais`**: Cadastra um novo profissional.

### Serviços (`/servicos`)
Endpoints para listar e gerenciar os serviços oferecidos.
* **`GET /servicos`**: Lista todos os serviços disponíveis.
* **`POST /servicos`**: Cria um novo serviço a ser ofertado.

### Endpoints de Interface / Views
Esses endpoints retornam páginas HTML renderizadas pela aplicação (Templates).
* **`GET /login`** (LoginController): Retorna a tela de login (`login.html`).
* **`GET /dashboard`** (DashboardController): Retorna o painel principal (`dashboard.html`).

## Estrutura do Projeto
A arquitetura do projeto segue o padrão MVC (Model-View-Controller) tradicional do Spring:
- **`controller`**: Camada que recebe e responde às requisições HTTP (definindo os Endpoints listados acima).
- **`Service`**: Camada de regras de negócio.
- **`repository`**: Camada de persistência (interface com o banco de dados via Spring Data JPA).
- **`model`**: Classes de entidade que representam as tabelas do banco de dados (Agendamento, Cliente, Profissional, Servico).

## Próximos Passos (TODOs Mapeados no Código)
* Refatorar os endpoints de `AgendamentoController` para receber e retornar DTOs (Data Transfer Objects) ao invés das entidades diretamente.
* Implementar a visualização de agendamentos por cliente específico no `AgendamentoController`.
