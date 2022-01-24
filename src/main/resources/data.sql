INSERT INTO user (email, password, first_name, last_name, balance)
VALUES
('dancarter@email.com', 'danythebest10', 'Dan', 'Carter', 50),
('jonnywilkinson@email.com', 'worldchampion2003', 'Jonny', 'Wilkinson', 40),
('quadecooper@email.com', 'crochetdudiable', 'Quade', 'Cooper', 70),
('romainntk@email.com', 'talent1999', 'Romain', 'Ntamack', 100),
('finnrussell@email.com', 'touriste92', 'Finn', 'Russell', 80),
('admin@email.com', 'jaitouslespouvoirsPMB', 'admin', 'admin', 0);

INSERT INTO bank_account (iban, bic, name, user_email)
VALUES
('FR99111222333444555', 'AGRI0099', 'CA', 1),
('FR99000999888777666', 'CMC8899', 'CM', 4);

INSERT INTO connection (user_id, buddy_id)
VALUES
(1,2),
(4,1),
(5,3),
(2,4);

INSERT INTO buddy_transaction (amount_before_fees, fees, final_amount, description, date, connection_id)
VALUES
(20, 0.1, 20.1, 'train ticket', CURRENT_DATE, 1),
(30, 0.15, 30.15, 'restaurant', CURRENT_DATE, 2),
(60, 0.3, 60.3, 'Melbourne trip', CURRENT_DATE, 3),
(50, 0.25, 50.25, 'parking ticket', CURRENT_DATE, 4);