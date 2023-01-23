DROP DATABASE IF EXISTS autenticacao;

CREATE DATABASE autenticacao;

\c autenticacao;

CREATE TABLE usuario (
	id SERIAL PRIMARY KEY,
	nome VARCHAR(255) NOT NULL,
	email VARCHAR(50) NOT NULL,
	senha VARCHAR(1000) NOT NULL,
	cpf VARCHAR(20) NOT NULL,
	telefone VARCHAR(20) NOT NULL,
	estado INT NOT NULL,
	cidade INT NOT NULL,
	cep VARCHAR(20) NOT NULL,
	rua VARCHAR(255) NOT NULL,
	numero INT NOT NULL,
	complemento VARCHAR(255),
	ativo BOOLEAN NOT NULL,
	cargo INT NOT NULL
);

INSERT INTO usuario (nome, email, senha, cpf, telefone, estado, cidade, cep, rua, numero, complemento, ativo, cargo) VALUES
	('ADMIN', 'admin@admin.com', 'c75275135589cb373ed390b233b129b485faa24d86874b4eafd038ad33bdd093909f23ca19c4a5fce7359b873f1edb79c4013186614712e232aa9e82f1e3ad9f', '765.141.530-20', '(41) 99788-1313', 25, 3426, '88.465-450', 'Rua do Admin', 15, null, TRUE, 0),
	('GERENTE 1', 'gerente1@gerente.com', '5b0f380c06da974c8591432bb24fc713f2a019098b161112c52eca0db5ecd8d9833319330308bab56cd98eb253b0891f4c53deddc2c4857dd958571c3d564dee', '865.229.210-81', '(41) 99761-1414', 25, 3426, '81.462-455', 'Rua do Gerente 1', 13, null, TRUE, 1),
	('CLIENTE 1', 'cliente1@cliente.com', '059bd4278d57c805cd939d76f30b26ef80d420c2a0662e88e2e75f8002bd62c7cd90bf3144d4d6cae3d979358360ede1d0dca9d2b66438acf59fdb30dcb9631f', '246.905.310-21', '(41) 97894-2136', 25, 3426, '87.422-220', 'Rua do Cliente 1', 10, null, TRUE, 2);
