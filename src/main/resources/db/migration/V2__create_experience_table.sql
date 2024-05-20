DROP TABLE IF EXISTS "experiences";

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
    profile_id INTEGER NOT NULL, FOREIGN KEY (profile_id) REFERENCES profiles(id)
);
