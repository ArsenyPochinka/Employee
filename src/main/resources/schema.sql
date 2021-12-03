CREATE TABLE IF NOT EXISTS Organization (
                                            id        SERIAL PRIMARY KEY,
                                            version   INTEGER NOT NULL,
                                            name      VARCHAR(50) NOT NULL,
    full_name VARCHAR(50) NOT NULL,
    inn       VARCHAR(12) NOT NULL,
    kpp       VARCHAR(9) NOT NULL,
    address   VARCHAR(50) NOT NULL,
    phone     VARCHAR(16),
    is_active BIT
    );

COMMENT ON TABLE Organization IS 'Organization';
COMMENT ON COLUMN Organization.id IS 'Unique organization identifier';
COMMENT ON COLUMN Organization.version IS 'Service field hibernate';
COMMENT ON COLUMN Organization.name IS 'name';
COMMENT ON COLUMN Organization.full_name IS 'full_name';
COMMENT ON COLUMN Organization.inn IS 'inn';
COMMENT ON COLUMN Organization.kpp IS 'kpp';
COMMENT ON COLUMN Organization.address IS 'address';
COMMENT ON COLUMN Organization.phone IS 'phone';
COMMENT ON COLUMN Organization.is_active IS 'is active = 1 is true, 0 is false';

CREATE TABLE IF NOT EXISTS Office (
                                      id              SERIAL PRIMARY KEY,
                                      version         INTEGER NOT NULL,
                                      organization_id INTEGER NOT NULL,
                                      name            VARCHAR(50) NOT NULL,
    address         VARCHAR(50) NOT NULL,
    phone           VARCHAR(16),
    is_active       BIT
    );

COMMENT ON TABLE Office IS 'Office';
COMMENT ON COLUMN Office.id IS 'Unique office identifier';
COMMENT ON COLUMN Office.version IS 'Service field hibernate';
COMMENT ON COLUMN Office.organization_id IS 'Unique organization identifier';
COMMENT ON COLUMN Office.name IS 'name';
COMMENT ON COLUMN Office.address IS 'address';
COMMENT ON COLUMN Office.phone IS 'phone';
COMMENT ON COLUMN Office.is_active IS 'is active = 1 is true, 0 is false';

CREATE TABLE IF NOT EXISTS Person (
                                      id            SERIAL PRIMARY KEY,
                                      version       INTEGER NOT NULL,
                                      office_id     INTEGER NOT NULL,
                                      first_name    VARCHAR(50) NOT NULL,
    second_name   VARCHAR(50),
    middle_name   VARCHAR(50),
    post          VARCHAR(50) NOT NULL,
    phone         VARCHAR(16),
    is_identified BIT
    );

COMMENT ON TABLE Person IS 'Person';
COMMENT ON COLUMN Person.id IS 'Unique person identifier';
COMMENT ON COLUMN Person.version IS 'Service field hibernate';
COMMENT ON COLUMN Person.first_name IS 'firstname';
COMMENT ON COLUMN Person.second_name IS 'second name';
COMMENT ON COLUMN Person.middle_name IS 'middle name';
COMMENT ON COLUMN Person.post Is 'post';
COMMENT ON COLUMN Person.phone IS 'phone';
COMMENT ON COLUMN Person.is_identified IS 'is active = 1 is true, 0 is false';

CREATE TABLE IF NOT EXISTS Doc (
                                   person_id   INTEGER NOT NULL PRIMARY KEY,
                                   version     INTEGER NOT NULL,
                                   doc_type_id INTEGER NOT NULL,
                                   doc_number  VARCHAR(12) NOT NULL,
    doc_date    VARCHAR(10) NOT NULL
    );

COMMENT ON TABLE Doc IS 'Doc';
COMMENT ON COLUMN Doc.person_id IS 'Unique person identifier';
COMMENT ON COLUMN Doc.version IS 'Service field hibernate';
COMMENT ON COLUMN Doc.doc_type_id IS 'Unique type of doc identifier';
COMMENT ON COLUMN Doc.doc_number IS 'number of doc';
COMMENT ON COLUMN Doc.doc_date IS 'date of doc';

CREATE TABLE IF NOT EXISTS TypeDoc (
                                       id   SERIAL PRIMARY KEY,
                                       name VARCHAR(50) NOT NULL,
    code VARCHAR(10) NOT NULL
    );

COMMENT ON TABLE TypeDoc IS 'Type of doc';
COMMENT ON COLUMN TypeDoc.id IS 'Unique type of doc identifier';
COMMENT ON COLUMN TypeDoc.name IS 'name';
COMMENT ON COLUMN TypeDoc.code IS 'code';

CREATE TABLE IF NOT EXISTS Country (
                                       id      SERIAL PRIMARY KEY,
                                       version INTEGER NOT NULL,
                                       name    VARCHAR(50) NOT NULL,
    code    VARCHAR(10) NOT NULL
    );

COMMENT ON TABLE Country IS 'Country';
COMMENT ON COLUMN Country.id IS 'Unique country identifier';
COMMENT ON COLUMN Country.version IS 'Service field hibernate';
COMMENT ON COLUMN Country.name IS 'name';
COMMENT ON COLUMN Country.code IS 'code';

CREATE TABLE IF NOT EXISTS Person_Country (
                                              person_id  INTEGER,
                                              country_id INTEGER
);

COMMENT ON TABLE Person_Country IS 'join-table for connecting a person and a country';
COMMENT ON COLUMN Person_Country.person_id IS 'Unique person identifier';
COMMENT ON COLUMN Person_Country.country_id IS 'Unique country identifier';

ALTER TABLE Office ADD FOREIGN KEY (organization_id) REFERENCES Organization(id);

ALTER TABLE Person ADD FOREIGN KEY (office_id) REFERENCES Office(id);

ALTER TABLE Doc ADD FOREIGN KEY (person_id) REFERENCES Person(id);

ALTER TABLE Doc ADD FOREIGN KEY (doc_type_id) REFERENCES TypeDoc(id);

ALTER TABLE Person_Country ADD FOREIGN KEY (person_id) REFERENCES Person(id);
ALTER TABLE Person_Country ADD FOREIGN KEY (country_id) REFERENCES Country(id);