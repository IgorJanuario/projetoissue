create database issue;

use issue;

CREATE TABLE user
(
	id		smallint	auto_increment NOT NULL,
    nome	varchar(100)		NOT NULL,
    email	varchar(100)		NOT NULL,
    login	varchar(100)		NOT NULL,
    senha	varchar(100)		NOT NULL,
	
    Primary key(id)
);

CREATE TABLE empresa
(
	id		smallint		auto_increment  NOT NULL,
    nome	varchar(100)	NOT NULL,
    
    Primary key(id)
);

CREATE TABLE projeto
(
	id				smallint		auto_increment	NOT NULL,
    nome			varchar(100)	NOT NULL,
	dataInicio		date			NOT NULL,
    dataFim			date,
    orcamento		double,
    descricao		VARCHAR(200),
    idEmpresa		smallint,
    
    Primary key(id),
    foreign key(idEmpresa) references empresa(id)

);