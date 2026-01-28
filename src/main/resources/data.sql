INSERT INTO vehicles (type, license_plate, capacity, speed)
VALUES
    ('TRUCK', 'GR-TX-9921', 2000, 90),
    ('VAN', 'GR-VN-4432', 800, 110),
    ('MOTORCYCLE', 'GR-MC-1122', 50, 120),
    ('DRONE', 'DRN-001-B', 10, 45),
    ('TRUCK', 'GR-TX-5566', 2500, 85)
ON CONFLICT (license_plate) DO NOTHING;

INSERT INTO orders (customer_name, weight, destination, shipping_type, status, cost) VALUES
    ('Alpha Corp', 120.5, 'Athens, Greece', 'EXPRESS', 'PENDING', 450.00),
    ('Beta Logistics', 500.0, 'Thessaloniki, Greece', 'STANDARD', 'IN_PROGRESS', 1200.50),
    ('Gamma Retail', 15.2, 'Patra, Greece', 'ECONOMY', 'DELIVERED', 55.00),
    ('Delta Manufacturing', 2200.0, 'Heraklion, Greece', 'HEAVY_LOAD', 'PENDING', 4500.00),
    ('Epsilon Tech', 2.5, 'Larissa, Greece', 'EXPRESS', 'IN_PROGRESS', 25.00)
ON CONFLICT DO NOTHING;