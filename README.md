# msgApp

## Ambiente

---
### Docker Compose
Executar o comando abaixo a partir da pasta *./docker/*

    docker-compose up -d

Ou levantar os serviços através do plugin: https://plugins.jetbrains.com/plugin/7724-docker


#### [pgAdmin (PostgreSQL)](https://www.pgadmin.org)
- Abrir interface web: http://localhost:5050/
- User: admin@admin.com
- Senha: root

Configurações iniciais:
Após login no pgAdmin, seguir os passos abaixo:

- Criar base de dados manualmente:
  - Abrir o pgAdmin
  - Abrir a aba SQL e executar o comando abaixo:
    ```sql
    create database msgAppdb;
    ```
- Abrir Servers > Create > Server
  - General:
    - Name: msgAppdb
  - Connection:
    - Host name/address: 127.0.0.1 / verify ip (docker inspect postgres)
    - Port: 5432
    - Maintenance database: postgres or msgAppdb
    - Username: postgres
    - Password: postgres

---

## Subir a aplicação


