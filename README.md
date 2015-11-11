# GPA - Assuntos Estudantis


Gestão de Programas Acadêmicos - Módulo de Assuntos Estudantis

## Criando usuários no banco de dados para realização de testes
```
senha: teste1234 - sha256: "97e7ae26dd76600646701e97840d52b6ab7cb23cd03f8fa8e50640d84b52b5e1"
````

```sql
insert into pessoa (cpf) values ('76875083353');
insert into pessoa (cpf) values ('77014308300');
insert into pessoa (cpf) values ('79793053372');

insert into papel (nome) values ('ROLE_ALUNO');
insert into papel (nome) values ('ROLE_ADMIN');
insert into papel (nome) values ('ROLE_COORDENADOR');

insert into papel_pessoa (pessoa_id, papel_id) values ((select id from pessoa where cpf='76875083353' limit 1),(select id from papel where nome='ROLE_ALUNO' limit 1));
insert into papel_pessoa (pessoa_id, papel_id) values ((select id from pessoa where cpf='77014308300' limit 1),(select id from papel where nome='ROLE_ADMIN' limit 1));
insert into papel_pessoa (pessoa_id, papel_id) values ((select id from pessoa where cpf='79793053372' limit 1),(select id from papel where nome='ROLE_COORDENADOR' limit 1));

select * from pessoa;
select * from papel;
select * from papel_pessoa;
```

## Criando senha SHA256 no PostgreSQL
```sql
CREATE EXTENSION pgcrypto;
SELECT encode(digest('teste1234', 'sha256'), 'hex');
```
