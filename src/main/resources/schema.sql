DROP TABLE IF EXISTS shipping_requests;

CREATE TABLE shipping_requests (
   id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
   vehicle_type VARCHAR(50) NOT NULL,
   strategy_type VARCHAR(50) NOT NULL,
   destination VARCHAR(255) NOT NULL,
   payload_weight DOUBLE PRECISION NOT NULL,
   status VARCHAR(20) NOT NULL,
   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);