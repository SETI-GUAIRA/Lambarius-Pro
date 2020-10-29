alter table "pessoa" add column hora_cadastro timestamp DEFAULT (now());
alter table "pessoa" add column hora_atualizacao timestamp DEFAULT (now());
