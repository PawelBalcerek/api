DROP TABLE IF EXISTS "Buy_Offers" CASCADE;

CREATE TABLE "Buy_Offers" (
  "ID" int PRIMARY KEY,
  "resource_id" int NOT NULL,
  "amount" int NOT NULL,
  "max_price" numeric(10,4) NOT NULL,
  "date" timestamp NOT NULL,
  "is_valid" boolean NOT NULL
);