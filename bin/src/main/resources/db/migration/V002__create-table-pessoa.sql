CREATE TABLE pessoa (
	codigo serial not null primary key,
	nome character varying(200) not null,
  data_nascimento timestamp not null,
	cpf_cnpj character varying(20) not null,
  rg_ie character varying(20) not null,
  foto character varying(10000),

	logradouro character varying(255),
	numero character varying(20),
	bairro character varying(255),
	complemento character varying(255),

	telefone character varying(15),
	celular character varying(15),
	email character varying(255)

);
