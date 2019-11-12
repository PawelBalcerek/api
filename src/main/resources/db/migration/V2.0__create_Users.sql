DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE users (
  "id" SERIAL PRIMARY KEY,
  "name" varchar(255) NOT NULL,
  "email" varchar(255) NOT NULL,
  "password" varchar(255) NOT NULL,
  "cash" Float8 NOT NULL
);
