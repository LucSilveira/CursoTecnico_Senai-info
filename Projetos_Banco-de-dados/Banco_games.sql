CREATE SCHEMA dbgames;
USE dbgames;
CREATE TABLE games(
id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(255) NOT NULL,
video_game VARCHAR(200),
preco FLOAT(8,2)
);

SELECT * FROM dbgames.games;

CREATE TABLE usuarios(
id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
usuario VARCHAR(255) NOT NULL,
senha VARCHAR(255) NOT NULL
);

INSERT INTO usuarios(usuario, senha) VALUES('adm', 'adm');
SELECT * FROM dbgames.usuarios;
