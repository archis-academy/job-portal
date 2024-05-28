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
    url VARCHAR(255),
    technologies TEXT,
    description TEXT,
    profile_id INTEGER NOT NULL,
    CONSTRAINT fk_profile
<<<<<<< HEAD
    FOREIGN KEY (profile_id) REFERENCES profiles(id)
=======
    FOREIGN KEY (profile_id) REFERENCES profiles(id);
>>>>>>> c29905fac51992df9066688a10213115afedb23e

);

