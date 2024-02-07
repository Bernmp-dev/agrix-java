<br />

[![linkedin]][linkedin-url]
[![github]][github-url]
![email]

# Visão Geral

`Harvest Hub` é um sistema de gerenciamento agrícola projetado para facilitar o controle de fazendas, plantações e o uso de fertilizantes. Ele permite aos usuários gerenciar fazendas, registrar detalhes de cultivos, associar fertilizantes a cultivos e gerenciar usuários.

# Acesso à API e Swagger UI

A API do HarvestHub está atualmente ativa e pode ser acessada através do seguinte endereço: [https://harvesthub.fly.dev](https://harvesthub.fly.dev). Além disso, o Swagger UI está disponível para explorar e interagir com a documentação da API através do caminho `/swagger-ui/index.html` a partir do endereço fornecido.

Ao acessar a rota `/swagger-ui/index.html` é necessário primeiramente realizar o cadastro de um novo usuário, após isso realize o login e copie o token JWT que será recebido na resposta, esse mesmo token deve ser inserido no campo `Authorize 🔒` no canto superior direito da página, após isso todos acessos estarão liberados, usuário registrado, logado e autenticado com sucesso.

# Tecnologias e Ferramentas

<details>

O projeto `harvestHub` utiliza uma série de tecnologias e ferramentas modernas para garantir eficiência, segurança e facilidade de manutenção:

- **Java 17**: Linguagem de programação utilizada para o desenvolvimento do backend, aproveitando suas características robustas e orientadas a objeto.
- **Spring Boot**: Framework escolhido para facilitar a configuração e o desenvolvimento da aplicação, proporcionando recursos como injeção de dependência, endpoints REST e mais.
- **PostgreSQL**: Sistema de gerenciamento de banco de dados relacional escolhido para armazenar os dados da aplicação de forma eficiente e segura.
- **Hibernate**: Ferramenta de mapeamento objeto-relacional para Java, utilizada para implementar a camada de persistência, facilitando o acesso e a manipulação dos dados no banco de dados.
- **Maven**: Ferramenta de automação de build e gerenciamento de dependências, utilizada para construir o projeto e gerenciar suas bibliotecas.
- **Docker**: Plataforma utilizada para empacotar a aplicação e suas dependências em um container, simplificando a implantação e a execução em diferentes ambientes.
- **Spring Security**: Framework utilizado para implementar recursos de segurança na aplicação, incluindo autenticação, autorização e proteção contra vulnerabilidades comuns.
- **JWT (JSON Web Tokens)**: Padrão utilizado para a criação de tokens de acesso que permitem a autenticação e transmissão segura de informações entre partes da aplicação.
- **Swagger**: Ferramenta utilizada para documentar a API REST, fornecendo uma interface de usuário interativa para explorar e testar os endpoints da API.
</details>


# Configuração

<details>

O projeto utiliza perfis do Spring (`application-cloud.properties` e `application-local.properties`) para definir configurações específicas do ambiente. É possível alternar entre esses perfis usando a propriedade `spring.profiles.active`.
</details>


# Estrutura do Projeto
<details>

O projeto `harvestHub` é organizado em pacotes seguindo a arquitetura MVC, divididos em:

- **`controllers`**: Controladores REST para manipulação das requisições HTTP.
- **`services`**: Camada de serviço contendo a lógica de negócios.
- **`repositories`**: Interfaces para a comunicação com o banco de dados.
- **`entities`**: Classes de entidade representando tabelas do banco de dados.
- **`dto`**: Objetos de Transferência de Dados usados para transferir informações entre subcamadas.
- **`exceptions`**: Exceções customizadas para tratamento de erros específicos.
- **`util`**: Classes utilitárias, incluindo configurações de segurança e CORS.

</details>


# Documentação dos Endpoints
  
<details>
<summary><strong>Farm Controller Endpoints</strong></summary>
  
1. **Criar Fazenda**
   - **Endpoint**: `POST /`
   - **Descrição**: Cria uma nova fazenda.
   - **Corpo da Requisição**:
     ```json
     {
       "name": "Fazenda do João",
       "size": 100.0
     }
     ```

2. **Listar Todas as Fazendas**
   - **Endpoint**: `GET /`
   - **Descrição**: Retorna todas as fazendas cadastradas.

3. **Buscar Fazenda por ID**
   - **Endpoint**: `GET /{id}`
   - **Descrição**: Retorna detalhes de uma fazenda específica.
   - **Parâmetros de Path**:
     - `id`: ID da fazenda.

4. **Excluir Todas as Fazendas**
   - **Endpoint**: `DELETE /`
   - **Descrição**: Remove todas as fazendas cadastradas.
</details>

<details>
<summary><strong>Fertilizer Controller Endpoints</strong></summary>

1. **Listar Todos os Fertilizantes**
   - **Endpoint**: `GET /`
   - **Descrição**: Retorna todos os fertilizantes cadastrados.

2. **Buscar Fertilizante por ID**
   - **Endpoint**: `GET /{id}`
   - **Descrição**: Retorna detalhes de um fertilizante específico.
   - **Parâmetros de Path**:
     - `id`: ID do fertilizante.

3. **Buscar Fertilizantes por ID da Plantação**
   - **Endpoint**: `GET /crops/{cropId}`
   - **Descrição**: Retorna todos os fertilizantes associados a uma plantação específica.
   - **Parâmetros de Path**:
     - `cropId`: ID da plantação.

4. **Criar Fertilizante**
   - **Endpoint**: `POST /`
   - **Descrição**: Cria um novo fertilizante.
   - **Corpo da Requisição**:
     ```json
     {
       "name": "Adubo 1",
       "brand": "Marca 1",
       "composition": "Composição 1"
     }
     ```

5. **Associar Fertilizante à Plantação**
   - **Endpoint**: `POST /{fertilizerId}/crops/{cropId}/`
   - **Descrição**: Associa um fertilizante a uma plantação.
   - **Parâmetros de Path**:
     - `cropId`: ID da plantação.
     - `fertilizerId`: ID do fertilizante.

</details>

<details>
<summary><strong>Person Controller Endpoints</strong></summary>

1. **Registrar Novo Usuário**
   - **Endpoint**: `POST /user`
   - **Descrição**: Registra um novo usuário no sistema.
   - **Corpo da Requisição**:
     ```json
     {
       "username": "novo_usuario",
       "password": "senha_segura",
       "role": "ADMIN"
     }
     ```

2. **Login do Usuário**
   - **Endpoint**: `POST /user/login`
   - **Descrição**: Valida o login do usuário e retorna um token de autenticação.
   - **Corpo da Requisição**:
     ```json
     {
       "username": "novo_usuario",
       "password": "senha_segura"
     }
     ```     

3. **Listar Todos os Usuários**
   - **Endpoint**: `GET /user`
   - **Descrição**: Retorna todos os usuários cadastrados no sistema.

</details>

</details>


<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->

[linkedin]: https://img.shields.io/badge/-LinkedIn-35495E.svg?style=for-the-badge&logo=linkedin

[linkedin-url]: https://https://www.linkedin.com/in/bernardo-marquesp/

[email]: https://img.shields.io/badge/bernardomp.dev@gmail.com-35495E?style=for-the-badge&logo=gmail&logoColor=white

[github]: https://img.shields.io/badge/GitHub-35495E?style=for-the-badge&logo=github&logoColor=white

[github-url]: https://github.com/Bernmp-dev
