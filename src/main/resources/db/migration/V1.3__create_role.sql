CREATE TABLE roles (
    id                   SERIAL NOT NULL,
    name                 VARCHAR(30) not null unique,
    allowed_resource     VARCHAR(200),
    allowed_read         BOOLEAN not null default false ,
    allowed_create       BOOLEAN not null default false ,
    allowed_update       BOOLEAN not null default false ,
    allowed_delete       BOOLEAN not null default false
);

ALTER TABLE roles ADD CONSTRAINT roles_pk PRIMARY KEY ( id );

CREATE TABLE users_roles (
    user_id    INTEGER NOT NULL,
    role_id    INTEGER NOT NULL
);

ALTER TABLE users_roles
    ADD CONSTRAINT users_fk FOREIGN KEY ( user_id )
        REFERENCES students ( id );

ALTER TABLE users_roles
    ADD CONSTRAINT role_fk FOREIGN KEY ( role_id )
        REFERENCES roles ( id );