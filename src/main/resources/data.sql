INSERT INTO Organization (version, name, full_name, inn, kpp, address,  phone, is_active) VALUES (0, 'MTS', 'Mobile Tele Systems', '723098123098', '123098123', 'Moscow, Maykovskay street, 10.', '+7(956)445-23-99', 'true');
INSERT INTO Organization (version, name, full_name, inn, kpp, address,  phone, is_active) VALUES (0, 'Beeline', 'Bee Line GSM', '323098123785', '323095721', 'Moscow, Polynskay street, 12.', '+7(912)445-23-88', 'true');
INSERT INTO Organization (version, name, full_name, inn, kpp, address,  phone, is_active) VALUES (0, 'Tele2', 'Tele2 AB', '243098123012', '243098154', 'Moscow, Stroganova street, 1.', '+7(985)445-23-12', 'true');

INSERT INTO Office (version, org_id, name, address,  phone, is_active) VALUES (0, 1, 'MTS General Office', 'Moscow, Pokrovskay street, 17.', '+7(956)345-23-21', 'true');
INSERT INTO Office (version, org_id, name, address,  phone, is_active) VALUES (0, 1, 'MTS Second Office', 'Saint-Petersburg, Varvaskay street, 21.', '+7(968)345-22-19', 'true');
INSERT INTO Office (version, org_id, name, address,  phone, is_active) VALUES (0, 2, 'Beeline General Office', 'Moscow, Severnay street, 15.', '+7(999)345-23-22', 'true');
INSERT INTO Office (version, org_id, name, address,  phone, is_active) VALUES (0, 2, 'Beeline Second Office', 'Saint-Petersburg, Mayakovskay street, 12.', '+7(912)345-23-12', 'false');
INSERT INTO Office (version, org_id, name, address,  phone, is_active) VALUES (0, 3, 'Tele2 General Office', 'Moscow, Vinogradova street, 1.', '+7(918)345-23-25', 'false');
INSERT INTO Office (version, org_id, name, address,  phone, is_active) VALUES (0, 3, 'Tele2 Second Office', 'Saint-Petersburg, Ilinskay street, 2.', '+7(925)343-23-88', 'true');

INSERT INTO Type_Doc (version, name, code) VALUES (0,'Passport of a citizen of the Russian Federation', '21');
INSERT INTO Type_Doc (version, name, code) VALUES (0, 'Passport of a citizen of Ukraine', '45');
INSERT INTO Type_Doc (version, name, code) VALUES (0, 'Passport of a citizen of Belarus', '23');

INSERT INTO Country (version, name, code) VALUES (0, 'Russian Federation', '643');
INSERT INTO Country (version, name, code) VALUES (0, 'Ukraine', '254');
INSERT INTO Country (version, name, code) VALUES (0, 'Belarus', '512');

INSERT INTO Users (version, office_id, first_name, last_name, position, phone, is_identified, country_id) VALUES (0, 1, 'Kirsenko', 'Konstantin', 'manager', '+7(955)345-23-55', 'true', 1);
INSERT INTO Users (version, office_id, first_name, last_name, position, phone, is_identified, country_id) VALUES (0, 1, 'Pochinka', 'Arseny', 'provider', '+7(912)345-23-56', 'true', 1);
INSERT INTO Users (version, office_id, first_name, last_name, position, phone, is_identified, country_id) VALUES (0, 2, 'Khafizov', 'Timur', 'doctor', '+7(912)345-23-12', 'true', 2);
INSERT INTO Users (version, office_id, first_name, last_name, position, phone, is_identified, country_id) VALUES (0, 3, 'Usachev', 'Ilia', 'manager', '+7(954)345-23-67', 'true', 2);
INSERT INTO Users (version, office_id, first_name, last_name, position, phone, is_identified, country_id) VALUES (0, 4, 'Mahov', 'Dmitriy', 'provider', '+7(942)345-23-43', 'true', 3);
INSERT INTO Users (version, office_id, first_name, last_name, position, phone, is_identified, country_id) VALUES (0, 5, 'Fedorov', 'Victor', 'doctor', '+7(932)347-22-51', 'true', 3);
INSERT INTO Users (version, office_id, first_name, last_name, position, phone, is_identified, country_id) VALUES (0, 6, 'Kirov', 'Kirill', 'manager', '+7(953)345-23-48', 'true', 1);
INSERT INTO Users (version, office_id, first_name, last_name, position, phone, is_identified, country_id) VALUES (0, 6, 'Firsov', 'Konstantin', 'provider', '+7(942)345-23-42', 'true', 1);
INSERT INTO Users (version, office_id, first_name, last_name, position, phone, is_identified, country_id) VALUES (0, 6, 'Nikulin', 'Vasiliy', 'doctor', '+7(965)345-23-32', 'false', 1);

INSERT INTO Doc (user_id, version, doc_type_id, doc_number, doc_date) VALUES (1, 0, 1, '45 12 342342', '2012-12-24');
INSERT INTO Doc (user_id, version, doc_type_id, doc_number, doc_date) VALUES (2, 0, 1, '46 19 442343', '2002-12-03');
INSERT INTO Doc (user_id, version, doc_type_id, doc_number, doc_date) VALUES (3, 0, 2, '49 23 547834', '2015-01-12');
INSERT INTO Doc (user_id, version, doc_type_id, doc_number, doc_date) VALUES (4, 0, 2, '45 11 123422', '2002-03-12');
INSERT INTO Doc (user_id, version, doc_type_id, doc_number, doc_date) VALUES (5, 0, 3, '12 23 232342', '2005-12-12');
INSERT INTO Doc (user_id, version, doc_type_id, doc_number, doc_date) VALUES (6, 0, 3, '48 12 346782', '1995-01-03');
INSERT INTO Doc (user_id, version, doc_type_id, doc_number, doc_date) VALUES (7, 0, 1, '12 25 342409', '2001-01-02');
INSERT INTO Doc (user_id, version, doc_type_id, doc_number, doc_date) VALUES (8, 0, 1, '01 02 517586', '2006-12-10');
INSERT INTO Doc (user_id, version, doc_type_id, doc_number, doc_date) VALUES (9, 0, 1, '02 01 512385', '2001-11-10');