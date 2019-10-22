DROP TABLE IF EXISTS configurations CASCADE;

CREATE TABLE configurations (
  "name" varchar(255) PRIMARY KEY,
  "number" INT NOT NULL
);
