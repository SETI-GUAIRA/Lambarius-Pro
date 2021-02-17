CREATE TABLE produtor(
  codigo serial not null primary key,
  feirante boolean not null,

  constraint fk_produtor_pessoa foreign key (codigo) references pessoa (codigo)
);
