-- noinspection SqlResolveForFile

insert into GENRE (id, name) values (1, 'Detective');
insert into GENRE (id, name) values (2, 'Novel');
insert into GENRE (id, name) values (3, 'Children tale');
insert into GENRE (id, name) values (4, 'Story');

insert into AUTHOR(id, name) values(1,'Tolstoy');
insert into AUTHOR(id, name) values(2,'Pushkin');
insert into AUTHOR(id, name) values(3,'Esenin');
insert into AUTHOR(id, name) values(4,'Ilf & Petrov');

insert into BOOK(name, author_id, genre_id)
values('War and Peace', 1 , 2);

insert into BOOK(name, author_id, genre_id)
values('Black man', 3 , 2);

insert into BOOK(name, author_id, genre_id)
values('12 Chairs', 4 , 4);

insert into COMMENT(comment_string, book_id)
values('good book!', 1);

insert into COMMENT(comment_string, book_id)
values('excellent book!', 1);

insert into COMMENT(comment_string, book_id)
values('just shit!', 1);

insert into COMMENT(comment_string, book_id)
values('good book!', 2);

insert into COMMENT(comment_string, book_id)
values('excellent book!', 2);

insert into COMMENT(comment_string, book_id)
values('just shit!', 2);

insert into COMMENT(comment_string, book_id)
values('good book!', 3);

insert into COMMENT(comment_string, book_id)
values('excellent book!', 3);

insert into COMMENT(comment_string, book_id)
values('just shit!', 3);


