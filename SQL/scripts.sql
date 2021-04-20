create database Eve_Library;
use Eve_Library;

create table users
(
	id int primary key auto_increment,
    name varchar(50) not null,
    email varchar(50) not null unique,
    password varchar(255) not null,
    password_salt longblob not null,
    user_type varchar(50) not null
);
create table books
(
	book_id int primary key auto_increment,
	ISBN varchar(20) not null unique,
    book_name varchar(255) not null,
    author_name varchar(255) not null,
    book_description text not null,
    book_price decimal not null,
    book_photho longblob not null
);
create table request_books
(
	request_id int primary key auto_increment,
    user_id int not null,
    book_ISBN int not null,
    
    foreign key(user_id) references users(id),
    foreign key(book_ISBN) references books(ISBN)
);

select * from users;