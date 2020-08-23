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
	balance numeric,
	user_id serial not null,
	
	constraint user_accounts_pk
	primary key (id)
	 
);



insert into user_roles (name)
values ('ADMIN'), ('MANAGER'), ('ACCOUNT_HOLDER'), ('CLOSED');

commit;

grant usage on schema project to project_app;


ALTER DEFAULT PRIVILEGES IN SCHEMA project GRANT SELECT ON TABLES TO project_app;
GRANT SELECT ON ALL TABLES IN SCHEMA project TO project_app;
GRANT update ON ALL TABLES IN SCHEMA project TO project_app;
GRANT insert ON ALL TABLES IN SCHEMA project TO project_app;
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA project TO project_app;
select * from app_users;

SELECT table_catalog, table_schema, table_name, privilege_type
FROM   information_schema.table_privileges 
WHERE  grantee = 'project_app';
;


create or replace function add_account() returns trigger as $$
	begin 
		insert into project.user_accounts (balance)
		values (0);
		return new;
	end;
$$
language plpgsql;

drop trigger user_add_account on app_users;

create trigger user_add_account
after insert on app_users
for each statement 
execute procedure add_account();

delete from app_users 
where id = 2;



