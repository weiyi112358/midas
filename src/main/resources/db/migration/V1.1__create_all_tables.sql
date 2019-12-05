DROP TABLE IF EXISTS students CASCADE;
DROP TABLE IF EXISTS tutors CASCADE;
DROP TABLE IF EXISTS  organizations CASCADE;




CREATE TABLE students(
    id    BIGSERIAL NOT NULL,
    name    VARCHAR(30) NOT NULL,
    email    VARCHAR(50),
    password VARCHAR(50),
    secret_key VARCHAR(50),
    org_id  BIGSERIAL NOT NULL
);

ALTER TABLE students ADD CONSTRAINT students_pk PRIMARY KEY ( id );


CREATE TABLE tutors(
    id    BIGSERIAL NOT NULL,
    name    VARCHAR  NOT NULL,
    email   VARCHAR  NOT NULL

);

ALTER TABLE tutors ADD CONSTRAINT tutors_pk PRIMARY KEY ( id );

CREATE TABLE organizations(
    id BIGSERIAL NOT NULL,
    name VARCHAR NOT NULL,
    area VARCHAR NOT NULL
);

ALTER TABLE organizations ADD CONSTRAINT organizations_pk PRIMARY KEY ( id );


