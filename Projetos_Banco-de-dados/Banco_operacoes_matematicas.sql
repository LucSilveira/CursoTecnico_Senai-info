drop database if exists matematica;
create database matematica;
use matematica;


/* Exemplo se multiplicacao*/
SELECT 3 * 9;

/* Exemplo de soma */ 
SELECT 3 + 9;

/* Exemplo de divisao */
SELECT 9 / 3;

/* Exemplo de subtracao */
SELECT 9 - 3;

/* exemplo de Mod(resto de divisao) */
SELECT 9 MOD 3;

/* Exemplo de operacoes */
SELECT 9 / 3 * 2 + 5;

/**********************************************************************/
		/*				EXEMPLO DE EXERCICIO			*/
/**********************************************************************/
CREATE TABLE Livros(
id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
livro VARCHAR(50) NOT NULL,
preco INT NOT NULL 
);

insert into Livros(livro, preco) values ( "DIARIO DE UM BANANA", '50'),
										( "jOGOS VORAZES", '30'),
                                        ( "Castle", '80');
                                        
select * from Livros;

select livro, preco * 3 as 'Precos' from Livros;

select livro, preco / 2 as 'precos com descontos' from Livros;

/**************************************************************************
***************************************************************************/

/* ARREDONDAR VALORES */
select CEILING(5 * 3);

/* VALOR DE PI */
select PI();

/* PEGA UM VALOR E ELEVA AO OUTRO */
select POW(2, 5);

/* RAIZ QUADRADA */
select SQRT(36);

/* SENO, COSSENO */
select SIN(45);
select COS(45);
select TAN(45);

/* HEXADECIMAL */
select HEX(120);
select BIN(120);

/* logaritmo */
select LOG(8);

/* somar quantidades */
select sum(preco) from Livros;

/* ******************************************************************** */
/* Creiando funcao */ 
create function test (x int, y int) returns int return x * y;
select test(2, 5) AS test;

/**************************************************************************
***************************************************************************
***************************************************************************/
create table empresa(
	id bigint primary key not null auto_increment,
    nome varchar(15) not null unique,
    lucro bigint not null,
    idFuncionario bigint not null,
    foreign key(idFuncionario) references funcionario(id),
    foreign key(lucro) references lanche(id)
);

create table funcionario(
	id bigint primary key not null auto_increment,
    nome varchar(30) not null,
    sexo enum('MASCULINO', 'FEMININO') not null,
    metasCompridas int not null,
    confeccoes int not null,
    cargo enum('GERENTE', 'ATENDENTE') not null
);

create table lanche(
	id bigint primary key not null auto_increment,
    nome varchar(20) not null unique,
    preco decimal(12,2) not null,
    quantidade int not null,
    idEmpresa bigint not null,
    
    foreign key(idEmpresa) references empresa(id)
);

insert into funcionario(nome, sexo, metasCompridas, confeccoes, cargo) values 
	("Lucas", 'MASCULINO', '5', '15', 'GERENTE'),
    ("Victor", 'FEMININO', '3', '10', 'ATENDENTE');
    
insert into empresa(nome, idFuncionario) values ("Mc", 1), ("BK", 2);

insert into lanche(nome, preco, quantidade) values ("1º", 12.20, 2), ("2º", 15.00, 3);

/*****************************************************************************/

select SUM(preco * quantidade) from lanche;

drop function if exists vendas2;
create function vendas2(q int, preco double) returns double return q * preco;
select vendas2(2, 12.20);

select nome,  preco * 2 as 'lanche' from lanche;

/*****************************************************************************/

select sum(quantidade * preco),l.nome from lanche as lc join 

# Função de agrupamento: SUM(somatória)
select sum(salario),s.nome from funcionario as f join setor as s on s.id = f.setor_id group by setor_id;