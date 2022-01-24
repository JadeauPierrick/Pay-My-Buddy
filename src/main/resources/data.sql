INSERT INTO user (id, email, password, first_name, last_name)
VALUES
(1, 'dancarter@email.com', 'danythebest10', 'Dan', 'Carter'),
(2, 'jonnywilkinson@email.com', 'worldchampion2003', 'Jonny', 'Wilkinson'),
(3, 'quadecooper@email.com', 'crochetdudiable', 'Quade', 'Cooper'),
(4, 'romainntk@email.com', 'talent1999', 'Romain', 'Ntamack'),
(5, 'finnrussell@email.com', 'touriste92', 'Finn', 'Russell');

INSERT INTO connection (user_id, buddy_id)
VALUES
(1,2),
(4,1),
(5,3),
(2,4);

INSERT INTO account (balance, user_id)
VALUES
(100, 1),
(100, 2),
(100, 3),
(100, 4),
(100, 5);
