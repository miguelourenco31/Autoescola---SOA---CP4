# 🚗 AutoEscola3ESR — CP4 SOA

API REST desenvolvida para o **Checkpoint 4 da disciplina de SOA / Web Services**.

O projeto representa o sistema de uma autoescola, permitindo o gerenciamento de alunos, instrutores, usuários e instruções, com autenticação via JWT e aplicação das regras de negócio para agendamento e cancelamento de aulas.

## 👨‍💻 Integrantes

- Lorenzzo Vendruscolo Dias — RM558305
- Gabriel Martins Vannucci — RM556883
- Miguel Marques Lourenço — RM555426
- Pedro Henrique Ferronato — RM554757

## 🛠️ Tecnologias

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security
- JWT
- Bean Validation
- MySQL
- Flyway
- Maven

## 🔐 Autenticação e autorização

A API utiliza autenticação baseada em **JWT (JSON Web Token)**.

O login é realizado através do endpoint:

```http
POST /login
```

Exemplo:

```json
{
  "login": "admin",
  "senha": "123456"
}
```

Após a autenticação, o token JWT retornado deve ser enviado nas requisições protegidas:

```http
Authorization: Bearer <token>
```

O sistema possui dois perfis:

- `ADMIN`
- `USER`

As operações administrativas de gerenciamento de usuários são restritas ao perfil `ADMIN`.

## 👤 Usuários

O sistema permite:

- Cadastrar usuários;
- Listar usuários;
- Atualizar login e perfil;
- Excluir usuários;
- Alterar a própria senha;
- Armazenar senhas de forma criptografada utilizando BCrypt.

Principais endpoints:

```http
POST   /usuarios
GET    /usuarios
PUT    /usuarios
DELETE /usuarios/{id}
PUT    /usuarios/senha
```

## 🎓 Alunos

O sistema permite o cadastro de alunos no banco de dados.

```http
POST /alunos
```

O cadastro contém informações como:

- Nome;
- E-mail;
- Telefone;
- CPF;
- Endereço;
- Status ativo.

## 🚘 Instrutores

A API possui gerenciamento de instrutores e controle de acesso aos endpoints de acordo com o perfil do usuário.

As especialidades disponíveis são:

- `CARROS`
- `MOTOS`
- `CAMINHOES`
- `VANS`

## 📅 Agendamento de instruções

O agendamento é realizado através de:

```http
POST /instrucoes
```

Exemplo:

```json
{
  "id_aluno": 1,
  "id_instrutor": 1,
  "especialidade": "CARROS",
  "data_hora": "16/09/2026 - 10:00"
}
```

O sistema aplica regras de negócio para os agendamentos, incluindo:

- Aulas permitidas de segunda-feira a sábado;
- Horário permitido entre 06:00 e 21:00;
- Duração de 1 hora;
- Agendamento com antecedência mínima de 30 minutos;
- Alunos inativos não podem realizar agendamentos;
- Instrutores inativos não podem receber agendamentos;
- Máximo de **2 instruções por aluno no mesmo dia**;
- Um instrutor não pode possuir conflito de horário;
- O instrutor pode ser informado ou selecionado entre os disponíveis.

## ❌ Cancelamento de instruções

Uma instrução pode ser cancelada através de:

```http
PUT /instrucoes/cancelamento
```

Exemplo:

```json
{
  "idInstrucao": 1,
  "motivo": "ALUNO_DESISTIU"
}
```

Motivos disponíveis:

- `ALUNO_DESISTIU`
- `INSTRUTOR_CANCELOU`
- `OUTROS`

### Regras de cancelamento

- O motivo do cancelamento é obrigatório;
- O cancelamento deve ocorrer com no mínimo **24 horas de antecedência**;
- Uma instrução já cancelada não pode ser cancelada novamente;
- Instruções canceladas deixam de bloquear o horário do instrutor;
- Instruções canceladas não são consideradas no limite diário do aluno.

## 🗄️ Banco de dados

O projeto utiliza **MySQL** para persistência dos dados e **Flyway** para controle das migrations do banco.

Banco utilizado:

```text
autoescola3esr
```

As migrations ficam disponíveis em:

```text
src/main/resources/db/migration
```

## ▶️ Como executar

### Pré-requisitos

- Java instalado;
- MySQL em execução;
- Banco `autoescola3esr` criado.

Configure o acesso ao banco em:

```text
src/main/resources/application.properties
```

Depois execute, na raiz do projeto:

### Windows

```powershell
.\mvnw.cmd spring-boot:run
```

A aplicação será disponibilizada em:

```text
http://localhost:8081
```

## 🧪 Testes

Para executar os testes do projeto:

```powershell
.\mvnw.cmd test
```

## 📚 Projeto acadêmico

Projeto desenvolvido para fins acadêmicos, como entrega do **Checkpoint 4 de SOA / Web Services**.