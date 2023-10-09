-- Insert test data for the "user" table
INSERT INTO "users" ("email", "username", "roles", "address")
VALUES
    ('user1@example.com', 'user1', 'User', '123 Main St'),
    ('user2@example.com', 'user2', 'User', '456 Elm St'),
    ('guest@example.com', NULL, 'Guest', NULL),
    ('admin@example.com', 'admin', 'Admin', '789 Oak St');

INSERT INTO countries (full_name, short_name, shipping_cost)
VALUES
    ('Australia', 'AU', 50),
    ('Austria', 'AT', 60),
    ('Belgium', 'BE', 70),
    ('Canada', 'CA', 80),
    ('Denmark', 'DK', 90),
    ('Finland', 'FI', 100),
    ('France', 'FR', 50),
    ('Germany', 'DE', 60),
    ('Hong Kong', 'HK', 70),
    ('Iceland', 'IS', 80),
    ('Ireland', 'IE', 90),
    ('Israel', 'IL', 100),
    ('Japan', 'JP', 50),
    ('Liechtenstein', 'LI', 60),
    ('Luxembourg', 'LU', 70),
    ('Malta', 'MT', 80),
    ('Netherlands', 'NL', 90),
    ('New Zealand', 'NZ', 100),
    ('Norway', 'NO', 50),
    ('Singapore', 'SG', 60),
    ('Slovenia', 'SI', 70),
    ('South Korea', 'KR', 80),
    ('Spain', 'ES', 90),
    ('Sweden', 'SE', 100),
    ('Switzerland', 'CH', 50),
    ('United Arab Emirates', 'AE', 60),
    ('United Kingdom', 'UK', 70),
    ('United States of America', 'US', 80);


-- Insert test data for the "product" table
INSERT INTO "product" ("name","image", "description", "stock", "price", "is_active")
VALUES
    ('Product A','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTXJTCRGla4zgMAgZJJ7mX6udkkyL1c7lBva0x3jMm6neiAEw55OuIIoQZkkRnE9YyNfGk&usqp=CAU', 'Description for Product A', 100, 19.99,true),
    ('Product B','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS8HsGaECRBcRwIPKyaGY9Cj04vawwlN9UQsg&usqp=CAU', 'Description for Product B', 50, 29.99,false),
    ('Product C','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQBRBrH1hsEF4q9CDff2WLgf2DPJKXYO28dpQ&usqp=CAU', 'Description for Product C', 75, 9.99,true);


-- Insert test data for the "products" table
INSERT INTO "orders" ("user_id", "status")
VALUES
    ( 1, 'færdig'),
    ( 1, 'blir behandlet'),
    ( 3,  'færdig'),
    ( 2,  'færdig');

-- Insert test data for the "shipment" table
INSERT INTO "shipment" ("order_id", "countries_name", "destination", "billing_address", "delivery_instruction", "gift")
VALUES
    (1, 'Norway' , '123 Shipping St','123 Billing St',  'Handle with care', false),
    (2, 'Norway' ,'456 Shipping St','456 Billing St',  'Fragile items', true),
    (3, 'Norway' ,'789 Shipping St', '789 Billing St',  'Leave at the doorstep', false);


insert into order_product ("orders_id", "product_id", "quantity")
Values
    (1, 1, 3),(1, 2, 3), (2, 1, 2), (3, 1, 1);

-- Assuming you have a table named "countries" with columns "full_name," "short_name," and "shipping_cost"
