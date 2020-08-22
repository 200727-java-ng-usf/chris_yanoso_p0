CREATE USER project_app
WITH password 'revature';

grant all privileges on 
database postgres
to project_app;

create table user_roles(
	id serial,
	name varchar(25) not null,
	
	constraint user_roles_pk 
	primary key (id)
);

create table app_users(
	id serial,
	username varchar(25) unique not null,
	password varchar(256) not null,
	first_name varchar(25) not null,
	last_name varchar(25) not null,
	role_id int not null,
	
	constraint app_users_pk 
	primary key (id),
	
	constraint user_role_fk 
	foreign key (role_id)
	references user_roles
);

create table user_accounts(
	id serial,
	balance int,
	user_id int not null,
	
	constraint user_accounts_pk
	primary key (id),
	
	constraint accounts_user_fk 
	foreign key (user_id)
	references app_users
);


alter table app_users 
add accounts_id int;

alter table app_users 
add 
constraint user_accounts_fk
foreign key (accounts_id)
references user_accounts;

insert into user_roles (name)
values ('ADMIN'), ('MANAGER'), ('ACCOUNT_HOLDER'), ('CLOSED');

alter table user_accounts 
drop balance;

alter table project.user_accounts
add balance numeric;