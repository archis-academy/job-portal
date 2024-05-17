DROP TABLE IF EXISTS profiles;
DROP TABLE IF EXISTS profile_sectors;
DROP SEQUENCE IF EXISTS profiles_seq;

CREATE SEQUENCE profiles_seq
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    START WITH 1;

CREATE TABLE profiles (
    id INTEGER DEFAULT nextval('profiles_seq') NOT NULL PRIMARY KEY,
    birth_date DATE NOT NULL,
    summary TEXT,
    sector VARCHAR(255)[]
);

DROP TABLE IF EXISTS certificates;
DROP SEQUENCE IF EXISTS certificates_seq;

CREATE SEQUENCE certificates_seq
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    START WITH 1;

CREATE TABLE certificates (
    id INTEGER DEFAULT nextval('certificates_seq') NOT NULL PRIMARY KEY,
    certificate_name VARCHAR(255) NOT NULL,
    company_name VARCHAR(255) NOT NULL,
    posting_date DATE NOT NULL,
    certificate_hours INTEGER NOT NULL,
    profile_id INTEGER NOT NULL,
    FOREIGN KEY (profile_id) REFERENCES profiles(id)
);
