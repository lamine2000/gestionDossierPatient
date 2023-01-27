create database manssi;
use manssi;
create table test(
    id integer auto_increment primary key,
    name varchar(255),
    surname varchar(255)
);

insert into test value (1, 'lamine', 'gueye');
