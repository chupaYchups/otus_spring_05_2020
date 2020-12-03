-- noinspection SqlResolveForFile

insert into GENRE (id, name) values (1, 'Detective'),
(2, 'Novel');

insert into AUTHOR(id, name) values(1,'Tolstoy'), (2,'Pushkin');

insert into BOOK(name, author_id, genre_id)
values('War and Peace', 1 , 2), ('Black man', 2 , 1);

insert into COMMENT(comment_string, book_id)
values('good book!', 1), ('excellent book!', 1), ('good book!', 2), ('excellent book!', 2);
