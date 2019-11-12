DROP TABLE IF EXISTS companies CASCADE;

CREATE TABLE companies (
  "id" SERIAL PRIMARY KEY,
  "name" varchar(255) NOT NULL
);