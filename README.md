<div align="center">
  <h1>💇‍♀️ LuxoHair - Sistema de Agendamentos (ProjetoBack)</h1>
  <p>Uma aplicação Full Stack robusta para gestão completa de agendamentos, clientes, profissionais e serviços.</p>
  
  [![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=java&logoColor=white)]()
  [![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.5-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)]()
  [![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)]()
  [![Docker](https://img.shields.io/badge/Docker-2CA5E0?style=for-the-badge&logo=docker&logoColor=white)]()
</div>

---

## 📌 Sobre o Projeto

**LuxoHair** (também referenciado como ProjetoBack) é uma solução Full Stack moderna desenvolvida para otimizar o gerenciamento de operações em salões de beleza e clínicas de estética. O sistema fornece uma API REST escalável e segura no backend, em conjunto com um dashboard administrativo dinâmico e responsivo no frontend.

> **Status do Projeto:** Em Desenvolvimento 🚧

## ✨ Funcionalidades Principais

- **API RESTful Completa:** Endpoints padronizados para operações CRUD nas entidades do sistema.
- **Autenticação e Segurança:** Proteção de acesso ao dashboard administrativo implementada com **Spring Security**.
- **Gestão Integrada:** Controle e persistência de dados para Clientes, Profissionais, Serviços e Agendamentos.
- **Dashboard Administrativo:** Interface limpa gerada no servidor, trazendo visualização de métricas e tabelas de gestão.
- **Ambiente Containerizado:** Infraestrutura pronta com Docker e Docker Compose, garantindo um deploy rápido e idêntico em qualquer máquina.

## 🛠️ Stack Tecnológica

### Backend
- **Linguagem:** Java 21
- **Framework:** Spring Boot 3.2.5
- **Módulos Spring:** Spring Web, Spring Data JPA, Spring Security
- **Banco de Dados:** PostgreSQL
- **Build & Dependências:** Maven
- **Utilitários:** Lombok

### Frontend
- **Motor de Templates:** Thymeleaf
- **Estilização:** Tailwind CSS (via CDN)
- **Ícones:** FontAwesome (via CDN)

## 📂 Estrutura de Diretórios

A arquitetura do projeto aplica o padrão MVC e adota as melhores práticas do ecossistema Spring Boot:

```plaintext
LuxoHair/
├── src/main/java/com/example/ProjetoBack/
│   ├── config/          # Classes de configuração (ex: SecurityConfig)
│   ├── controller/      # Controladores REST e Web (Mapeamento de endpoints)
│   ├── model/           # Entidades de domínio JPA (Cliente, Agendamento, etc.)
│   └── repository/      # Interfaces de persistência com Spring Data JPA
├── src/main/resources/
│   ├── static/          # Arquivos estáticos (CSS, JS, Imagens)
│   ├── templates/       # Templates HTML renderizados pelo Thymeleaf
│   └── application.properties # Configurações da aplicação e conexão com banco
├── Dockerfile           # Instruções para construção da imagem da aplicação
├── docker-compose.yml   # Orquestração do contêiner da aplicação e do PostgreSQL
└── pom.xml              # Gerenciador de dependências Maven
```

## 🚀 Como Executar o Projeto

### 1. Executando com Docker (Recomendado)

Este é o método mais simples e rápido, pois o Docker cuida de todo o ambiente para você.

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/kauaxp77/ProjetoBack.git
    ```
2.  **Navegue até a pasta do projeto:**
    ```bash
    cd ProjetoBack
    ```
3.  **Suba os contêineres:**
    ```bash
    docker-compose up --build
    ```
    O comando `--build` garante que a imagem da sua aplicação seja construída com as últimas alterações.

4.  **Acesse a aplicação:**
    A interface web estará disponível em: `http://localhost:8082/login`
    A API REST estará disponível sob o mesmo endereço, nos seus respectivos endpoints.

### 2. Executando Localmente

Este método é útil para desenvolvimento e depuração na sua IDE.

1.  **Clone o repositório e navegue até a pasta do projeto** (passos 1 e 2 acima).

2.  **Configure o Banco de Dados:**
    *   Certifique-se de ter uma instância do PostgreSQL rodando localmente.
    *   Abra o arquivo `ProjetoBack/src/main/resources/application.properties` e ajuste as seguintes linhas com suas credenciais:
        ```properties
        spring.datasource.url=jdbc:postgresql://localhost:5432/agendamentos_db
        spring.datasource.username=postgres
        spring.datasource.password=admin # Ou a senha configurada para seu usuário 'postgres'
        ```

3.  **Compile e execute a aplicação com Maven:**
    ```bash
    mvn spring-boot:run
    ```
    Ou, execute a classe `ProjetoBackApplication.java` diretamente da sua IDE.

## 🔐 Acesso ao Sistema

Para acessar o dashboard administrativo, utilize as credenciais padrão em memória:

*   **Usuário:** `admin@test.com`
*   **Senha:** `password`

A página de login está disponível em `http://localhost:8081/login` (se executando localmente sem Docker) ou `http://localhost:8082/login` (se executando com Docker).

---

## 🧪 Testando os Endpoints da API com Postman

Para interagir com a API, você precisará de um cliente HTTP como o Postman.

### 1. Autenticação no Postman

Como a aplicação utiliza autenticação baseada em formulário (Spring Security), você precisa "logar" para obter os cookies de sessão que autorizarão suas requisições subsequentes.

1.  **Crie uma nova requisição no Postman.**
2.  **Método:** `POST`
3.  **URL:** `http://localhost:8081/perform_login` (ou `http://localhost:8082/perform_login` se usando Docker)
4.  **Aba "Body":** Selecione `x-www-form-urlencoded`.
5.  **Adicione os seguintes pares de chave-valor:**
    *   `username`: `admin@test.com`
    *   `password`: `password`
6.  **Clique em "Send".**

O Postman irá armazenar os cookies de sessão automaticamente. Agora você pode fazer requisições para os endpoints protegidos.

### 2. Endpoints Disponíveis

A seguir, a documentação detalhada de cada endpoint:

#### `AdminController` - Gerenciamento Administrativo
**Base URL:** `http://localhost:8081/admin` (ou `http://localhost:8082/admin` com Docker)

*   **`POST /admin/servicos` - Adicionar um novo serviço**
    *   **Descrição:** Cria um novo serviço no sistema.
    *   **Método:** `POST`
    *   **Body (raw, JSON):**
        ```json
        {
          "nome_servico": "Corte de Cabelo Masculino",
          "duracao_estimada": 45,
          "preco": 60.00
        }
        ```
    *   **Resposta:** Retorna o serviço criado com seu ID.

*   **`GET /admin/servicos` - Listar todos os serviços**
    *   **Descrição:** Retorna uma lista de todos os serviços cadastrados.
    *   **Método:** `GET`
    *   **Resposta:** Uma lista de objetos `Servico`.

*   **`POST /admin/clientes` - Adicionar um novo cliente**
    *   **Descrição:** Cria um novo cliente no sistema.
    *   **Método:** `POST`
    *   **Body (raw, JSON):**
        ```json
        {
          "nome": "Ana Paula",
          "telefone": "11987654321",
          "email": "ana.paula@example.com"
        }
        ```
    *   **Resposta:** Retorna o cliente criado com seu ID.

*   **`GET /admin/clientes` - Listar todos os clientes**
    *   **Descrição:** Retorna uma lista de todos os clientes cadastrados.
    *   **Método:** `GET`
    *   **Resposta:** Uma lista de objetos `Cliente`.

#### `AgendamentoController` - Gerenciamento de Agendamentos
**Base URL:** `http://localhost:8081/agendamentos` (ou `http://localhost:8082/agendamentos` com Docker)

*   **`POST /agendamentos` - Adicionar um novo agendamento**
    *   **Descrição:** Cria um novo agendamento. **Certifique-se de que os IDs de cliente, profissional e serviço já existam no banco de dados.**
    *   **Método:** `POST`
    *   **Body (raw, JSON):**
        ```json
        {
          "cliente": { "id_cliente": 1 },
          "profissional": { "id_profissional": 1 },
          "servico": { "id_servico": 1 },
          "data_hora": "2024-12-25T10:00:00",
          "status": "AGENDADO"
        }
        ```
    *   **Resposta:** Retorna o agendamento criado com seu ID.

*   **`GET /agendamentos` - Listar todos os agendamentos**
    *   **Descrição:** Retorna uma lista de todos os agendamentos.
    *   **Método:** `GET`
    *   **Resposta:** Uma lista de objetos `Agendamento`.

#### `ClienteController` - Operações de Cliente (Acesso Geral)
**Base URL:** `http://localhost:8081/clientes` (ou `http://localhost:8082/clientes` com Docker)

*   **`GET /clientes` - Listar todos os clientes**
    *   **Descrição:** Retorna uma lista de todos os clientes cadastrados.
    *   **Método:** `GET`
    *   **Resposta:** Uma lista de objetos `Cliente`.

*   **`POST /clientes` - Adicionar um novo cliente**
    *   **Descrição:** Cria um novo cliente no sistema.
    *   **Método:** `POST`
    *   **Body (raw, JSON):**
        ```json
        {
          "nome": "Pedro Henrique",
          "telefone": "11999991111",
          "email": "pedro.henrique@example.com"
        }
        ```
    *   **Resposta:** Retorna o cliente criado com seu ID.

#### `ProfissionalController` - Operações de Profissional (Acesso Geral)
**Base URL:** `http://localhost:8081/profissionais` (ou `http://localhost:8082/profissionais` com Docker)

*   **`GET /profissionais` - Listar todos os profissionais**
    *   **Descrição:** Retorna uma lista de todos os profissionais cadastrados.
    *   **Método:** `GET`
    *   **Resposta:** Uma lista de objetos `Profissional`.

*   **`POST /profissionais` - Adicionar um novo profissional**
    *   **Descrição:** Cria um novo profissional no sistema.
    *   **Método:** `POST`
    *   **Body (raw, JSON):**
        ```json
        {
          "nome": "Mariana Santos",
          "especialidade": "Manicure e Pedicure"
        }
        ```
    *   **Resposta:** Retorna o profissional criado com seu ID.

#### `ServicoController` - Operações de Serviço (Acesso Geral)
**Base URL:** `http://localhost:8081/servicos` (ou `http://localhost:8082/servicos` com Docker)

*   **`GET /servicos` - Listar todos os serviços**
    *   **Descrição:** Retorna uma lista de todos os serviços cadastrados.
    *   **Método:** `GET`
    *   **Resposta:** Uma lista de objetos `Servico`.

*   **`POST /servicos` - Adicionar um novo serviço**
    *   **Descrição:** Cria um novo serviço no sistema.
    *   **Método:** `POST`
    *   **Body (raw, JSON):**
        ```json
        {
          "nome_servico": "Coloração",
          "duracao_estimada": 90,
          "preco": 150.00
        }
        ```
    *   **Resposta:** Retorna o serviço criado com seu ID.

---

Espero que esta documentação detalhada ajude você e outros desenvolvedores a entender e testar o projeto! Se precisar de mais alguma coisa, é só avisar.
