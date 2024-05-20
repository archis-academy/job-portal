-- V2__update_users_table.sql
DROP TABLE IF EXISTS "profiles";

DROP SEQUENCE IF EXISTS profile_seq;

CREATE SEQUENCE profile_seq INCREMENT BY 1 MINVALUE 0 MAXVALUE 2147483647 START WITH 1;

CREATE TABLE  "public"."profiles"
(
    "id" INTEGER DEFAULT nextval('profile_seq') NOT NULL PRIMARY KEY,
    "name" VARCHAR(255)
);

ALTER TABLE "public"."users"
    ADD COLUMN "uuid" VARCHAR(36),
    ADD COLUMN "telephone" VARCHAR(255),
    ADD COLUMN "address" TEXT,
    ADD COLUMN "user_type" VARCHAR(50) NOT NULL,
    ADD COLUMN "created_at" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    ADD COLUMN "updated_at" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    ADD COLUMN "profile_id" INTEGER NOT NULL;

ALTER TABLE "public"."users"
    ADD CONSTRAINT "fk_profile_id" FOREIGN KEY (profile_id) REFERENCES "public"."profiles"(id);
