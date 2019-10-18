DROP TABLE IF EXISTS student CASCADE;
DROP TABLE IF EXISTS tutor CASCADE;
DROP TABLE IF EXISTS  organization CASCADE;




CREATE TABLE student(
    id    BIGSERIAL NOT NULL,
    name    VARCHAR(30) NOT NULL,
    email    VARCHAR(50)
);

ALTER TABLE student ADD CONSTRAINT student_pk PRIMARY KEY ( id );


CREATE TABLE tutor(
    id    BIGSERIAL NOT NULL,
    name    VARCHAR  NOT NULL,
    email   VARCHAR  NOT NULL

);

ALTER TABLE tutor ADD CONSTRAINT tutor_pk PRIMARY KEY ( id );

CREATE TABLE organization(
    id BIGSERIAL NOT NULL,
    name VARCHAR NOT NULL,
    area VARCHAR NOT NULL
);

ALTER TABLE organization ADD CONSTRAINT organization_pk PRIMARY KEY ( id );


