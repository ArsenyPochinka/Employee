INSERT INTO Organization (version, name, full_name, inn, kpp, address,  phone, is_active) VALUES (0, 'MTS', 'Mobile Tele Systems', '723098123098', '123098123', 'Moscow, Maykovskay street, 10.', '+7(956)445-23-99', 1);
INSERT INTO Organization (version, name, full_name, inn, kpp, address,  phone, is_active) VALUES (0, 'Beeline', 'Bee Line GSM', '323098123785', '323095721', 'Moscow, Polynskay street, 12.', '+7(912)445-23-88', 1);
INSERT INTO Organization (version, name, full_name, inn, kpp, address,  phone, is_active) VALUES (0, 'Tele2', 'Tele2 AB', '243098123012', '243098154', 'Moscow, Stroganova street, 1.', '+7(985)445-23-12', 1);

INSERT INTO Office (version, organization_id, name, address,  phone, is_active) VALUES (0, 1, 'MTS General Office', 'Moscow, Pokrovskay street, 17.', '+7(956)345-23-21', 1);
INSERT INTO Office (version, organization_id, name, address,  phone, is_active) VALUES (0, 1, 'MTS Second Office', 'Saint-Petersburg, Varvaskay street, 21.', '+7(968)345-22-19', 1);
INSERT INTO Office (version, organization_id, name, address,  phone, is_active) VALUES (0, 2, 'Beeline General Office', 'Moscow, Severnay street, 15.', '+7(999)345-23-22', 1);
INSERT INTO Office (version, organization_id, name, address,  phone, is_active) VALUES (0, 2, 'Beeline Second Office', 'Saint-Petersburg, Mayakovskay street, 12.', '+7(912)345-23-12', 0);
INSERT INTO Office (version, organization_id, name, address,  phone, is_active) VALUES (0, 3, 'Tele2 General Office', 'Moscow, Vinogradova street, 1.', '+7(918)345-23-25', 0);
INSERT INTO Office (version, organization_id, name, address,  phone, is_active) VALUES (0, 3, 'Tele2 Second Office', 'Saint-Petersburg, Ilinskay street, 2.', '+7(925)343-23-88', 1);

INSERT INTO Person (version, office_id, first_name, second_name, post, phone, is_identified) VALUES (0, 1, 'Kirsenko', 'Konstantin', 'manager', '+7(955)345-23-55', 1);
INSERT INTO Person (version, office_id, first_name, second_name, post, phone, is_identified) VALUES (0, 1, 'Pochinka', 'Arseny', 'provider', '+7(912)345-23-56', 1);
INSERT INTO Person (version, office_id, first_name, second_name, post, phone, is_identified) VALUES (0, 2, 'Khafizov', 'Timur', 'doctor', '+7(912)345-23-12', 1);
INSERT INTO Person (version, office_id, first_name, second_name, post, phone, is_identified) VALUES (0, 3, 'Usachev', 'Ilia', 'manager', '+7(954)345-23-67', 1);
INSERT INTO Person (version, office_id, first_name, second_name, post, phone, is_identified) VALUES (0, 4, 'Mahov', 'Dmitriy', 'provider', '+7(942)345-23-43', 1);
INSERT INTO Person (version, office_id, first_name, second_name, post, phone, is_identified) VALUES (0, 5, 'Fedorov', 'Victor', 'doctor', '+7(932)347-22-51', 1);
INSERT INTO Person (version, office_id, first_name, second_name, post, phone, is_identified) VALUES (0, 6, 'Kirov', 'Kirill', 'manager', '+7(953)345-23-48', 1);
INSERT INTO Person (version, office_id, first_name, second_name, post, phone, is_identified) VALUES (0, 6, 'Firsov', 'Konstantin', 'provider', '+7(942)345-23-42', 1);
INSERT INTO Person (version, office_id, first_name, second_name, post, phone, is_identified) VALUES (0, 6, 'Nikulin', 'Vasiliy', 'doctor', '+7(965)345-23-32', 0);

INSERT INTO TypeDoc (name, code) VALUES ('Passport of a citizen of the Russian Federation', '21');
INSERT INTO TypeDoc (name, code) VALUES ('Passport of a citizen of Ukraine', '45');
INSERT INTO TypeDoc (name, code) VALUES ('Passport of a citizen of Belarus', '23');

INSERT INTO Doc (person_id, version, doc_type_id, doc_number, doc_date) VALUES (1, 0, 1, '45 12 342342', '24/12/2012');
INSERT INTO Doc (person_id, version, doc_type_id, doc_number, doc_date) VALUES (2, 0, 1, '46 19 442343', '12/03/2002');
INSERT INTO Doc (person_id, version, doc_type_id, doc_number, doc_date) VALUES (3, 0, 2, '49 23 547834', '01/12/2015');
INSERT INTO Doc (person_id, version, doc_type_id, doc_number, doc_date) VALUES (4, 0, 2, '45 11 123422', '23/11/2006');
INSERT INTO Doc (person_id, version, doc_type_id, doc_number, doc_date) VALUES (5, 0, 3, '12 23 232342', '12/12/2015');
INSERT INTO Doc (person_id, version, doc_type_id, doc_number, doc_date) VALUES (6, 0, 3, '48 12 346782', '09/03/1995');
INSERT INTO Doc (person_id, version, doc_type_id, doc_number, doc_date) VALUES (7, 0, 1, '12 25 342409', '01/02/2013');
INSERT INTO Doc (person_id, version, doc_type_id, doc_number, doc_date) VALUES (8, 0, 1, '01 02 517586', '22/10/2016');
INSERT INTO Doc (person_id, version, doc_type_id, doc_number, doc_date) VALUES (9, 0, 1, '02 01 512385', '21/10/2011');

INSERT INTO Country (version, name, code) VALUES (0, 'Russian Federation', '643');
INSERT INTO Country (version, name, code) VALUES (0, 'Ukraine', '254');
INSERT INTO Country (version, name, code) VALUES (0, 'Belarus', '512');

INSERT INTO Person_Country (person_id, country_id) VALUES (1, 1);
INSERT INTO Person_Country (person_id, country_id) VALUES (1, 2);
INSERT INTO Person_Country (person_id, country_id) VALUES (2, 1);
INSERT INTO Person_Country (person_id, country_id) VALUES (3, 2);
INSERT INTO Person_Country (person_id, country_id) VALUES (4, 2);
INSERT INTO Person_Country (person_id, country_id) VALUES (5, 3);
INSERT INTO Person_Country (person_id, country_id) VALUES (6, 3);
INSERT INTO Person_Country (person_id, country_id) VALUES (7, 1);
INSERT INTO Person_Country (person_id, country_id) VALUES (7, 3);
INSERT INTO Person_Country (person_id, country_id) VALUES (8, 1);
INSERT INTO Person_Country (person_id, country_id) VALUES (9, 1);
INSERT INTO Person_Country (person_id, country_id) VALUES (9, 3);