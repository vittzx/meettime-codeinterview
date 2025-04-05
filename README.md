
# MeetTime Code Interview

Desafio técnico para a construção de uma API RestFull com SpringBoot seguindo:
- Boas práticas de segurança
- Boas práticas de código
- Boas práticas de separação de responsabilidade, tratamento adequeado de erros e instruções detalhadas de como executar a aplicação

## Tecnologies
- Java 17
- SpringBoot (Lombok, Spring Dev Tools, Spring Starter Web)
- Maven 4.0

## Passo a passo executar a aplicação:
    1) Tenha o Java 17 (jdk-17) e o Maven (mvn) instalado e uma IDLE que seja compatível a execução do framework SpringBoot 
    (recomendação: Intellij)
    
    2)  De um [git clone https://github.com/vittzx/meettime-codeinterview]

    3) Após isso, abra a projeto em uma IDLE e carregue as dependencias do pom.xml pelo sua IDLE ou rodando

    4) Adiciona as variáveis de ambiente que está na sessão 'Variáveis de Ambiente' com seus respectivos valores na sua IDLE e execute o projeto

    5) Para obter o access token, de um GET no endpoint de access token e coloque a URL em algum navegador

    6) Faça o login e salve o {{access_token}} do response para utilizar em outros endpoints 



## Variáveis de Ambiente

Para rodar esse projeto, você vai precisar adicionar as seguintes variáveis de ambiente na sua IDLE


`HUBSPOT_BASE_URL`
`HUBSPOT_OAUTH_AUTHENTICATE_BASE_URL`
`HUBSPOT_ACCESS_TOKEN_ENDPOINT`
`HUBSPOT_AUTHORIZATION_ENDPOINT`
`HUBSPOT_CONTACT_ENDPOINT`
`HUBSPOT_CLIENT_APP`
`HUBSPOT_CLIENT_ID`
`HUBSPOT_REDIRECT_URI`
`HUBSPOT_SECRET_KEYS`
`SERVER_PORT`

## Documentação da API

#### Base URL

```http
  http://localhost:{{PORTA_DO_SERVIDOR}}
```

#### Endpoint de access token

```http
  GET /auth/v1/token
```

#### Response body: status `200`
```json
{
    "type": "SUCCESS",
    "response": {
        "url": "URL_DE_REDIRECIONAMENTO"
    }
}
```
#### Endpoint de Criação de Contato

```http
  GET /v1/contact
```

| Header   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `authorization`      | `string` | **Obrigatório**. Bearer token |


#### Request body:
```json
{
    "contacts": [
      {
        "email": "user6@example.com",
        "firstName": "Fiona",
        "lastName": "Brown",
        "phone": "1(555) 888-9999",
        "company": "NextGen",
        "website": "nextgen.com",
        "lifeCycleStage": "opportunity"
      }
    ]
}
```

#### Response body: status `201`
```json
{
    "type": "SUCCESS",
    "response": {
        "contacts": [
            {
                "id": "111611563379",
                "email": "user6@example.com",
                "fullName": "Fiona Brown",
                "company": "NextGen"
            }
        ]
    }
}
```

#### Cenários de Erro


#### Erro interno: status `500`
```json
{
    "type": "ERROR",
    "response": {
        "message":"Internal server error."
    }
}
```

#### Token de acesso inválido ou expirado: status `401`
```json
{
    "type": "ERROR",
    "response": {
        "message":"Invalid or expired access token to make request"
    }
}
```

#### Requisição inválida (campo): status `400`
```json
{
    "type": "ERROR",
    "response": {
        "message":"Field {0} is invalid."
    }
}
```

#### Requisição inválida (estrutura): status `400`
```json
{
    "type": "ERROR",
    "response": {
        "message":"Request body is invalid."
    }
}
```


## Decisões

- Este projeto foi feito em Arquitetura Hexagonal, para melhor exclusão de dependencias entre os Services, Adapters, Domínio na aplicação
- Escritura em do código em Ingles (Linguagem universal)
- Não foi utilizado o Mapper do org.mapstruct devido a erros internos da dependencias ao ler entidades
- A documentação da API foi feita no README pela praticidade e pelos poucos caso de uso, porem o ideal é ser feito no swagger;
- Retonar a URL no response body foi a opção escolhida, pois no redirecionamento via postman estava com erro, e na web estava indo corretamente

## Ideias

- Ideal adicionar uma validação no Request (validar accessToken, adicionar um listener/fila, validar regras de negócio ) com Decorator Pattern

- Ideal implementar um Enum para o campo "lifeCycleStage" e com Strategy Pattern

- Ideal implementar um banco de dados para salvar os eventos criados e outras informações

- Ideal implementar Spring Security para acessos a API (Meet time)


