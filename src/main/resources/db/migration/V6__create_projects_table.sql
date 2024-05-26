-- V1__create_projects_table.sql
DROP TABLE IF EXISTS projects;
DROP SEQUENCE IF EXISTS projects_seq;

CREATE SEQUENCE projects_seq INCREMENT BY 1 MINVALUE 0 MAXVALUE 2147483647 START WITH 1;

CREATE TABLE projects (
    id INTEGER DEFAULT nextval('projects_seq') NOT NULL PRIMARY KEY,
    project_name VARCHAR(255) NOT NULL,
    start_date TIMESTAMP,
    end_date TIMESTAMP,
    position VARCHAR(255) NOT NULL,
    status BOOLEAN,
    url VARCHAR(255),
    technologies TEXT,
    description TEXT,
    profile_id INTEGER NOT NULL
);

-- V6__add_foreign_key_to_projects_table.sql

ALTER TABLE projects
ADD CONSTRAINT fk_profile
FOREIGN KEY (profile_id) REFERENCES profiles(id);