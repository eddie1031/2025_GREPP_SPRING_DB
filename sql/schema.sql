create database `grepp_dao`;
use `grepp_dao`;

create table `items` (
    item_id bigint not null primary key auto_increment,
    name varchar(100) not null,
    code varchar(100) not null unique,
    price int,
    created_at datetime default now()
);

create table `orders` (
    order_id bigint not null primary key auto_increment,
    code varchar(100) not null unique,
    ordered_at datetime default now()
);

create table `order_items` (
    order_item_id bigint not null primary key auto_increment,
    order_code varchar(100) not null,
    item_code varchar(100) not null,
    quantity int not null,
    foreign key (order_code) references orders(code),
    foreign key (item_code) references items(code)
);







