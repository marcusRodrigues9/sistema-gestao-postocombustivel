Sistema de Gestão de Posto de Combustíveis 🚀

Backend em Spring Boot 3 para gerenciar tipos de combustíveis, bombas e abastecimentos, com validações e documentação via Swagger/OpenAPI.

Funcionalidades

CRUD de Tipos de Combustível, Bombas e Abastecimentos

Cálculo automático do valor total do abastecimento

Validações com Bean Validation

Tratamento global de exceções

API documentada com Swagger

Tecnologias

Java 17, Spring Boot 3

Spring Data JPA, H2 Database

Swagger/OpenAPI

Maven

Como executar

Clone o repositório:

git clone <link-do-repo>
cd mtcombustiveis


Execute o projeto:

mvn spring-boot:run


Acesse a documentação da API via Swagger:

http://localhost:8080/swagger-ui/index.html

Endpoints principais

/tipoCombustivel → CRUD tipos de combustível

/bombaCombustivel → CRUD bombas

/abastecimentos → CRUD abastecimentos

💡 Pronto para integração com frontend em React ou deploy em nuvem.
