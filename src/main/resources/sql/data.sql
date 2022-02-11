INSERT INTO user (id, email, password, first_name, last_name)
VALUES
(1, 'dancarter@email.com', '$2y$10$opbFjV4KaPaFj39mRXbLLeK7iMb/Epw7NAMI.zRuaZ98IFT8fbAc2', 'Dan', 'Carter'),
(2, 'jonnywilkinson@email.com', '$2y$10$eUiWeEsByhYTpufioJzeiexHc04furjsqqibv0Mj9j0qBO3dR1XJG', 'Jonny', 'Wilkinson'),
(3, 'quadecooper@email.com', '$2y$10$jISaZUAZoCONHZBXmB0AGO6a7RjGUWoNZnACiscN/baMi9bziJEq.', 'Quade', 'Cooper'),
(4, 'romainntk@email.com', '$2y$10$/Idj2ZT92iT703sNMvViUu2DEL7dPvgjkDLs/QXNNglUU59b2lmHy', 'Romain', 'Ntamack'),
(5, 'finnrussell@email.com', '$2y$10$MsSeMXyg24TlowcZIXdBY.SoqWm2yKMIsAXu1.6Jd89w0czQ1oRhq', 'Finn', 'Russell');

-- 1 ; email : dancarter@email.com, password : danythebest10
-- 2 ; email : jonnywilkinson@email.com, password : worldchampion2003
-- 3 ; email : quadecooper@email.com, password : crochetdudiable
-- 4 ; email : romainntk@email.com, password : talent1999
-- 5 ; email : finnrussell@email.com, password : touriste92

INSERT INTO connection (user_id, buddy_id)
VALUES
(1,2),
(1,3),
(1,4),
(2,4);

INSERT INTO account (balance, user_id)
VALUES
(100, 1),
(100, 2),
(100, 3),
(100, 4),
(100, 5);

INSERT INTO transaction (type, amount, fees, description, account_id, buddy_account_id)
VALUES
('TRANSFER_RECEIVE', 50, 0.25, 'Restaurant', 1, 2),
('TRANSFER_RECEIVE', 10, 0.05, 'Bus ticket', 1, 2),
('TRANSFER_RECEIVE', 80, 0.40, 'Plane ticket', 1, 2),
('TRANSFER_RECEIVE', 50, 0.25, 'Flowers', 1, 2),
('TRANSFER_RECEIVE', 50, 0.25, 'Movie tickets', 1, 2),
('TRANSFER_RECEIVE', 50, 0.25, 'Gift Matt', 1, 2),
('TRANSFER_RECEIVE', 50, 0.25, 'Gasoline', 1, 2),
('TRANSFER_RECEIVE', 50, 0.25, 'Gift Andrew', 1, 2),
('TRANSFER_RECEIVE', 50, 0.25, 'Gift Quade', 1, 2),
('TRANSFER_RECEIVE', 50, 0.25, 'Gift Finn', 1, 2),
('TRANSFER_RECEIVE', 50, 0.25, 'Restaurant', 1, 2),
('TRANSFER_RECEIVE', 50, 0.25, 'Rugby match', 1, 2),
('TRANSFER_RECEIVE', 50, 0.25, 'Phone', 1, 2),
('TRANSFER_RECEIVE', 50, 0.25, 'Trip money', 1, 2);


