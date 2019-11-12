DROP TABLE IF EXISTS "buy_offers" CASCADE;

CREATE TABLE "buy_offers" (
  "id" SERIAL PRIMARY KEY,
  "resource_id" SERIAL NOT NULL,
  "amount" INT NOT NULL,
  "max_price" Float8 NOT NULL,
  "date" TIMESTAMP WITH TIME ZONE NOT NULL,
  "is_valid" boolean NOT NULL,
  "start_amount" INT NOT NULL
);