DROP TABLE IF EXISTS student CASCADE;
DROP TABLE IF EXISTS tutor CASCADE;
DROP TABLE IF EXISTS  organization CASCADE;


DROP SEQUENCE IF EXISTS student CASCADE;
DROP SEQUENCE IF EXISTS  tutor CASCADE;
DROP SEQUENCE IF EXISTS  organization CASCADE;

CREATE SEQUENCE student_id_seq START WITH 1;
CREATE SEQUENCE tutor_id_seq START WITH 1;
CREATE SEQUENCE organization_id_seq START WITH 1;

CREATE TABLE student(
    id    INTEGER NOT NULL default nextval('student_id_seq'),
    name    VARCHAR(30) NOT NULL,
    email    VARCHAR(50)
);

ALTER TABLE student ADD CONSTRAINT student_pk PRIMARY KEY ( id );


CREATE TABLE tutor(
    id    INTEGER NOT NULL default nextval('tutor_id_seq'),
    name    VARCHAR  NOT NULL
);

ALTER TABLE tutor ADD CONSTRAINT tutor_pk PRIMARY KEY ( id );

CREATE TABLE organization(
    id INTEGER NOT NULL default nextval('organization_id_seq'),
    name VARCHAR NOT NULL
);

ALTER TABLE organization ADD CONSTRAINT organization_pk PRIMARY KEY ( id );


