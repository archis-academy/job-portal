ALTER TABLE users RENAME COLUMN user_type TO user_role;
ALTER TABLE posts RENAME COLUMN post_url TO image_url;

ALTER TABLE posts RENAME COLUMN user_uuids TO liked_users;

ALTER TABLE educations ADD COLUMN start_date TIMESTAMP;
ALTER TABLE comments ADD COLUMN update_date TIMESTAMP;
