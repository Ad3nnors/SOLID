drop schema SistemaAgendamento;

create database SistemaAgendamento;

use SistemaAgendamento;

create table SistemaAgendamento.CLIENTES(
	id bigint not null auto_increment,
	cpf varchar(11) not null unique,
	email varchar(128) not null,
	senha varchar(64) not null, 
	nome varchar(128) not null,
	telefone varchar(13),
	sexo char(2),
	ano int(4),
	mes int(2),
	dia int(2), 
	primary key (id)
	);

create table SistemaAgendamento.PROFISSIONAIS(
	id bigint not null auto_increment,
	cpf varchar(11) not null unique,
	email varchar(128) not null,
	senha varchar(64) not null, 
	nome varchar(128) not null,
	area varchar(128) not null,
	especialidade varchar(128) not null,
	primary key (id)
    );

create table SistemaAgendamento.CONSULTAS(
	id bigint not null auto_increment,

	id_C bigint not null,
	constraint CLIENTES_fk foreign  key (id_C) references CLIENTES(id),

	id_P bigint not null,
	constraint PROFISSIONAIS_fk foreign  key (id_P) references PROFISSIONAIS(id),

	ano int not null,
	mes int not null,
	dia int not null, 
	hora int not null,
	primary key (id),
	CONSTRAINT CH_Contulta CHECK (ano>=2000 and ano <=3000 AND mes >= 1 AND mes <= 12 and dia >= 1 and dia <= 31)
	);

insert into SistemaAgendamento.CLIENTES(cpf, email, senha, nome) 
	values  ('11122233344', 'luciana.souza@estudante.ufscar.br', '1234', 'Luciana Souza');

insert into SistemaAgendamento.CLIENTES(cpf, email, senha, nome) 
	values  ('22233344455', 'jean.araujo@estudante.ufscar.br', '1234', 'Jean Araujo');

insert into SistemaAgendamento.CLIENTES(cpf, email, senha, nome) 
	values  ('33344455566', 'agiacomini@estudante.ufscar.br', '1234', 'Anderson Henrique');

insert into SistemaAgendamento.PROFISSIONAIS(cpf, email, senha, nome, area, especialidade) 
	values  ('11122233344', 'luciana.souza@estudante.ufscar.br', '1234', 'Luciana Souza', 'medicina', 'médica cardiologista');

insert into SistemaAgendamento.PROFISSIONAIS(cpf, email, senha, nome, area, especialidade) 
	values  ('22233344455', 'jean.araujo@estudante.ufscar.br', '1234', 'Jean Araujo', 'direito', 'advogado criminal');

insert into SistemaAgendamento.PROFISSIONAIS(cpf, email, senha, nome, area, especialidade) 
	values  ('33344455566', 'agiacomini@estudante.ufscar.br', '1234', 'Anderson Henrique', 'psicologia', 'psicólogo infantil');

insert into SistemaAgendamento.CONSULTAS(id_C, id_P, ano, mes, dia, hora)
	values (1, 2, 2021, 10, 2, 14);

insert into SistemaAgendamento.CONSULTAS(id_C, id_P, ano, mes, dia, hora)
	values (2, 3, 2021, 9, 7, 11);

insert into SistemaAgendamento.CONSULTAS(id_C, id_P, ano, mes, dia, hora)
	values (3, 1, 2021, 12, 3, 10);