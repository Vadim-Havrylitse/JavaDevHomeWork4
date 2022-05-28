INSERT INTO testgoitdb.companies (name,country,city)
	VALUES
	('ABN inc', 'Makedonia', 'Tringo'),
	('FIFA corp', 'Italy', 'Rome'),
	('Spring4beavers', 'Holland', 'Amsterdam');

INSERT INTO testgoitdb.customers (name, surname)
	VALUES
	('John', 'Makedon'),
	('Bob', 'Fridgh'),
	('Ivan', 'Petrenko');

INSERT INTO testgoitdb.skills (industry, degree)
	VALUES
	('Java', 'Middle'),
    ('JS', 'Middle'),
    ('C++', 'Senior'),
    ('C++', 'Middle');
    
INSERT INTO testgoitdb.skills (industry)
	VALUES
	('Java'),
    ('JS');
    
INSERT INTO testgoitdb.projects 
	VALUES
    (1, 'Loombook', 123000, '2022-12-22', 1,2),
    (2, 'IDEA Workspace', 2340000500, null, 2,2),
    (3, 'Git workbench', 450100500, null, 1,3);
    
INSERT INTO testgoitdb.developers
	VAlUES
    (1, 'Nenci', 'Ibragimovich', 31, 'female', 2),
    (2, 'Victor', 'Ronaldo', 29, 'male', null),
    (3, 'Imanuel', 'Mikron', 22, 'other', 1);
    
INSERT INTO testgoitdb.developers_skills
	VALUES
    (2,3),
    (1,3),
    (1,6),
    (1,2),
    (2,2),
    (3,1);
    
    
INSERT INTO testgoitdb.developers_projects
	VALUES 
    (1, 2),
    (2, 2),
    (3, 1),
    (3, 2),
    (1, 1),
	(1, 3);

UPDATE testgoitdb.developers
SET salary = 12000
WHERE id = 1;

UPDATE testgoitdb.developers
SET salary = 22000
WHERE id = 2;

UPDATE testgoitdb.developers
SET salary = 12000
WHERE id = 3;

UPDATE projects
SET cost = 12500
WHERE id = 2;

UPDATE projects
SET cost = 230000
WHERE id = 1;

UPDATE projects
SET cost = 12500
WHERE id = 3;
