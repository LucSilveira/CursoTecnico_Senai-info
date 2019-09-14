use matematica;

/************************************************************************/
/*						T.A.B.E.L.A.S								    */
/************************************************************************/

create table empresas(
	id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nome VARCHAR(30) NOT NULL,
    lucro DECIMAL NOT NULL
);

create table funcionarios(
	id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nome VARCHAR(30) NOT NULL,
    sexo ENUM('MASCULINO', 'FEMININO') NOT NULL,
    metas_cumpridas INT NOT NULL,
    cargo ENUM('GERENTE', 'ATENDENTE') NOT NULL,
    id_empresa BIGINT NOT NULL,
    
    foreign key(id_empresa) references empresa(id)
);

create table lanches(
	id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nome VARCHAR(20) NOT NULL UNIQUE,
    preco DECIMAL(12,2) NOT NULL,
    quantidade INT NOT NULL,
    id_empresa BIGINT NOT NULL,
    
    FOREIGN KEY(id_empresa) references empresas(id)
);

/********************************************************************************************************/
/*											I.N.S.E.R.T.S											    */
/********************************************************************************************************/

insert into empresas(nome, lucro) values ("Mc.Donald´s", '0.00'), ("Burger King", '0.00');

insert into funcionarios(nome, sexo, metas_cumpridas, cargo, id_empresa) values 
										("Lucas Silveira", 'MASCULINO', '0', 'GERENTE', 2),
                                        ("Victor Ravazio", 'MASCULINO', '0', 'ATENDENTE', 1);
                                        
insert into lanches(nome, preco, quantidade, id_empresa) values ("Big Mac", '0.00', '0', 1),
																("Whopper", '0.00', '0', 2);
                                                                
/***********************************************************************************************************/

/***********************************************************************************************************/