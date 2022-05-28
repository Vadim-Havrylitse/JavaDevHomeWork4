CREATE DATABASE testgoitdb;

CREATE TABLE testgoitdb.developers (
	id bigint AUTO_INCREMENT,
    name varchar(20) NOT NULL,
    surname varchar(20) NOT NULL,
    age bigint CHECK (age>0),
    sex varchar(10),
    PRIMARY KEY(id)
    );
    
CREATE TABLE testGoITDB.skills (
	id bigint AUTO_INCREMENT,
    industry varchar(20),
    degree varchar(20) DEFAULT 'Junior',
    PRIMARY KEY(id)
    );

CREATE TABLE testgoitdb.projects (
	id bigint AUTO_INCREMENT,
    name varchar(50) UNIQUE,
    budget bigint NOT NULL, 
    release_date date,
    PRIMARY KEY(id)
    );
    
CREATE TABLE testgoitdb.companies (
	id bigint AUTO_INCREMENT,
    name varchar(50) NOT NULL,
    country varchar(20),
    city varchar(20),
    PRIMARY KEY(id)
    );
    
CREATE TABLE testgoitdb.customers (
	id bigint AUTO_INCREMENT,
    name varchar(20),
    surname varchar(20),
    PRIMARY KEY(id)
    );

CREATE TABLE testgoitdb.developers_projects (
	developers_id bigint,
    projects_id bigint,
    FOREIGN KEY (projects_id) REFERENCES testgoitdb.projects(id),
    FOREIGN KEY (developers_id) REFERENCES testgoitdb.developers(id)
    );
    
CREATE TABLE testgoitdb.developers_skills (
	developers_id bigint,
    skills_id bigint,
    FOREIGN KEY (skills_id) REFERENCES testgoitdb.skills(id),
    FOREIGN KEY (developers_id) REFERENCES testgoitdb.developers(id)
    );
    
ALTER TABLE testgoitdb.projects
	ADD COLUMN companies_id bigint NOT NULL,
    ADD COLUMN customers_id bigint NOT NULL,
    ADD FOREIGN KEY (customers_id) REFERENCES testgoitdb.customers(id),
    ADD FOREIGN KEY (companies_id) REFERENCES testgoitdb.companies(id);
    
ALTER TABLE testgoitdb.developers
	ADD COLUMN companies_id bigint,
    ADD FOREIGN KEY (companies_id) REFERENCES testgoitdb.companies(id);

ALTER TABLE testgoitdb.developers
    ADD COLUMN salary int;

ALTER TABLE projects
    ADD COLUMN cost bigint not NULL;