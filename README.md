# ProjetoBack

Um sistema back-end desenvolvido em **Java 21** utilizando o framework **Spring Boot**. O projeto está configurado para fornecer serviços web (SOAP/Web Services), acessar dados via JPA com banco de dados PostgreSQL e inclui validações de dados.

## 🛠️ Tecnologias e Ferramentas Utilizadas

Este projeto foi construído com as seguintes tecnologias e dependências:

* **[Java 21](https://jdk.java.net/21/):** Linguagem principal do projeto.
* **[Spring Boot 4.0.6](https://spring.io/projects/spring-boot):** Framework base da aplicação.
* **[Spring Data JPA](https://spring.io/projects/spring-data-jpa):** Para persistência de dados e mapeamento objeto-relacional (ORM).
* **[Spring Web Services](https://docs.spring.io/spring-ws/sites/2.0/reference/html/):** Para criação e consumo de serviços web baseados em SOAP.
* **[Spring Boot Validation](https://spring.io/guides/gs/validating-form-input/):** Para validação de dados de entrada.
* **[PostgreSQL](https://www.postgresql.org/):** Banco de dados relacional configurado como dependência de tempo de execução (`runtime`).
* **[Lombok](https://projectlombok.org/):** Biblioteca para reduzir o código boilerplate (getters, setters, construtores, etc).
* **[Maven](https://maven.apache.org/):** Gerenciador de dependências e build do projeto (Wrapper incluso na versão 3.3.4).

## ⚙️ Pré-requisitos

Antes de executar o projeto localmente, certifique-se de ter instalado em sua máquina:

* **JDK 21**
* **PostgreSQL** rodando localmente ou em nuvem (necessário configurar as credenciais no `application.properties`).
* *Opcional:* O Maven não precisa estar instalado globalmente, pois o projeto já inclui o Maven Wrapper (`mvnw` e `mvnw.cmd`).

## 🚀 Como Executar o Projeto

1. **Clone o repositório:**
   ```bash
   git clone [https://github.com/seu-usuario/ProjetoBack.git](https://github.com/seu-usuario/ProjetoBack.git)
   cd ProjetoBack
   
2. **Configure o Banco de Dados:**
Abra o arquivo src/main/resources/application.properties e adicione as configurações de conexão com o seu banco PostgreSQL (URL, usuário e senha).
Exemplo:

Properties
spring.application.name=ProjetoBack
spring.datasource.url=jdbc:postgresql://localhost:5432/nome_do_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update

3. **Execute a aplicação:**
Utilize o Maven Wrapper que já vem no projeto para baixar as dependências e iniciar o servidor.

No Windows:

DOS
mvnw.cmd spring-boot:run
No Linux / macOS:

Bash
./mvnw spring-boot:run
## 📁 Estrutura do Projeto

* **src/main/java/com/example/ProjetoBack/: Contém o código-fonte principal da aplicação Java.

* **src/main/resources/: Contém os arquivos de configuração, como o application.properties.

* **src/test/: Diretório reservado para os testes unitários e de integração.

* **pom.xml: Arquivo de configuração do Maven, onde estão listadas todas as dependências e plugins.

* **mvnw e mvnw.cmd: Scripts do Maven Wrapper para execução padronizada sem precisar instalar o Maven localmente.

✒️ Autores
Desenvolvido por Wendson Kauã, Daniel, Caterine, Gabriel e Felipe.
