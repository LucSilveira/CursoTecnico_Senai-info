create database curso;

use curso;

create table materia (id int not null auto_increment primary key,
			 nome varchar(60) not null,
             duracao varchar(10) not null,
             prof varchar(60),
             descricao varchar(255));
             
insert into materia (nome, duracao, prof, descricao) values
			("Banco de dados", "75", "Tsukamoto", "Curso de banco de dados com MySQL");
            
insert into materia (nome, duracao, prof, descricao) values
			("Programação para dispositivos móveis", "150", "Tsukamoto", "Programação para android");
            
insert into materia (nome, duracao, prof, descricao) values
			("Programação para Web", "150", "Felipe", "Programação utilizando Java Web");
            
insert into materia (nome, duracao, prof, descricao) values
			("JavaScript", "80", "Felipinho", "Programação em Javascript");
            
insert into materia (nome, duracao, prof, descricao) values
			("Jquery", "200", "Board", "Programação em Jqeury");

insert into materia (nome, duracao, prof, descricao) values
			("Inglês", "45", "João", "Quando você vê you speak inglish");

insert into materia (nome, duracao, prof, descricao) values
			("Css/Html", "250", "Felipinho Board", "Programação em Css e Html");

insert into materia (nome, duracao, prof, descricao) values
			("Java", "60", "Jorgito", "Programação em Java");
            
select * from materia;

select * from materia where id=2;
select * from materia where prof="felipe";

select * from materia where prof="tsukamoto" and duracao="75";

alter table materia add(sala varchar(4));
             
alter table materia drop sala;
alter table materia drop duracao;

alter table materia add duracao varchar(10) after nome;

delete from materia where id=13;

select * from materia order by id asc;

select * from materia where duracao <= 100 and prof = "joao";

select * from materia where duracao >= 100 or prof = "joao";

select count(*) from materia where prof = "Tsukamoto";

select * from materia where prof like "%board";

select nome, prof from materia;
select prof, nome from materia order by prof asc;

select * from materia;

update materia set duracao = "50" where id = 3;

set SQL_SAFE_UPDATES = 0;

/*
DDL = data definition language

Create database
Create table
Alter table
Drop table

DML = data manipulation language

Insert
Update
Delete
Select
*/
show databases;

create database if not exists curso;

select * from materia;

select max(id) from materia;

select min(id) from materia;

alter table materia modify duracao int;

select avg(duracao) from materia;

select sum(duracao) from materia;

select * from materia;

select * from materia where duracao >= 100 and not id <= 3;

select * from materia where not prof = "Tsukamoto";

select * from materia where duracao between 100 and 200;

select * from materia where duracao >= 100 and duracao <= 300;

select * from materia where prof not like "%o%";

/************************************************************************/

create database ex1;
use ex1;
create table clientes (cpf int not null primary key,
			 nome varchar(30) not null,
             dataNascimento varchar(50) not null,
             endereco varchar(50),
             cep varchar(30),
             bairro varchar(30),
             cidade varchar(30),
             uf varchar(30));
             
alter table clientes add(ultimaCompra varchar(50));

alter table clientes modify cpf varchar(255);

insert into clientes (cpf, nome, dataNascimento, endereco, cep, bairro, cidade, uf) values
			(04496332780, "João da Silva", "25/11/1969", "Rua Antônio Numes", "88045963",
            "Palmeiras", "Londrina", "PR");
            
insert into clientes (cpf, nome, dataNascimento, endereco, cep, bairro, cidade, uf) values
			(05485031490, "Ana Regina Fagundes", "21/09/1986", "Rua Palmeias Novas",
            "88078923", "Leblon" ,"Rio de Janeiro", "RJ");
            
insert into clientes (cpf, nome, dataNascimento, endereco, cep, bairro, cidade, uf) values
			(03350314905, "Fernando Soares", "05/03/1990", "Rua Palmeias Novas", "88048917",
            "Copacabana", "Rio de Janeiro", "RJ");
            
select * from clientes where cidade like "Rio de Janeiro";

delete from clientes where cidade ="Londrina";

select * from clientes;

