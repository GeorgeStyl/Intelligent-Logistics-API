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
     ('Titan Logistics', 1250.0, 'Patra, Greece', 'STANDARD', 'DELAYED', 3200.00, CURRENT_TIMESTAMP),
     ('Eco-Green Solutions', 45.8, 'Volos, Greece', 'STANDARD', 'IN_PROGRESS', 115.50, CURRENT_TIMESTAMP),
     ('Athens Tech Hub', 5.2, 'Athens, Greece', 'EXPRESS', 'PENDING', 45.00, CURRENT_TIMESTAMP),
     ('Northern Imports', 3400.0, 'Thessaloniki, Greece', 'STANDARD', 'DELAYED', 5500.00, CURRENT_TIMESTAMP),
     ('Island Traders', 12.5, 'Chania, Greece', 'FREE', 'DELIVERED', 35.00, CURRENT_TIMESTAMP),
     ('Skyline Retail', 150.0, 'Rhodes, Greece', 'STANDARD', 'DELAYED', 420.00, CURRENT_TIMESTAMP),
     ('FastTrack Couriers', 2.0, 'Ioannina, Greece', 'EXPRESS', 'DELIVERED', 25.00, CURRENT_TIMESTAMP),
     ('Aegean Maritime', 5000.0, 'Piraeus, Greece', 'STANDARD', 'PENDING', 8500.00, CURRENT_TIMESTAMP),
     ('Global Pharma', 18.2, 'Larissa, Greece', 'EXPRESS', 'IN_PROGRESS', 65.00, CURRENT_TIMESTAMP),
     ('Mountain Peaks', 75.5, 'Trikala, Greece', 'STANDARD', 'DELIVERED', 180.00, CURRENT_TIMESTAMP),
     ('Solar Energy Co', 220.0, 'Kalamata, Greece', 'STANDARD', 'PENDING', 480.00, CURRENT_TIMESTAMP),
     ('Hellenic Fabrics', 650.0, 'Serres, Greece', 'STANDARD', 'IN_PROGRESS', 1100.00, CURRENT_TIMESTAMP),
     ('Deep Sea Exports', 4200.0, 'Heraklion, Greece', 'STANDARD', 'DELAYED', 7200.00, CURRENT_TIMESTAMP),
     ('Urban Styles', 25.0, 'Kavala, Greece', 'FREE', 'DELIVERED', 45.00, CURRENT_TIMESTAMP);