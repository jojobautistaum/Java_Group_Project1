create schema if not exists customer_data_service;
  use customer_data_service;

create table if not exists customer (
	customer_id int not null auto_increment primary key,
	city varchar(20) not null,
	first_name varchar(50) not null,
	last_name varchar(50) not null,
	level varchar(20) not null,
	state char(2) not null,
	street varchar(50) not null,
	zipcode varchar(5) not null
);