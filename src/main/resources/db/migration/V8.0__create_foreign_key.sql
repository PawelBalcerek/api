ALTER TABLE resources ADD FOREIGN KEY ("user_id") REFERENCES users (id);

ALTER TABLE resources ADD FOREIGN KEY ("comp_id") REFERENCES companies (id);

ALTER TABLE "sell_offers" ADD FOREIGN KEY ("resource_id") REFERENCES resources (id);

ALTER TABLE "buy_offers" ADD FOREIGN KEY ("resource_id") REFERENCES resources (id);

ALTER TABLE "transactions" ADD FOREIGN KEY ("sell_offer_id") REFERENCES "sell_offers" (id);

ALTER TABLE "transactions" ADD FOREIGN KEY ("buy_offer_id") REFERENCES "buy_offers" (id);