CREATE TABLE pessoa(
	codigo serial not null primary key,
	nome text not null,
  data_nascimento timestamp not null,
	cpf_cnpj text varying(15) not null,
  rg_ie text not null,
  foto text not null,

	logradouro text varying(255),
	numero text varying(20),
	bairro text varying(255),
	complemento text varying(255),

	telefone text varying(15),
	celular text varying(15),
	email text varying(255),

);
