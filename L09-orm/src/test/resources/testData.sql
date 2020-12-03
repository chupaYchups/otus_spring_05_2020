-- noinspection SqlResolveForFile

insert into GENRE (id, name) values (1, 'Detective'),
(2, 'Novel');

insert into AUTHOR(id, name) values(1,'Tolstoy'), (2,'Pushkin');

insert into BOOK(id, name, author_id, genre_id)
values(1, 'War and Peace', 1 , 1), (2, 'Black man', 2 , 1);

insert into COMMENT(id, comment_string, book_id)
values(1, 'good book!', 1), (2, 'excellent book!', 1), (3, 'good book!', 2), (4, 'excellent book!', 2);
