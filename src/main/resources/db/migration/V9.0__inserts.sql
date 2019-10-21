INSERT INTO users ( name,email,password)
VALUES('szymon961113','szymon961113@gmail.com', '$2a$10$70l0F1jKAKoZt9avkuNNUuBQsVp5aYdL3zyUShkjiizQ4Uwg9l8/y');

INSERT INTO companies(name, user_id)
VALUES('company 1', 1);

INSERT INTO resources(user_id,comp_id,amount)
VALUES(1,1,100);

INSERT INTO public.sell_offers(
	resource_id, amount, price, date, is_valid, start_amount)
	VALUES ( 1, 100, 32.86, current_timestamp, TRUE, 452);

INSERT INTO buy_offers(
	resource_id, amount, max_price, date, is_valid)
	VALUES ( 1, 100, 42.42, current_timestamp, true);

INSERT INTO public.transactions(
	sell_offer_id, buy_offer_id, date, amount, price)
	VALUES (1, 1, current_timestamp, 424, 535.7);