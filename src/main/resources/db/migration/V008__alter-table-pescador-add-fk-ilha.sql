alter table "pescador" add column "ilha" int NULL;
alter table "pescador" add foreign key (ilha) references "ilha"("codigo")
