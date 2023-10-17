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
INSERT INTO Product (name, image, description, stock, price, width, depth, height, weight)
VALUES ('Nordic Delights Box',
        'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQyFnbOSJ2iZlu9dW_34estkndTZUsos2BGVdkgXsEzCLJS_1NVWoCk55_4ugxCk6Pgj8A&usqp=CAU',
        'Gourmet Scandinavian treats such as lingonberry preserves, crispbread, smoked salmon, cloudberry jam, and artisan chocolates. Surprise: A traditional Scandinavian recipe card or a unique Nordic kitchen gadget.',
        100, 49.99, 20, 20, 10, 2),
       ('Viking Legends Box', 'https://i.etsystatic.com/15224771/r/il/fb1a25/4231262185/il_1588xN.4231262185_nae0.jpg',
        'Items inspired by Viking history and mythology, like a Thor''s hammer pendant, rune stone coasters, a mead-making kit, and a historical novel set in the Viking age. Surprise: A mini book of Viking sagas or a hand-carved Viking figurine.',
        100, 59.99, 20, 20, 10, 3),
       ('Hygge Home Box',
        'https://hellosubscription.com/wp-content/uploads/2022/11/hygge-box-october-2022-8.jpeg?quality=90&strip=all',
        'Cozy essentials for creating a warm and inviting atmosphere, such as scented candles, soft blankets, mugs for hot beverages, and a journal for mindfulness. Surprise: A sachet of traditional Nordic mulled spice mix or a hygge-inspired playlist.',
        100, 44.99, 25, 25, 12, 2.5),
       ('Scandinavian Design Box',
        'https://norlii.com/wp-content/uploads/822C2155-84EA-46DD-98F6-7E362F8173E5-1-e1673943038619.jpg',
        'Chic home decor items that showcase the minimalist elegance of Scandinavian design, like geometric planters, wooden utensils, sleek desk accessories, and abstract art prints. Surprise: A profile of a renowned Scandinavian designer or a guide to incorporating Scandinavian design principles into one’s living space.',
        100, 64.99, 30, 30, 15, 3.5),
       ('Midsummer Magic Box',
        'https://cdn.intelligencebank.com/au/share/NOrD/1VN0z/19dwP/preset=pB6BA/A210BK008_boxed_midsummer_magic_cake_stand_box_front',
        'Celebrate the enchanting Swedish Midsummer festival with items like flower crowns, berry-infused teas, floral-scented skincare products, and a Maypole dance guide. Surprise: A packet of wildflower seeds or a playlist of traditional Midsummer songs.',
        100, 54.99, 25, 25, 10, 2.2),
       ('Arctic Adventure Box', 'https://i.ebayimg.com/images/g/pnMAAOSwfIBkIcIy/s-l1600.jpg',
        'Exploration gear for the rugged adventurer, including a thermos, pocket knife, compass, warm gloves, and a guidebook to Arctic wildlife. Surprise: A collection of stunning Northern Lights photographs or a video message from a Scandinavian adventurer.',
        100, 69.99, 30, 30, 15, 4),
       ('Forest Forager Box',
        'https://shop.fantasticfungi.com/cdn/shop/files/DSC03518-Edit_a7492bae-df0c-45b1-ad27-3f0a45dc9de4_1.jpg?v=1693573877',
        'A foraging kit complete with a field guide, sustainable harvesting tools, dried wild mushrooms, herbal teas, and handmade botanical soaps. Surprise: A personalized note from a Scandinavian foraging expert or a DIY recipe for a wild edible dish.',
        100, 59.99, 25, 25, 12, 3),
       ('Mystical Fjord Box',
        'https://gamezone.no/Media/Cache/Images/7/6/WEB_Image_Fjord_in_Norway_1000_biter_Puslespill_Ra_10216743_box_frontal_export_typ_300_rgb-1804576323_plid_77036.jpeg',
        'Items inspired by the mystery of Scandinavian fjords, such as a fjord-themed jigsaw puzzle, crystal-infused bath salts, a book of Nordic myths, and a fjord landscape art print. Surprise: A mini crystal or gemstone associated with Norse folklore or a handwritten riddle to solve.',
        100, 52.99, 20, 20, 10, 2.5);

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
