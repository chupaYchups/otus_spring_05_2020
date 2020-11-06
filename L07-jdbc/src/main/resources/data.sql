insert into GENRE (id, genre_name) values (1, 'Детектив');
insert into GENRE (id, genre_name) values (2, 'Роман');
insert into GENRE (id, genre_name) values (3, 'Детская сказка');

insert into AUTHOR(id, first_name, second_name, last_name) values(1,'Толстой','Лев','Николаевич');
insert into AUTHOR(id, first_name, second_name, last_name) values(2,'Пушкин','Александр','Сергеевич');
insert into AUTHOR(id, first_name, second_name, last_name) values(3,'Есенин','Сергей','Александрович');


insert into BOOK(id, book_name, publish_date, author_id, genre_id)
values(1,'Война и Мир', CURDATE(), 1 , 2);
