-- Insert test data for the "user" table
INSERT INTO "users" ("id", "email", "username", "address")
VALUES ('97bf334f-6c68-44b8-8209-229774482903', 'user1@example.com', 'user1', '123 Main St'),
       ('97bf334f-6c68-44b8-8209-229774482902', 'user2@example.com', 'user2', '456 Elm St'),
       ('97bf334f-6c68-44b8-8209-229774482901', 'admin@example.com', 'admin', '789 Oak St'),
       ('97bf334f-6c68-44b8-8209-229774482906', 'john.doe@example.com', 'JohnDoe', '123 Elm St, Springfield'),
       ('97bf334f-6c68-44b8-8209-229774482905', 'jane.doe@example.com', 'JaneDoe', '456 Maple St, Springfield'),
       ('97bf334f-6c68-44b8-8209-229774482907', 'alice.smith@example.com', 'AliceSmith', '789 Birch St, Springfield'),
       ('97bf334f-6c68-44b8-8209-229774482908', 'bob.jones@example.com', 'BobJones', '101 Pine St, Springfield'),
       ('97bf334f-6c68-44b8-8209-229774482909', 'charlie.brown@example.com', 'CharlieBrown', '202 Oak St, Springfield');


INSERT INTO countries (full_name, short_name, shipping_cost)
VALUES ('Australia', 'AU', 50),
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
INSERT INTO "product" ("name", "image", "description", "stock", "price", "is_active", "width", "height", "depth",
                       "weight")
VALUES ('Product A',
        'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTXJTCRGla4zgMAgZJJ7mX6udkkyL1c7lBva0x3jMm6neiAEw55OuIIoQZkkRnE9YyNfGk&usqp=CAU',
        'Description for Product A', 100, 19.99, true, 1, 0, 0, 0),
       ('Product B',
        'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS8HsGaECRBcRwIPKyaGY9Cj04vawwlN9UQsg&usqp=CAU',
        'Description for Product B', 50, 29.99, false, 0, 2, 0, 0),
       ('Product C',
        'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQBRBrH1hsEF4q9CDff2WLgf2DPJKXYO28dpQ&usqp=CAU',
        'Description for Product C', 75, 9.99, true, 0, 0, 3, 0),
       ('Widget', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQBRBrH1hsEF4q9CDff2WLgf2DPJKXYO28dpQ&usqp=CAU',
        'A standard widget.', 100, 19.99, TRUE, 10, 10, 10, 500),
       ('Gadget', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQBRBrH1hsEF4q9CDff2WLgf2DPJKXYO28dpQ&usqp=CAU',
        'A fancy gadget.', 50, 29.99, TRUE, 5, 5, 5, 300);

INSERT INTO shipment (countries_name, email, shipping_address, billing_address, status, postal_code, city,
                      phone_number, delivery_instruction, gift, user_id)
VALUES ('US', 'john.doe@example.com', '789 Birch St, Springfield', '123 Elm St, Springfield', 0, 12345,
        'Springfield', '1234567890', 'Leave at door', FALSE, '97bf334f-6c68-44b8-8209-229774482903'),
       ('CA', 'jane.doe@example.com', '333 Cedar St, Vancouver', '456 Maple St, Springfield', 1, 54321, 'Vancouver',
        '2345678901', 'Knock twice', TRUE, '97bf334f-6c68-44b8-8209-229774482902'),
       ('UK', 'alice.smith@example.com', '444 Willow St, London', '789 Birch St, Springfield', 2, 67890, 'London',
        '3456789012', 'Call before delivery', FALSE, '97bf334f-6c68-44b8-8209-229774482905'),
       ('FR', 'bob.jones@example.com', '555 Poplar St, Paris', '101 Pine St, Springfield', 3, 78901, 'Paris',
        '4567890123', 'Leave with neighbor', TRUE, '97bf334f-6c68-44b8-8209-229774482906'),
       ('DE', 'charlie.brown@example.com', '666 Spruce St, Berlin', '202 Oak St, Springfield', 1, 89012, 'Berlin',
        '5678901234', 'Ring bell', FALSE, '97bf334f-6c68-44b8-8209-229774482908');

-- Assuming the shipments generated IDs from '1' to '5' and have different statuses
INSERT INTO shipment_history (shipment_id, status, timestamp)
VALUES (1, 0, current_timestamp),
       (2, 1, current_timestamp),
       (3, 2, current_timestamp),
       (4, 3, current_timestamp),
       (5, 1, current_timestamp);


-- Assuming the Orders generated IDs from '1' to '5' and products have IDs from '1' to '5'
INSERT INTO shipment_product (shipment_id, product_id, quantity)
VALUES (1, 1, 5),
       (2, 2, 3),
       (3, 3, 4),
       (4, 4, 2),
       (5, 5, 6);
