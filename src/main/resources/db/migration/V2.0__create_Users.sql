DROP TABLE IF EXISTS "Users" CASCADE;

CREATE TABLE "Users" (
  "ID" SERIAL PRIMARY KEY,
  "name" varchar NOT NULL,
  "email" varchar NOT NULL,
  "password" varchar NOT NULL
);
