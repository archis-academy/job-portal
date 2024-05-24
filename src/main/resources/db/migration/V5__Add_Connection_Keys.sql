
DROP TABLE IF EXISTS connections;
DROP SEQUENCE IF EXISTS connections_seq;

CREATE SEQUENCE connections_seq INCREMENT BY 1 MINVALUE 0 MAXVALUE 2147483647 START WITH 1;

CREATE TABLE connections (
    id INTEGER DEFAULT nextval('connections') NOT NULL PRIMARY KEY,
    status VARCHAR(255) NOT NULL,
    request_date TIMESTAMP NOT NULL,
    user_id INTEGER NOT NULL,
    connected_user_id INTEGER NOT NULL

);

-- Add foreign key constraints
ALTER TABLE connections
ADD CONSTRAINT fk_connections_user FOREIGN KEY (user_id) REFERENCES users(id),
ADD CONSTRAINT fk_connections_connected_user FOREIGN KEY (connected_user_id) REFERENCES users(id);

-- Optionally, add a unique constraint to prevent duplicate connections
ALTER TABLE connections
ADD CONSTRAINT unique_connections UNIQUE (user_id, connected_user_id);