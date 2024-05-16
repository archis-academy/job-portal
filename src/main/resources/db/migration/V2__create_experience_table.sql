DROP TABLE IF EXISTS "experiences";

DROP SEQUENCE IF EXISTS experiences_seq;

CREATE SEQUENCE experiences_seq INCREMENT BY 1 MINVALUE 0 MAXVALUE 2147483647 START WITH 1;

CREATE TABLE "public"."experiences"
(
    "id" INTEGER DEFAULT nextval('experiences_seq') NOT NULL PRIMARY KEY,
    "company_name" VARCHAR(255) NOT NULL,
    "start_date" DATE NOT NULL,
    "end_date" DATE,
    "location" VARCHAR(255),
    "location_type" VARCHAR(255),
    "position" VARCHAR(255) NOT NULL,
    "description" TEXT,
    "profile_title" VARCHAR(255) NOT NULL
);
