-- noinspection SqlResolveForFile

insert into GENRE (id, name) values (1, 'Детектив');
insert into GENRE (id, name) values (2, 'Роман');
insert into GENRE (id, name) values (3, 'Детская сказка');
insert into GENRE (id, name) values (4, 'Рассказ');

insert into AUTHOR(id, name) values(1,'Толстой');
insert into AUTHOR(id, name) values(2,'Пушкин');
insert into AUTHOR(id, name) values(3,'Есенин');
insert into AUTHOR(id, name) values(4,'Ильф');
insert into AUTHOR(id, name) values(5,'Петров');


insert into BOOK(name, publish_date, author_id, genre_id)
values('Война и Мир', CURDATE(), 1 , 2);

insert into BOOK(name, publish_date, author_id, genre_id)
values('Чёрный человек', CURDATE(), 3 , 2);