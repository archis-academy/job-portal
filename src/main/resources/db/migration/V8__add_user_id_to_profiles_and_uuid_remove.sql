
ALTER TABLE profiles
    ADD COLUMN user_id INTEGER;

ALTER TABLE profiles
    ADD CONSTRAINT fk_user
    FOREIGN KEY (user_id)
    REFERENCES users(id);

