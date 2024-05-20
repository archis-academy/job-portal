-- V3__update_users_table.sql

ALTER TABLE "public"."users"
    ADD COLUMN "uuid" VARCHAR(36),
    ADD COLUMN "telephone" VARCHAR(255),
    ADD COLUMN "address" TEXT,
    ADD COLUMN "user_type" VARCHAR(50) NOT NULL,
    ADD COLUMN "created_at" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    ADD COLUMN "updated_at" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    ADD COLUMN "profile_id" INTEGER NOT NULL;

ALTER TABLE "public"."users"
    ADD CONSTRAINT "fk_profile_id"
    FOREIGN KEY ("profile_id")
    REFERENCES "public"."profiles"("id")
    ON DELETE CASCADE;
