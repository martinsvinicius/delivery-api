-- kitchens
insert into kitchen (name) values ('Tailandesa');
insert into kitchen (name) values ('Indiana');

-- restaurants
insert into restaurant (delivery_price_rate, name, kitchen_id) values ( 10, 'Thai Gourmet', 1);
insert into restaurant (delivery_price_rate, name, kitchen_id) values (9.50, 'Thai Delivery', 1);
insert into restaurant (delivery_price_rate, name, kitchen_id) values (15, 'Tuk Tuk Indian Food', 2);

-- states
insert into public.state (name) values ('RIO GRANDE DO NORTE');
insert into public.state (name) values ('SÃO PAULO');
