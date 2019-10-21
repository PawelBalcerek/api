DROP TABLE IF EXISTS "Resources" CASCADE;

CREATE TABLE "Resources" (
  "ID" int PRIMARY KEY,
  "user_id" int NOT NULL,
  "comp_id" int NOT NULL,
  "amount" int NOT NULL
);
