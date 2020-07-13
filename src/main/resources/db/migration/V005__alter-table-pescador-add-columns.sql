alter table add constraint fk_pescador_associacao foreign key (associacao) references associacao (codigo);
alter table add constraint fk_pescador_porto foreign key (porto) references porto (codigo)
