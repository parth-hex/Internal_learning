CREATE SCHEMA my_schema;

CREATE TABLE my_schema.sample_user
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    first_name VARCHAR(255),
    last_name  VARCHAR(255),
    email      VARCHAR(255),
    CONSTRAINT pk_sample_user PRIMARY KEY (id)
);

