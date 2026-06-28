CREATE EXTENSION btree_gist;

CREATE TABLE users (
    id integer PRIMARY KEY,
    name varchar(100) CHECK (name <> '') NOT NULL
);

CREATE TABLE meetings (
    id integer PRIMARY KEY,
    title varchar(100) CHECK (title <> '') NOT NULL,
    description varchar(200)
);

-- https://www.postgresql.org/docs/current/rangetypes.html#RANGETYPES-CONSTRAINT
-- https://www.postgresql.org/docs/current/ddl-constraints.html#DDL-CONSTRAINTS-EXCLUSION
CREATE TABLE slots (
    id integer PRIMARY KEY,
    uid integer REFERENCES users,
    mid integer REFERENCES meetings,
    start_end tstzrange NOT NULL,
    EXCLUDE USING GIST (uid WITH =, start_end WITH &&)
);
