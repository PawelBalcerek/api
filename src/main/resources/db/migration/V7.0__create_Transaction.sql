DROP TABLE IF EXISTS "Transaction" CASCADE;

CREATE TABLE "Transaction" (
  "ID" int PRIMARY KEY,
  "sell_offer_id" int NOT NULL,
  "buy_offer_id" int NOT NULL,
  "date" timestamp NOT NULL,
  "amount" int NOT NULL,
  "price" numeric(10,4) NOT NULL
);