-- Insert test data for the "user" table
INSERT INTO "users" ("email", "username", "roles", "address")
VALUES
    ('user1@example.com', 'user1', 'User', '123 Main St'),
    ('user2@example.com', 'user2', 'User', '456 Elm St'),
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
INSERT INTO "product" ("name","image", "description", "stock", "price")
VALUES
    ('Product A','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTXJTCRGla4zgMAgZJJ7mX6udkkyL1c7lBva0x3jMm6neiAEw55OuIIoQZkkRnE9YyNfGk&usqp=CAU', 'Description for Product A', 100, 19.99),
    ('Product B','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS8HsGaECRBcRwIPKyaGY9Cj04vawwlN9UQsg&usqp=CAU', 'Description for Product B', 50, 29.99),
    ('Product C','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQBRBrH1hsEF4q9CDff2WLgf2DPJKXYO28dpQ&usqp=CAU', 'Description for Product C', 75, 9.99);


-- Insert test data for the "products" table
INSERT INTO "orders" ("user_id", "status")
VALUES
    ( 1, 'færdig'),
    ( 1, 'blir behandlet'),
    ( 3,  'færdig'),
    ( null,  'færdig'),
    ( 2,  'færdig');

-- Insert test data for the "shipment" table
INSERT INTO Shipment
(order_id, countries_name, destination, billing_address, postal_code, city, phone_number, delivery_instruction, gift, email)
VALUES
    (1, 'United States of America', '123 Main St', '456 Elm St', 12345, 'New York', 1234567890, 'Leave at front door', true,'john.doe@example.com'),
    (2, 'Canada', '789 Maple St', '101 Pine St', 67890, 'Toronto', 2345678901, 'Ring doorbell twice', false,'jane.smith@example.ca'),
    (3, 'United Kingdom', '234 Oak St', '567 Birch St', 11223, 'London', 3456789012, NULL, false,'william.jones@example.co.uk');

ALTER TABLE  orders_products ADD column quantity integer; --TODO Sjækk om dæ kan fikses bedre.

insert into orders_products ("orders_id", "products_id", "quantity")
Values
    (1, 1, 3),(1, 2, 3), (2, 1, 2), (3, 1, 1);
