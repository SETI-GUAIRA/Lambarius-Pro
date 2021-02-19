CREATE TABLE produto(
  codigo serial not null primary key,
  nome character varying(200) not null,
  tipoproduto int not null,
  unidademedida character varying(10) not null
);
