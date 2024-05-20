DROP TABLE IF EXISTS profiles;
DROP SEQUENCE IF EXISTS profiles_seq;

CREATE SEQUENCE profiles_seq
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    START WITH 1;

CREATE TABLE profiles (
    id INTEGER DEFAULT nextval('profiles_seq') NOT NULL PRIMARY KEY,
    birth_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    summary TEXT,
    sector TEXT
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
    posting_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    certificate_hours INTEGER NOT NULL,
    certificate_url VARCHAR(255) NOT NULL,
    profile_id INTEGER NOT NULL,
    ADD CONSTRAINT fk_profile_id
        FOREIGN KEY (profile_id)
        REFERENCES profiles(id)
        ON DELETE CASCADE
);
