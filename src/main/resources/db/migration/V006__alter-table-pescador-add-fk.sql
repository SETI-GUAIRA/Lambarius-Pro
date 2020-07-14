alter table "pescador" add foreign key (associacao) references "associacao"("codigo");
alter table "pescador" add foreign key (porto) references "porto"("codigo")
