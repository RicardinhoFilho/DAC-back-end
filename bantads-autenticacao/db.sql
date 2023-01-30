DROP DATABASE IF EXISTS autenticacao;

CREATE DATABASE autenticacao;

\c autenticacao;

CREATE TABLE autenticacao (
	id SERIAL PRIMARY KEY,
	email VARCHAR(50) NOT NULL,
	senha VARCHAR(1000) NOT NULL,
	cargo INT NOT NULL
);

INSERT INTO autenticacao (email, senha, cargo) VALUES
	('gerente1@gerente.com', '5b0f380c06da974c8591432bb24fc713f2a019098b161112c52eca0db5ecd8d9833319330308bab56cd98eb253b0891f4c53deddc2c4857dd958571c3d564dee', 1),
	('gerente2@gerente.com', 'b18f6b920da857f0e3858fc59ee084686c5175a9598f19f38f2ae3017781d1ef4447ef07bae613f13fd8b597e1e51ed9e85fe437e79beb15de037b7b02e086b2', 1),
	('cliente1@cliente.com', '059bd4278d57c805cd939d76f30b26ef80d420c2a0662e88e2e75f8002bd62c7cd90bf3144d4d6cae3d979358360ede1d0dca9d2b66438acf59fdb30dcb9631f', 2),
	('cliente2@cliente.com', 'fc51872ca532fac69d0ce2f73dc2f25cc2687504e1954b376a76dcde2f0429d830e6e58b49056724e8a9664b09c5f3ef7df492559d406dd123651e7a2437fd64', 2),
	('admin@admin.com', '7a56551dac1cf054e7ea2ac50ddd9e694592a7f7d1a981b6cc7a94e71754db24aeaa3b036f50af3df9aa596214c2d80fe33afcea4a8cd1f0f99b7da68ffa685e', 0);

-- Senhas
-- Gerente1.
-- Gerente2.
-- Cliente1.
-- Admin2022.