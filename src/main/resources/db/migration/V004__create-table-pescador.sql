CREATE TABLE pescador (
  codigo serial not null primary key,
  rgp text not null,

  constraint fk_pescador_pessoa foreign key (codigo) references pessoa (codigo)
);
