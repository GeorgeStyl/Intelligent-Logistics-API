INSERT INTO vehicles (type, license_plate, capacity, speed, created_at) VALUES
    ('TRUCK', 'GR-TX-1020', 3000, 85, CURRENT_TIMESTAMP),
    ('TRUCK', 'GR-TX-4455', 2800, 88, CURRENT_TIMESTAMP),
    ('TRUCK', 'GR-TX-8899', 3200, 82, CURRENT_TIMESTAMP),
    ('TRUCK', 'GR-TX-1122', 2900, 90, CURRENT_TIMESTAMP),
    ('TRUCK', 'GR-TX-3344', 3100, 87, CURRENT_TIMESTAMP),
    ('VAN', 'GR-VN-2030', 900, 105, CURRENT_TIMESTAMP),
    ('VAN', 'GR-VN-5566', 850, 115, CURRENT_TIMESTAMP),
    ('VAN', 'GR-VN-7788', 950, 112, CURRENT_TIMESTAMP),
    ('VAN', 'GR-VN-9900', 800, 108, CURRENT_TIMESTAMP),
    ('VAN', 'GR-VN-4433', 880, 110, CURRENT_TIMESTAMP),
    ('DRONE', 'DRN-003-C', 15, 55, CURRENT_TIMESTAMP),
    ('DRONE', 'DRN-004-D', 12, 60, CURRENT_TIMESTAMP),
    ('DRONE', 'DRN-005-E', 20, 50, CURRENT_TIMESTAMP),
    ('DRONE', 'DRN-006-F', 10, 65, CURRENT_TIMESTAMP),
    ('DRONE', 'DRN-007-G', 18, 48, CURRENT_TIMESTAMP)
ON CONFLICT (license_plate) DO NOTHING;

INSERT INTO orders (customer_name, weight, destination, shipping_type, status, cost, created_at) VALUES
    ('Alpha Corp', 120.5, 'Athens, Greece', 'EXPRESS', 'PENDING', 450.00, CURRENT_TIMESTAMP),
    ('Beta Logistics', 500.0, 'Thessaloniki, Greece', 'STANDARD', 'IN_PROGRESS', 1200.50, CURRENT_TIMESTAMP),
    ('Gamma Retail', 15.2, 'Patra, Greece', 'ECONOMY', 'DELIVERED', 55.00, CURRENT_TIMESTAMP),
    ('Delta Manufacturing', 2200.0, 'Heraklion, Greece', 'HEAVY_LOAD', 'PENDING', 4500.00, CURRENT_TIMESTAMP),
    ('Epsilon Tech', 2.5, 'Larissa, Greece', 'EXPRESS', 'IN_PROGRESS', 25.00, CURRENT_TIMESTAMP)
ON CONFLICT DO NOTHING;

DELETE FROM vehicles WHERE type = 'MOTORCYCLE';