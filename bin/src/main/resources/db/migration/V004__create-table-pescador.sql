CREATE TABLE pescador (
  codigo serial not null primary key,
  rgp character varying(20) not null,

  constraint fk_pescador_pessoa foreign key (codigo) references pessoa (codigo)
);
