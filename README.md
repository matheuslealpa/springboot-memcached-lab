# Memcached + Spring Boot Lab

## O que é o Memcached?
Memcached é um sistema de cache distribuído que melhora o desempenho de aplicações web armazenando temporariamente objetos e dados frequentemente acessados na memória RAM. Ele reduz a carga no banco de dados, tornando as aplicações mais rápidas e escaláveis.

## Tecnologias Utilizadas
- **Spring Boot** (Java)
- **XMemcached** (Cliente Java para Memcached)
- **Docker & Docker Compose**

## Como Rodar o Projeto

### 1️⃣ **Inicializar o Memcached e a aplicação com Docker Compose**

```sh
docker-compose up -d --build
```

Isso iniciará um container para o Memcached e outro para a aplicação Spring Boot.

### Verificar se os containers estão rodando
```sh
docker ps
```

Deve haver um container com a imagem `memcached` e outro com sua aplicação Spring Boot.

### Testar os Endpoints REST com cURL

#### Salvar um valor no cache
```sh
curl -X POST "http://localhost:8080/api/cache?key=nome&value=MATHEUSLEAL" \
     -H "Content-Type: application/json"
```

#### Recuperar um valor do cache
```sh
curl -X GET "http://localhost:8080/api/cache/nome"
```

#### Deletar um valor do cache
```sh
curl -X DELETE "http://localhost:8080/api/cache/nome"
```
