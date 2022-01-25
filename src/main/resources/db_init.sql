--create database test;

create table users (
 id int not null,
 username varchar(255) not null,
 password varchar(255) not null
);
insert into users values(1,'user','user');
insert into users values(2,'postgres','postgres');

drop table books;
create table books (
                       id SERIAL NOT NULL PRIMARY KEY,
                       title varchar(255) not null,
                       author varchar(255) not null,
                       genre varchar(255) not null,
                       text text,
                       img bytea
);
insert into books (title,author,genre,text) values('Первая','SQL','ужасы','Маленькая книга на первы раз.');
insert into books (title,author,genre,text) values('Вторая','SQL','ужасы фантастика','На этот раз этот текст чуть больше первого текста.');
insert into books (title,author,genre,text) values('Третья','SQL','фантастика мелодрама','Тут просто всякая ерунда.');
insert into books (title,author,genre,text) values('Четвертая','SQL','мелодрама','Все живы.');
insert into books (title,author,genre,text) values('Пятая','скрипт','мелодрама','Все живы.');
insert into books (title,author,genre,text) values('Шестая','скрипт','детектив','Нашли что пропало.');
insert into books (title,author,genre,text) values('Седьмая','скрипт','детектив','Нашли что пропало.');
insert into books (title,author,genre,text) values('Восьмая','скрипт','триллер','Главный герой остался жив.');
insert into books (title,author,genre,text) values('Девятая','скрипт','триллер','Главный герой остался жив.');
insert into books (title,author,genre,text) values('Десятая','скрипт','триллер','Главный герой остался жив.');
insert into books (title,author,genre,text) values('Запредельная','скрипт','триллер','Главный герой остался жив.');
