# Task Manager API

[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)
[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://adoptium.net/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.2-brightgreen.svg)](https://spring.io/projects/spring-boot)

> Uma API REST moderna para gerenciamento de tarefas com autenticação JWT, construída com Spring Boot 4

## 📋 Sobre o Projeto

**Task Manager API** é uma aplicação backend desenvolvida com **Spring Boot 4.0** e **Java 21** que fornece um sistema completo de gerenciamento de tarefas pessoais. O sistema permite que usuários se registrem, autentiquem via JWT e gerenciem suas tarefas com suporte a filtros, paginação, prioridades e categorias.

A aplicação foi projetada seguindo princípios de **Clean Architecture** e **SOLID**, com separação clara entre camadas e foco em segurança e qualidade de código.

### ✨ Principais Features

- ✅ CRUD completo de **Tarefas** com título, descrição, status, prioridade, categoria e data de vencimento
- 🔐 Autenticação e registro de usuários com **JWT (JSON Web Token)**
- 🔍 Listagem de tarefas com **filtros** por status (`TODO`, `IN_PROGRESS`, `DONE`) e prioridade (`LOW`, `MEDIUM`, `HIGH`)
- 📄 **Paginação** nativa via Spring Data
- 📖 Documentação interativa com **Swagger/OpenAPI**
- 🐳 Containerização com **Docker** (multi-stage build)
- 🛡️ Tratamento global de exceções com respostas padronizadas
- 🔒 Senhas criptografadas com **BCrypt**
- 📊 **Spring Boot Actuator** para monitoramento
- 🏗️ **Spring Boot Admin** integrado

## 🛠 Tecnologias

| Tecnologia | Versão | Descrição |
|---|---|---|
| **Java** | 21 | Linguagem principal |
| **Spring Boot** | 4.0.2 | Framework backend |
| **Spring Data JPA** | — | Acesso a dados e ORM |
| **Spring Security** | — | Autenticação e autorização |
| **Spring Boot Actuator** | — | Monitoramento e health checks |
| **Spring Boot Admin** | 4.0.0 | Dashboard de administração |
| **PostgreSQL** | — | Banco de dados relacional |
| **JJWT** | 0.11.5 | Geração e validação de tokens JWT |
| **SpringDoc OpenAPI** | 2.8.4 | Documentação Swagger/OpenAPI |
| **Lombok** | — | Redução de boilerplate |
| **Docker** | — | Containerização |
| **JUnit 5 & Mockito** | — | Testes unitários e de integração |
| **Maven** | — | Gerenciamento de dependências e build |

## 🚀 Getting Started

### Pré-requisitos

- Java 21+ ([Download aqui](https://adoptium.net/))
- Docker Desktop ([Download aqui](https://www.docker.com/products/docker-desktop)) — para o PostgreSQL
- Maven 3.8+ (ou use o wrapper `mvnw` incluído)

### Instalação

1. **Clone o repositório**

```bash
git clone https://github.com/gilberto/task-manager-api.git
cd task-manager-api
```

2. **Inicie o PostgreSQL** (via Docker)

```bash
docker run -d \
  --name task-manager-db \
  -e POSTGRES_DB=task-manager-api \
  -e POSTGRES_USER=<seu_usuario> \
  -e POSTGRES_PASSWORD=<sua_senha> \
  -p 5432:5432 \
  postgres:15
```

3. **Execute a aplicação**

```bash
./mvnw spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`

### ⚙️ Variáveis de Ambiente

| Variável | Descrição | Obrigatória |
|---|---|:---:|
| `SPRING_PROFILES_ACTIVE` | Perfil ativo (`dev` ou `prod`) | Não (default: `dev`) |
| `JWT_SECRET` | Chave secreta para assinatura JWT (hex, 256 bits) | ✅ Sim |
| `SPRING_DATASOURCE_URL` | URL de conexão JDBC do PostgreSQL | ✅ Sim (em prod) |
| `SPRING_DATASOURCE_USERNAME` | Usuário do banco de dados | ✅ Sim (em prod) |
| `SPRING_DATASOURCE_PASSWORD` | Senha do banco de dados | ✅ Sim (em prod) |

#### 🔑 JWT Secret para testes locais

A variável `JWT_SECRET` é **obrigatória** para a aplicação funcionar. Para gerar um secret válido (256 bits, hex), execute no terminal:

```bash
openssl rand -hex 32
```

Isso irá gerar uma chave como, por exemplo: `a1b2c3d4e5f6...` (64 caracteres hexadecimais).

Em seguida, exporte o valor gerado como variável de ambiente **antes** de iniciar a aplicação:

```bash
export JWT_SECRET=<valor_gerado_pelo_comando_acima>
./mvnw spring-boot:run
```

O campo que consome esta variável está em `src/main/resources/application.yml`:

```yaml
jwt:
  secret: ${JWT_SECRET}
```

> ⚠️ **Importante:** Nunca versione o valor do `JWT_SECRET`. Em **produção**, defina-o como variável de ambiente do servidor ou do container.

### 🧪 Rodando os Testes

```bash
# Testes unitários
./mvnw test

# Testes completos (unitários + integração)
./mvnw verify
```

## 📖 Uso

### Endpoints Principais

#### Autenticação

| Método | Endpoint | Descrição | Auth |
|--------|----------|-----------|:----:|
| `POST` | `/api/auth/register` | Registrar novo usuário | ❌ |
| `POST` | `/api/auth/login` | Autenticar usuário | ❌ |

#### Tarefas

| Método | Endpoint | Descrição | Auth |
|--------|----------|-----------|:----:|
| `GET` | `/api/tasks` | Listar tarefas (com filtros e paginação) | ✅ |
| `POST` | `/api/tasks` | Criar nova tarefa | ✅ |
| `PUT` | `/api/tasks/{id}` | Atualizar tarefa | ✅ |
| `DELETE` | `/api/tasks/{id}` | Deletar tarefa | ✅ |

### Exemplos de Request

**Registrar Usuário**

```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Seu Nome",
    "email": "seu@email.com",
    "senha": "suaSenha"
  }'
```

**Login**

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "seu@email.com",
    "senha": "suaSenha"
  }'
```

**Resposta de Autenticação**

```json
{
  "token": "eyJhbGciOiJIUzI1NiIs...",
  "tokenType": "Bearer",
  "expiresIn": 3600000
}
```

**Criar Tarefa**

```bash
curl -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIs..." \
  -d '{
    "titulo": "Estudar Spring Boot",
    "descricao": "Completar módulo de Spring Security",
    "status": "TODO",
    "prioridade": "HIGH",
    "categoria": "Estudos",
    "dueDate": "2026-03-01"
  }'
```

**Listar Tarefas com Filtros**

```bash
# Todas as tarefas (paginado)
curl -H "Authorization: Bearer <token>" \
  "http://localhost:8080/api/tasks?page=0&size=10"

# Filtrar por status
curl -H "Authorization: Bearer <token>" \
  "http://localhost:8080/api/tasks?status=TODO"

# Filtrar por prioridade
curl -H "Authorization: Bearer <token>" \
  "http://localhost:8080/api/tasks?prioridade=HIGH"

# Filtrar por status e prioridade
curl -H "Authorization: Bearer <token>" \
  "http://localhost:8080/api/tasks?status=IN_PROGRESS&prioridade=MEDIUM"
```

### 📚 Documentação Interativa

Acesse a documentação Swagger em: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

API Docs JSON: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

## 🏗 Arquitetura

```
src/main/java/com/gilberto/task_manager_api/
├── config/             # Configurações (Security, JPA Auditing, OpenAPI)
├── controller/         # Controllers REST (Auth, Task)
├── dto/                # Data Transfer Objects
│   ├── auth/           #   ├── AuthResponse, LoginRequest, RegisterRequest
│   ├── task/           #   ├── TaskRequest, TaskResponse, TaskFilter
│   └── user/           #   └── DTOs de usuário
├── exception/          # Exception handler global e modelo de erro
├── model/              # Entidades JPA (User, Task)
│   └── enums/          #   └── TaskStatus, TaskPriority, UserRole
├── repository/         # Repositórios Spring Data JPA
├── security/           # JWT Filter, JWT Service, UserDetailsService
└── service/            # Lógica de negócio (TaskService, UserService)
```

### Padrões Utilizados

- **Repository Pattern** — Abstração de acesso a dados com Spring Data JPA
- **Service Layer** — Lógica de negócio isolada dos controllers
- **DTO Pattern** — Separação entre entidades e dados expostos na API
- **Builder Pattern** — Construção de objetos com Lombok `@Builder`
- **Stateless Authentication** — Sessões desabilitadas, autenticação 100% via JWT
- **Global Exception Handling** — Respostas de erro padronizadas com `@RestControllerAdvice`
- **Multi-stage Docker Build** — Imagem final leve usando apenas JRE

### Modelo de Dados

```
┌──────────────────────┐       ┌──────────────────────────────┐
│       users          │       │           tasks              │
├──────────────────────┤       ├──────────────────────────────┤
│ id (UUID, PK)        │       │ id (UUID, PK)                │
│ nome (VARCHAR)       │       │ titulo (VARCHAR, NOT NULL)   │
│ email (VARCHAR, UQ)  │◄──────│ user_id (UUID, FK)           │
│ senha (VARCHAR)      │  1:N  │ descricao (TEXT)             │
│ role (ENUM)          │       │ status (ENUM)                │
│ created_at (DATETIME)│       │ prioridade (ENUM)            │
└──────────────────────┘       │ categoria (VARCHAR)          │
                               │ due_date (DATE)              │
                               │ created_at (DATETIME)        │
                               └──────────────────────────────┘
```

## 🐳 Deploy com Docker

### Build e execução

```bash
# Build da imagem
docker build -t task-manager-api:latest .

# Executar o container
docker run -p 8080:8080 \
  -e SPRING_PROFILES_ACTIVE=prod \
  -e SPRING_DATASOURCE_URL=${SPRING_DATASOURCE_URL} \
  -e SPRING_DATASOURCE_USERNAME=${SPRING_DATASOURCE_USERNAME} \
  -e SPRING_DATASOURCE_PASSWORD=${SPRING_DATASOURCE_PASSWORD} \
  -e JWT_SECRET=${JWT_SECRET} \
  task-manager-api:latest
```


## 🤝 Como Contribuir

Contribuições são bem-vindas! Por favor:

1. Fork o projeto
2. Crie uma branch (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📝 Licença

Este projeto está sob a licença MIT. Veja [LICENSE](LICENSE) para mais detalhes.

## 📬 Contato

**Gilberto de Paiva Melo**

Project Link: [https://github.com/gilberto/task-manager-api](https://github.com/gilberto/task-manager-api)

---

⭐ Se este projeto foi útil, considere dar uma estrela!

