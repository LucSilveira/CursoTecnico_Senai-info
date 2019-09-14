
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

