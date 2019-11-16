CREATE DATABASE mutant
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Spanish_Spain.1252'
    LC_CTYPE = 'Spanish_Spain.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

CREATE TABLE subject (
   id BIGSERIAL PRIMARY KEY,
   dna VARCHAR(200) NOT NULL,
   mutant BOOLEAN NOT NULL,
   creationdate TIMESTAMP NOT NULL
)