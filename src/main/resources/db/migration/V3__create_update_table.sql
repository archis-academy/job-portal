-- V3__create_update_table.sql

DROP TABLE IF EXISTS experiences;
DROP SEQUENCE IF EXISTS experiences_seq;

CREATE SEQUENCE experiences_seq INCREMENT BY 1 MINVALUE 0 MAXVALUE 2147483647 START WITH 1;

CREATE TABLE "public"."experiences"
(
    "id" INTEGER DEFAULT nextval('experiences_seq') NOT NULL PRIMARY KEY,
    "company_name" VARCHAR(255) NOT NULL,
    "start_date" TIMESTAMP NOT NULL,
    "is_active" BOOLEAN NOT NULL,
    "end_date" TIMESTAMP,
    "location" VARCHAR(255),
    "location_type" VARCHAR(255),
    "position" VARCHAR(255) NOT NULL,
    "description" TEXT,
    profile_id INTEGER NOT NULL

);


-- V3__Create_Comments_Table.sql
DROP TABLE IF EXISTS comments;
DROP SEQUENCE IF EXISTS comments_seq;

CREATE SEQUENCE comments_seq INCREMENT BY 1 MINVALUE 0 MAXVALUE 2147483647 START WITH 1;

CREATE TABLE comments (
    id INTEGER DEFAULT nextval('comments_seq') NOT NULL PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    user_uuid VARCHAR(36) NOT NULL,
    created_date TIMESTAMP NOT NULL,
    post_id INTEGER NOT NULL,
    user_post_comment_mapper_id INTEGER NOT NULL
);

-- V3__Create_Educations_Table.sql

DROP TABLE IF EXISTS educations;
DROP SEQUENCE IF EXISTS educations_seq;

CREATE SEQUENCE educations_seq INCREMENT BY 1 MINVALUE 0 MAXVALUE 2147483647 START WITH 1;

CREATE TABLE educations (
    id INTEGER DEFAULT nextval('educations_seq') NOT NULL PRIMARY KEY,
    university VARCHAR(255) NOT NULL,
    department VARCHAR(255) NOT NULL,
    graduation_date TIMESTAMP,
    description TEXT,
    profile_id INTEGER NOT NULL
);

-- V3__Create_jobs_table.sql

DROP TABLE IF EXISTS jobs;
DROP SEQUENCE IF EXISTS jobs_seq;

CREATE SEQUENCE jobs_seq INCREMENT BY 1 MINVALUE 0 MAXVALUE 2147483647 START WITH 1;

CREATE TABLE jobs (
    id INTEGER DEFAULT nextval('jobs_seq') NOT NULL PRIMARY KEY,
    company_name VARCHAR(255),
    start_date TIMESTAMP,
    end_date TIMESTAMP,
    location VARCHAR(255),
    location_type VARCHAR(255),
    position VARCHAR(255),
    description TEXT,
    user_id INTEGER NOT NULL

);

-- V3__Create_job_application_mappers_table.sql

DROP TABLE IF EXISTS job_application_mappers;
DROP SEQUENCE IF EXISTS job_application_mappers_seq;

CREATE SEQUENCE job_application_mappers_seq INCREMENT BY 1 MINVALUE 0 MAXVALUE 2147483647 START WITH 1;

CREATE TABLE job_application_mappers (
    id INTEGER DEFAULT nextval('job_application_mappers_seq') NOT NULL PRIMARY KEY,
    job_id INTEGER

);

DROP TABLE IF EXISTS user_job_application_mappers;
DROP SEQUENCE IF EXISTS user_job_application_mappers_seq;

CREATE SEQUENCE user_job_application_mappers_seq INCREMENT BY 1 MINVALUE 0 MAXVALUE 2147483647 START WITH 1;

CREATE TABLE user_job_application_mappers (
    id INTEGER DEFAULT nextval('user_job_application_mappers_seq') NOT NULL PRIMARY KEY,
    user_id INTEGER NOT NULL,
    job_application_mapper_id INTEGER NOT NULL
);

ALTER TABLE public.users
ADD COLUMN uuid VARCHAR(255),
ADD COLUMN telephone VARCHAR(20),
ADD COLUMN address VARCHAR(255),
ADD COLUMN user_type VARCHAR(50),
ADD COLUMN created_at TIMESTAMP,
ADD COLUMN updated_at TIMESTAMP,
ADD COLUMN profile_id INTEGER;


DROP TABLE IF EXISTS posts;
DROP SEQUENCE IF EXISTS posts_seq;

CREATE SEQUENCE posts_seq INCREMENT BY 1 MINVALUE 0 MAXVALUE 2147483647 START WITH 1;


CREATE TABLE posts (
        id INTEGER DEFAULT nextval('posts_seq') NOT NULL PRIMARY KEY,
        description VARCHAR(255),
        post_url VARCHAR(255),
        user_uuids VARCHAR(255),
        created_date TIMESTAMP,
        user_id INTEGER
);

DROP TABLE IF EXISTS skills;
DROP SEQUENCE IF EXISTS skills_seq;

CREATE SEQUENCE skills_seq INCREMENT BY 1 MINVALUE 0 MAXVALUE 2147483647 START WITH 1;

CREATE TABLE IF NOT EXISTS skills (
    id INTEGER DEFAULT nextval('skills_seq') NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    profile_id INTEGER NOT NULL
);

DROP TABLE IF EXISTS user_post_comment_mappers;
DROP SEQUENCE IF EXISTS user_post_comment_mappers_seq;

CREATE SEQUENCE user_post_comment_mappers_seq INCREMENT BY 1 MINVALUE 0 MAXVALUE 2147483647 START WITH 1;

CREATE TABLE IF NOT EXISTS user_post_comment_mappers (
    id INTEGER DEFAULT nextval('user_post_comment_mappers_seq') NOT NULL PRIMARY KEY,
    post_id INTEGER,
    user_id INTEGER
);
