-- noinspection SqlResolveForFile

insert into GENRE (id, name) values (1, 'Test genre');

insert into GENRE (id, name) values (2, 'Story');

insert into GENRE (id, name) values (3, 'Roman');

insert into GENRE (id, name) values (4, 'Test genre 2');

insert into AUTHOR(id, name) values(1, 'Test author');

insert into AUTHOR(id, name) values(2, 'Leo Tolstoy');

insert into AUTHOR(id, name) values(3, 'Test author 2');

insert into BOOK(id, name, author_id, genre_id)
values(1, 'War and Peace', 2 , 2);

insert into BOOK(id, name, author_id, genre_id)
values(2, 'Аnna Karenina', 2 , 1);

insert into BOOK(id, name, author_id, genre_id)
values(3, 'Ispoved', 2 , 3);

insert into BOOK(id, name, author_id, genre_id)
values(4, 'Test book', 1 , 1);

insert into BOOK(id, name, author_id, genre_id)
values(5, 'Test book 2', 1 , 1);