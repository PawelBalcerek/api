DROP TABLE IF EXISTS resources CASCADE;

CREATE TABLE resources (
  "id" SERIAL PRIMARY KEY,
  "user_id" SERIAL NOT NULL,
  "comp_id" SERIAL NOT NULL,
  "amount" INT NOT NULL
);
