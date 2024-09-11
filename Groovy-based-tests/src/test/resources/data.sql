-- create schema my_schema;

set schema my_schema;

CREATE TABLE IF NOT EXISTS my_schema.sample_user (
    id INT PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255)
    );

-- below is not working must be some issue with the schema mappings.
/*-- Insert initial records into the 'user' table
INSERT INTO my_schema.sample_user (id, first_name, last_name, email) VALUES (1, 'Yash', 'Patel', 'lol@hex.com');
INSERT INTO my_schema.sample_user (id, first_name, last_name, email) VALUES (2, 'Bala', 'Arugula', 'rofl@hex.com');
INSERT INTO my_schema.sample_user (id, first_name, last_name, email) VALUES (3, 'Julian', 'Fernanado', 'lol1@hex.com');

commit;*/