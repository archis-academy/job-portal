-- V3__update_users_table.sql

-- Remove the old full_name column
ALTER TABLE "public"."users"
    DROP COLUMN "full_name";

ALTER TABLE "public"."users"
    ADD COLUMN "uuid" VARCHAR(36),
    ADD COLUMN "name" VARCHAR(255) NOT NULL,
    ADD COLUMN "sur_name" VARCHAR(255) NOT NULL,
    ADD COLUMN "telephone" VARCHAR(255),
    ADD COLUMN "address" TEXT,
    ADD COLUMN "user_type" VARCHAR(50) NOT NULL,
    ADD COLUMN "created_at" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    ADD COLUMN "updated_at" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;

-- Modify the existing columns
-- Ensure 'id' uses nextval('users_seq') for default value (if necessary)
ALTER TABLE "public"."users"
    ALTER COLUMN "id" SET DEFAULT nextval('users_seq');

-- Optional: Update the email column to ensure it remains NOT NULL
ALTER TABLE "public"."users"
    ALTER COLUMN "email" SET NOT NULL;
