DROP TABLE IF EXISTS configurations CASCADE;

CREATE TABLE configurations (
  "id" SERIAL PRIMARY KEY,
  "name" varchar(255) UNIQUE NOT NULL,
  "number" INT NOT NULL
);
