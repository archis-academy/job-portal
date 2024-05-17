DROP TABLE IF EXISTS "profiles";

DROP SEQUENCE IF EXISTS profile_seq;

CREATE SEQUENCE profile_seq INCREMENT BY 1 MINVALUE 0 MAXVALUE 2147483647 START WITH 1;

CREATE TABLE "public"."profiles"(
    "id" INTEGER DEFAULT nextval('profile_seq') NOT NULL PRIMARY KEY,
    "name" VARCHAR(255)
);

ALTER TABLE users ADD COLUMN "profile_id" INTEGER NOT NULL;

ALTER TABLE users ADD CONSTRAINT "fk_profile_id"
    FOREIGN KEY (profile_id) REFERENCES "public"."profiles"(id);

CREATE TABLE "public"."user_profile_mappers"(
    "id" INTEGER DEFAULT nextval('profile_seq') NOT NULL PRIMARY KEY,
    "user_id" INTEGER,
    "profile_id" INTEGER,
    CONSTRAINT "fk_user_id" FOREIGN KEY (user_id) REFERENCES "public"."users"(id),
    CONSTRAINT "fk_profile_id" FOREIGN KEY (profile_id) REFERENCES "public"."profiles"(id)
)