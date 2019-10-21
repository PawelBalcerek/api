DROP TABLE IF EXISTS "Sell_Offers" CASCADE;

CREATE TABLE "Sell_Offers" (
  "ID" int PRIMARY KEY,
  "resource_id" int NOT NULL,
  "amount" int NOT NULL,
  "price" numeric(10,4) NOT NULL,
  "date" timestamp NOT NULL,
  "is_valid" boolean NOT NULL,
  "start_amount" int NOT NULL
);