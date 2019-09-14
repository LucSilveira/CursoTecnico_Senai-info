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
    quantidade int not null
);

insert into funcionario(nome, sexo, metasCompridas, confeccoes, cargo) values 
	("Lucas", 'MASCULINO', '5', '15', 'GERENTE'),
    ("Victor", 'FEMININO', '3', '10', 'ATENDENTE');
    
insert into empresa(nome, idFuncionario) values ("Mc", 1), ("BK", 2);

insert into lanche(nome, preco, quantidade) values ("1º", 12.20, 2), ("2º", 15.00, 3);

select SUM(preco * quantidade) from lanche;

drop function if exists vendas2;
create function vendas2(q int, preco double) returns double return q * preco;
select vendas2(2, 12.20);

select nome,  preco * 2 as 'lanche' from lanche;

/************************************************************************/