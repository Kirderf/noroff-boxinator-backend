-- Insert test data for the "user" table
INSERT INTO "users" ("email", "username", "roles", "address", "password")
VALUES
    ('user1@example.com', 'user1', 'User', '123 Main St', 'password1'),
    ('user2@example.com', 'user2', 'User', '456 Elm St', 'password2'),
    ('admin@example.com', 'admin', 'Admin', '789 Oak St', 'adminpassword');



-- Insert test data for the "product" table
INSERT INTO "product" ("name", "description", "stock", "price")
VALUES
    ('Product A', 'Description for Product A', 100, 19.99),
    ('Product B', 'Description for Product B', 50, 29.99),
    ('Product C', 'Description for Product C', 75, 9.99);

-- Insert test data for the "order" table
INSERT INTO "orders" ("confirmed", "id")
VALUES
    (true, 1),
    (false, 2),
    (true, 3);

-- Insert test data for the "products" table
INSERT INTO "products" ("product_id", "user_id", "quantity", "orders_id")
VALUES
    (1, 1, 2, 1),
    (2, 1, 3, 2),
    (3, 2, 1, 3);

-- Insert test data for the "shipment" table
INSERT INTO "shipment" ("order_id", "destination", "billing_address", "status", "shipping_cost", "delivery_instruction", "gift")
VALUES
    (1, '123 Shipping St', '123 Billing St', 'Shipped', 5.99, 'Handle with care', false),
    (2, '456 Shipping St', '456 Billing St', 'In Transit', 7.99, 'Fragile items', true),
    (3, '789 Shipping St', '789 Billing St', 'Delivered', 9.99, 'Leave at the doorstep', false);
