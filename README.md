# GPA - Assuntos Estudantis


Gestão de Programas Acadêmicos - Módulo de Assuntos Estudantis

# Criando usuários no banco de dados para realização de testes
senha: teste1234 - sha256: "97e7ae26dd76600646701e97840d52b6ab7cb23cd03f8fa8e50640d84b52b5e1"
insert into pessoa (email, habilitado, login, password) values ('aluno@ufc.br',true, 'aluno', '97e7ae26dd76600646701e97840d52b6ab7cb23cd03f8fa8e50640d84b52b5e1');
insert into pessoa (email, habilitado, login, password) values ('admin@ufc.br',true, 'admin', '97e7ae26dd76600646701e97840d52b6ab7cb23cd03f8fa8e50640d84b52b5e1');
insert into pessoa (email, habilitado, login, password) values ('coord@ufc.br',true, 'coord', '97e7ae26dd76600646701e97840d52b6ab7cb23cd03f8fa8e50640d84b52b5e1');

insert into papel (nome) values ('ROLE_ALUNO');
insert into papel (nome) values ('ROLE_ADMIN');
insert into papel (nome) values ('ROLE_COORDENADOR');

insert into papel_pessoa (pessoa_id, papel_id) values ((select id from pessoa where login='aluno' limit 1),(select id from papel where nome='ROLE_ALUNO' limit 1));
insert into papel_pessoa (pessoa_id, papel_id) values ((select id from pessoa where login='admin' limit 1),(select id from papel where nome='ROLE_ADMIN' limit 1));
insert into papel_pessoa (pessoa_id, papel_id) values ((select id from pessoa where login='coord' limit 1),(select id from papel where nome='ROLE_COORDENADOR' limit 1));

select * from pessoa;
select * from papel;
select * from papel_pessoa;

# Criando senha SHA256 no PostgreSQL
```sql
CREATE EXTENSION pgcrypto;
SELECT encode(digest('teste1234', 'sha256'), 'hex');
```
