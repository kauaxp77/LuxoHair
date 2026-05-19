# 📅 ProjetoBack - Sistema de Agendamentos

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.5-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2CA5E0?style=for-the-badge&logo=docker&logoColor=white)

> **Status do Projeto:** Em Desenvolvimento 🚧

## 🎯 Sobre o Projeto
**ProjetoBack** é uma aplicação Full Stack para gerenciamento de agendamentos. O sistema foi construído com uma robusta API REST no backend e uma interface de usuário administrativa moderna e reativa no frontend, permitindo a gestão completa de clientes, profissionais, serviços e agendamentos.

## ✨ Funcionalidades Principais
* **✅ API RESTful Completa:** Endpoints para operações CRUD (Create, Read, Update, Delete) para todas as entidades do sistema.
* **✅ Autenticação Segura:** Sistema de login com Spring Security, protegendo o acesso ao dashboard administrativo.
* **✅ Dashboard Administrativo:** Uma interface web para visualização de métricas e gerenciamento de dados.
* **✅ Interface Moderna:** Frontend construído com Thymeleaf e estilizado com Tailwind CSS para uma experiência de usuário limpa e responsiva.
* **✅ Containerização:** Configuração completa com Docker e Docker Compose para um ambiente de desenvolvimento e produção padronizado e de fácil execução.

## 🛠️ Stack Tecnológica

A aplicação foi construída utilizando as seguintes tecnologias:

**Backend:**
* **Java 21:** Versão mais recente da linguagem Java.
* **Spring Boot 3.2.5:** Framework principal para o desenvolvimento da aplicação.
  * **Spring Web:** Para a criação de endpoints REST e controllers web.
  * **Spring Data JPA:** Para a persistência de dados e comunicação com o banco.
  * **Spring Security:** Para a implementação da camada de segurança e autenticação.
* **PostgreSQL:** Banco de dados relacional para armazenamento dos dados.
* **Maven:** Ferramenta para gerenciamento de dependências e build do projeto.
* **Lombok:** Para reduzir código boilerplate nas classes de modelo.

**Frontend:**
* **Thymeleaf:** Motor de templates para renderizar as páginas HTML no lado do servidor.
* **Tailwind CSS:** Framework de CSS utilitário para estilização rápida e moderna (via CDN).
* **FontAwesome:** Biblioteca de ícones (via CDN).

## 🚀 Como Executar o Projeto

Existem duas maneiras de executar o projeto: utilizando Docker (recomendado) ou localmente.

### Pré-requisitos
* Git
* Java 21
* Maven
* Docker e Docker Compose

### 1. Executando com Docker (Recomendado)
Este é o método mais simples e rápido, pois o Docker cuida de todo o ambiente para você.

1. Clone o repositório:
```bash
git clone [https://github.com/kauaxp77/ProjetoBack.git](https://github.com/kauaxp77/ProjetoBack.git)
```
2. Navegue até a pasta do projeto:
```Bash
cd ProjetoBack
```
3. Suba os contêineres
```Bash
docker-compose up --build
```
O comando --build garante que a imagem da sua aplicação seja construída com as últimas alterações.

4. Acesse a aplicação:

A interface web estará disponível em: http://localhost:8082/login

A API REST estará disponível sob o mesmo endereço, nos seus respectivos endpoints.
```

### 2. Executando Localmente
Este método é útil para desenvolvimento e depuração na sua IDE.

Clone o repositório e navegue até a pasta do projeto (passos 1 e 2 acima).

Configure o Banco de Dados:

Certifique-se de ter uma instância do PostgreSQL rodando localmente.

Abra o arquivo ProjetoBack/src/main/resources/application.properties e ajuste as seguintes linhas com suas credenciais:
```
spring.datasource.url=jdbc:postgresql://localhost:5432/nome_do_seu_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```
Compile e execute a aplicação com Maven:

```Bash
mvn spring-boot:run
```
Ou, execute a classe ProjetoBackApplication.java diretamente da sua IDE.

🔐 Acesso ao Sistema
Para acessar o dashboard administrativo, utilize as credenciais padrão em memória:

Usuário: admin@test.com

Senha: password
```
# 📂 Estrutura do Projeto
A estrutura de diretórios foi organizada para seguir as melhores práticas de projetos Spring Boot:

Plaintext
ProjetoBack/
├── .git/
├── src/main/java/com/example/ProjetoBack/
│   ├── config/          # Classes de configuração (SecurityConfig)
│   ├── controller/      # Controllers (REST e Web)
│   ├── model/           # Entidades JPA (Cliente, Agendamento, etc.)
│   └── repository/      # Interfaces Spring Data JPA
├── src/main/resources/
│   ├── static/          # Arquivos estáticos (CSS, JS, Imagens)
│   └── templates/       # Templates HTML (login.html, dashboard.html)
├── .gitignore
├── Dockerfile           # Define como construir a imagem da aplicação
├── docker-compose.yml   # Orquestra os contêineres da aplicação e do banco
├── pom.xml              # Arquivo de configuração do Maven
└── README.md            # Documentação do projeto
GET /clientes: Lista todos os clientes.

POST /clientes: Adiciona um novo cliente.

GET /profissionais: Lista todos os profissionais.

POST /profissionais: Adiciona um novo profissional.

GET /servicos: Lista todos os serviços.

POST /servicos: Adiciona um novo serviço.

GET /agendamentos: Lista todos os agendamentos.

POST /agendamentos: Adiciona um novo agendamento.
```
# 🔮 Próximos Passos
[ ] Conectar o dashboard aos dados reais do banco de dados.

[ ] Implementar as funcionalidades de Adicionar, Editar e Excluir na interface web.

[ ] Substituir o usuário em memória por um sistema de usuários no banco de dados.

[ ] Adicionar testes unitários e de integração.
## ✒️ Autores
Desenvolvido por Wendson Kauã, Daniel, Caterine, Gabriel e Felipe.
