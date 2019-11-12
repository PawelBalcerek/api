DROP TABLE IF EXISTS "sell_offers" CASCADE;

CREATE TABLE "sell_offers" (
  "id" SERIAL PRIMARY KEY,
  "resource_id" SERIAL NOT NULL,
  "amount" INT NOT NULL,
  "price" Float8 NOT NULL,
  "date" TIMESTAMP WITH TIME ZONE NOT NULL,
  "is_valid" boolean NOT NULL,
  "start_amount" INT NOT NULL
);