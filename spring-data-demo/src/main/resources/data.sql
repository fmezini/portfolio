insert into author values (101, 'John');
insert into author values (102, 'Kelly');
insert into author values (103, 'Fernando');
insert into author values (104, 'Barbara');

insert into phone(phone, author_id) values ('1234-5678', 101);
insert into phone(phone, author_id) values ('1234-5679', 101);
insert into phone(phone, author_id) values ('2222-1111', 102);
insert into phone(phone, author_id) values ('1000-1111', 103);

insert into book(id, name, author_id) values (101, 'Book 1', 101);
insert into book(id, name, author_id) values (102, 'Book 2', 102);
insert into book(id, name, author_id) values (103, 'Book 3', 103);
insert into book(id, name, author_id) values (104, 'Book 3', 103);