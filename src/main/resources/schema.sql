CREATE TABLE IF NOT EXISTS Organization (
                                            id        SERIAL PRIMARY KEY,
                                            version   INTEGER NOT NULL,
                                            name      VARCHAR(50) NOT NULL,
                                            full_name VARCHAR(50) NOT NULL,
                                            inn       VARCHAR(12) NOT NULL,
                                            kpp       VARCHAR(9) NOT NULL,
                                            address   VARCHAR(50) NOT NULL,
                                            phone     VARCHAR(20),
                                            is_active BOOLEAN
    );

COMMENT ON TABLE Organization IS 'Organization';
COMMENT ON COLUMN Organization.id IS 'Unique organization identifier';
COMMENT ON COLUMN Organization.version IS 'Service field hibernate';
COMMENT ON COLUMN Organization.name IS 'name of organization';
COMMENT ON COLUMN Organization.full_name IS 'full_name of organization';
COMMENT ON COLUMN Organization.inn IS 'inn of organization';
COMMENT ON COLUMN Organization.kpp IS 'kpp of organization';
COMMENT ON COLUMN Organization.address IS 'address of organization';
COMMENT ON COLUMN Organization.phone IS 'phone of organization';
COMMENT ON COLUMN Organization.is_active IS 'Is the organization in operation (is_active = true / false)';

CREATE TABLE IF NOT EXISTS Office (
                                      id              SERIAL PRIMARY KEY,
                                      version         INTEGER NOT NULL,
                                      org_id          INTEGER NOT NULL,
                                      name            VARCHAR(50),
                                      address         VARCHAR(50),
                                      phone           VARCHAR(16),
                                      is_active       BOOLEAN
    );

COMMENT ON TABLE Office IS 'Office of organization';
COMMENT ON COLUMN Office.id IS 'Unique office identifier';
COMMENT ON COLUMN Office.version IS 'Service field hibernate';
COMMENT ON COLUMN Office.org_id IS 'Unique organization identifier';
COMMENT ON COLUMN Office.name IS 'name of office';
COMMENT ON COLUMN Office.address IS 'address of office';
COMMENT ON COLUMN Office.phone IS 'phone of office';
COMMENT ON COLUMN Office.is_active IS 'Is the office in operation (is_active = true / false)';

CREATE TABLE IF NOT EXISTS "User" (
                                      id            SERIAL PRIMARY KEY,
                                      version       INTEGER NOT NULL,
                                      office_id     INTEGER NOT NULL,
                                      first_name    VARCHAR(50) NOT NULL,
                                      last_name     VARCHAR(50),
                                      middle_name   VARCHAR(50),
                                      position      VARCHAR(50) NOT NULL,
                                      phone         VARCHAR(16),
                                      is_identified BOOLEAN,
                                      country_id    INTEGER
    );

COMMENT ON TABLE "User" IS 'User of office';
COMMENT ON COLUMN "User".id IS 'Unique user identifier';
COMMENT ON COLUMN "User".version IS 'Service field hibernate';
COMMENT ON COLUMN "User".office_id IS 'Unique office identifier';
COMMENT ON COLUMN "User".first_name IS 'firstname of user';
COMMENT ON COLUMN "User".last_name IS 'last name  of user';
COMMENT ON COLUMN "User".middle_name IS 'middle name  of user';
COMMENT ON COLUMN "User".position Is 'position in office of user';
COMMENT ON COLUMN "User".phone IS 'phone  of user';
COMMENT ON COLUMN "User".is_identified IS 'Is the user authorized (is_active = true / false)';

CREATE TABLE IF NOT EXISTS Doc (
                                   user_id     INTEGER NOT NULL PRIMARY KEY,
                                   version     INTEGER NOT NULL,
                                   doc_type_id INTEGER,
                                   doc_number  VARCHAR(12),
                                   doc_date    DATE
    );

COMMENT ON TABLE Doc IS 'Doc of user';
COMMENT ON COLUMN Doc.user_id IS 'Unique user identifier';
COMMENT ON COLUMN Doc.version IS 'Service field hibernate';
COMMENT ON COLUMN Doc.doc_type_id IS 'Unique type of doc identifier';
COMMENT ON COLUMN Doc.doc_number IS 'number of doc';
COMMENT ON COLUMN Doc.doc_date IS 'date of doc';

CREATE TABLE IF NOT EXISTS Type_Doc (
                                       id      SERIAL PRIMARY KEY,
                                       version INTEGER NOT NULL,
                                       name    VARCHAR(50),
                                       code    VARCHAR(10)
    );

COMMENT ON TABLE Type_Doc IS 'Type of doc';
COMMENT ON COLUMN Type_Doc.version IS 'Service field hibernate';
COMMENT ON COLUMN Type_Doc.id IS 'Unique type of doc identifier';
COMMENT ON COLUMN Type_Doc.name IS 'name type of doc';
COMMENT ON COLUMN Type_Doc.code IS 'code type of doc';

CREATE TABLE IF NOT EXISTS Country (
                                       id      SERIAL PRIMARY KEY,
                                       version INTEGER NOT NULL,
                                       name    VARCHAR(50) ,
                                       code    VARCHAR(10)
    );

COMMENT ON TABLE Country IS 'Country of user';
COMMENT ON COLUMN Country.id IS 'Unique country identifier';
COMMENT ON COLUMN Country.version IS 'Service field hibernate';
COMMENT ON COLUMN Country.name IS 'name of country';
COMMENT ON COLUMN Country.code IS 'code of country';

ALTER TABLE Office ADD FOREIGN KEY (org_id) REFERENCES Organization(id);

ALTER TABLE "User" ADD FOREIGN KEY (office_id) REFERENCES Office(id);

ALTER TABLE "User" ADD FOREIGN KEY (country_id) REFERENCES Country(id);

ALTER TABLE Doc ADD FOREIGN KEY (user_id) REFERENCES "User"(id);

ALTER TABLE Doc ADD FOREIGN KEY (doc_type_id) REFERENCES Type_Doc(id);

