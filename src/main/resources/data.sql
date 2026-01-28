INSERT INTO vehicles (type, license_plate, capacity, speed, created_at)
VALUES
    ('TRUCK', 'GR-TX-9921', 2000, 90, CURRENT_TIMESTAMP),
    ('VAN', 'GR-VN-4432', 800, 110, CURRENT_TIMESTAMP),
    ('MOTORCYCLE', 'GR-MC-1122', 50, 120, CURRENT_TIMESTAMP),
    ('DRONE', 'DRN-001-B', 10, 45, CURRENT_TIMESTAMP),
    ('TRUCK', 'GR-TX-5566', 2500, 85, CURRENT_TIMESTAMP)
ON CONFLICT (license_plate) DO NOTHING;

INSERT INTO orders (customer_name, weight, destination, shipping_type, status, cost, created_at) VALUES
    ('Alpha Corp', 120.5, 'Athens, Greece', 'EXPRESS', 'PENDING', 450.00, CURRENT_TIMESTAMP),
    ('Beta Logistics', 500.0, 'Thessaloniki, Greece', 'STANDARD', 'IN_PROGRESS', 1200.50, CURRENT_TIMESTAMP),
    ('Gamma Retail', 15.2, 'Patra, Greece', 'ECONOMY', 'DELIVERED', 55.00, CURRENT_TIMESTAMP),
    ('Delta Manufacturing', 2200.0, 'Heraklion, Greece', 'HEAVY_LOAD', 'PENDING', 4500.00, CURRENT_TIMESTAMP),
    ('Epsilon Tech', 2.5, 'Larissa, Greece', 'EXPRESS', 'IN_PROGRESS', 25.00, CURRENT_TIMESTAMP)
ON CONFLICT DO NOTHING;