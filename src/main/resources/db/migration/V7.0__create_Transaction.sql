DROP TABLE IF EXISTS "transactions" CASCADE;

CREATE TABLE "transactions" (
  "id" SERIAL PRIMARY KEY,
  "sell_offer_id" SERIAL NOT NULL,
  "buy_offer_id" SERIAL NOT NULL,
  "date" TIMESTAMP WITH TIME ZONE NOT NULL,
  "amount" SERIAL NOT NULL,
  "price" Float8  NOT NULL
);