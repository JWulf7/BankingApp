drop table if exists customers cascade;

create table customers (
	userName VARCHAR (50) Primary key,
	userPassword varchar (50) not null,
	firstName varchar (50) not null,
	lastName varchar (50) not null,
	address varchar (150),
	approved boolean not null
);


drop table if exists employeesAdmins cascade;
create table employeesAdmins (
	userName VARCHAR (50) Primary key,
	userPassword varchar (50) not null,
	firstName varchar (50) not null,
	lastName varchar (50) not null,
	isAdmin boolean not null
);

drop table if exists bankAccounts CASCADE;
create table bankAccounts (
	accountNumber serial Primary key,
	accountBalance numeric (50, 2) default 0,
	userNam varchar (50) not null REFERENCES customers(userName)
);

drop table if exists transactions CASCADE;
create table transactions (
	transactionID serial PRIMARY KEY,
	accountNum int not null REFERENCES bankAccounts(accountNumber),
	descript VARCHAR (50) not null
);

-- begin with a few players --
insert into employeesAdmins (userName, userPassword, firstName, lastName, isAdmin)
	values ('UserName', 'Password1', 'Jonathan', 'Wulf', true);

insert into employeesAdmins (userName, userPassword, firstName, lastName, isAdmin)
	values ('Employee1', 'Password1', 'Larry', 'King', false);
insert into employeesAdmins (userName, userPassword, firstName, lastName, isAdmin)
	values ('Employee2', 'Password1', 'Ron', 'Swanson', false);

insert into customers (userName, userPassword, firstName, lastName, address, approved)
	values ('Fred123', 'Xmas123', 'Fred', 'Clause', '123 North Pole Ln, North Pole, Earth', true);
insert into customers (userName, userPassword, firstName, lastName, address, approved)
	values ('Trash4Lyfe', 'I<3Elmo', 'Oscar', 'Grouch', '5 Sesame Street, PBS', true);
insert into customers (userName, userPassword, firstName, lastName, address, approved)
	values ('DarKnight', 'c4tsRHottt', 'Bruce', 'Wayne', '17103 Grande Manor, Gotham', true);
insert into customers (userName, userPassword, firstName, lastName, address, approved)
	values ('FORCE', 'n0try0nlyD0', 'Baby', 'Yoda', '1 Egg Crib, planet', false);

insert into bankAccounts (accountBalance, userNam)
	values(500.50, 'Fred123');
insert into bankAccounts (accountBalance, userNam)
	values(28987.99, 'Fred123');
insert into bankAccounts (accountBalance, userNam)
	values(8000.01, 'Trash4Lyfe');
insert into bankAccounts (accountBalance, userNam)
	values(214.00, 'Trash4Lyfe');
insert into bankAccounts (accountBalance, userNam)
	values(12345.67, 'DarKnight');
insert into bankAccounts (accountBalance, userNam)
	values(9876543.21, 'DarKnight');
insert into bankAccounts (accountBalance, userNam)
	values(99999999999.99, 'DarKnight');
insert into bankAccounts (accountBalance, userNam)
	values(200.01, 'DarKnight');
insert into bankAccounts (accountBalance, userNam)
	values(10000.20, 'DarKnight');
insert into bankAccounts (accountBalance, userNam)
	values(700.00, 'DarKnight');
