CREATE TABLE IF NOT EXISTS shipping_requests (
     id SERIAL PRIMARY KEY,
     vehicle_type VARCHAR(50) NOT NULL,
     payload_weight DOUBLE PRECISION NOT NULL,
     velocity INT NOT NULL
);



CREATE TABLE IF NOT EXISTS vehicles (
    id SERIAL PRIMARY KEY,
    type VARCHAR(50) NOT NULL,
    licensePlate VARCHAR(50) NOT NULL,
    capacity INT NOT NULL ,
    speed INT NULL
);



CREATE TABLE IF NOT EXISTS orders (
    id SERIAL PRIMARY KEY,
    customer_name VARCHAR(255) NOT NULL,
    weight DOUBLE PRECISION NOT NULL,
    destination VARCHAR(255) NOT NULL,
    shippingType VARCHAR(50) NOT NULL,
    status VARCHAR(50) NOT NULL,
    cost DOUBLE PRECISION NOT NULL
);
