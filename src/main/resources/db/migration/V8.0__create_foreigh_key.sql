ALTER TABLE "Resources" ADD FOREIGN KEY ("user_id") REFERENCES "Users" ("ID");

ALTER TABLE "Resources" ADD FOREIGN KEY ("comp_id") REFERENCES "Companies" ("ID");

ALTER TABLE "Sell_Offers" ADD FOREIGN KEY ("resource_id") REFERENCES "Resources" ("ID");

ALTER TABLE "Buy_Offers" ADD FOREIGN KEY ("resource_id") REFERENCES "Resources" ("ID");

ALTER TABLE "Transaction" ADD FOREIGN KEY ("sell_offer_id") REFERENCES "Sell_Offers" ("ID");

ALTER TABLE "Transaction" ADD FOREIGN KEY ("buy_offer_id") REFERENCES "Buy_Offers" ("ID");
