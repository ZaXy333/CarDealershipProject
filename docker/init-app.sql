CREATE TABLE cars (
    id BIGSERIAL PRIMARY KEY,
    brand VARCHAR(50) NOT NULL,
    model VARCHAR(50) NOT NULL,
    production_year INT NOT NULL,
    mileage INT NOT NULL,
    price NUMERIC(10, 2) NOT NULL,
    fuel_type VARCHAR(30) NOT NULL
);

INSERT INTO cars (brand, model, production_year, mileage, price, fuel_type) VALUES
('BMW', 'Seria 3 E90', 2010, 215000, 32900.00, 'Benzyna'),
('Audi', 'A4 B8', 2013, 189000, 44500.00, 'Diesel'),
('Toyota', 'Corolla', 2020, 68000, 79900.00, 'Hybryda'),
('Skoda', 'Octavia III', 2017, 142000, 54000.00, 'Diesel'),
('Fiat', 'Grande Punto', 2006, 198000, 9500.00, 'Benzyna');