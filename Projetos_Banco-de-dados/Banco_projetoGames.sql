create database tecnow_17 default charset utf8 default collate utf8_general_ci;
use tecnow_17;
create table usuario(
	id_usuario bigint unsigned not null auto_increment primary key,
    nome varchar(50) not null,
    email varchar(50) not null unique,
    senha varchar(32) not null,
    dt_nascimento date not null,
    sexo enum('FEMININO', 'MASCULINO') not null
)default charset utf8;

create table jogo(
	id_jogo bigint unsigned not null auto_increment primary key,
    nome varchar(40) not null, 
	categoria enum('TIRO', 'RPG', 'PLATAFORMA_ESPORTE',
				   'HACK_AND_SLASH', 'OUTRO') not null, 
	dt_cadastro datetime default current_timestamp,
    user_id bigint unsigned not null,
    
    UNIQUE INDEX(user_id, nome),
    
     FOREIGN KEY(user_id)
		REFERENCES usuario(id_usuario)
        ON DELETE CASCADE
        ON UPDATE CASCADE
)default charset utf8;