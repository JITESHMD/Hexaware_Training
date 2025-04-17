CREATE DATABASE car_rental;
USE car_rental;

-- Customers
CREATE TABLE customers (
    customer_id INT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(100),
    phone VARCHAR(15),
    model VARCHAR(50)
);

INSERT INTO customers (customer_id, first_name, last_name, email, phone, model) VALUES
(1, 'Rahul', 'Sharma', 'rahul.sharma@example.com', '9876543210', 'Swift'),
(2, 'Priya', 'Verma', 'priya.verma@example.com', '8765432109', 'Baleno'),
(3, 'Amit', 'Patel', 'amit.patel@example.com', '9988776655', 'Creta'),
(4, 'Sneha', 'Reddy', 'sneha.reddy@example.com', '9090909090', 'i20'),
(5, 'Vikram', 'Singh', 'vikram.singh@example.com', '9123456780', 'XUV500'),
(6, 'Neha', 'Joshi', 'neha.joshi@example.com', '9988001122', 'Ertiga'),
(7, 'Rohan', 'Mehta', 'rohan.mehta@example.com', '9345612876', 'City'),
(8, 'Anjali', 'Kumar', 'anjali.kumar@example.com', '9567890123', 'Venue'),
(9, 'Siddharth', 'Desai', 'siddharth.desai@example.com', '9001234567', 'Kiger'),
(10, 'Pooja', 'Nair', 'pooja.nair@example.com', '9898989898', 'Altroz');

select * from customers;

-- Cars
CREATE TABLE cars (
    car_id INT PRIMARY KEY AUTO_INCREMENT,
    model VARCHAR(50) NOT NULL,
    brand VARCHAR(50) NOT NULL,
    daily_rate DECIMAL(10,2) NOT NULL,
    monthly_rate DECIMAL(10,2) NOT NULL,
    available BOOLEAN NOT NULL
);

INSERT INTO cars (model, brand, daily_rate, monthly_rate, available) VALUES
('Swift', 'Maruti Suzuki', 1200.00, 25000.00, true),
('i20', 'Hyundai', 1500.00, 28000.00, true),
('Altroz', 'Tata', 1300.00, 27000.00, true),
('City', 'Honda', 1800.00, 35000.00, true),
('Ertiga', 'Maruti Suzuki', 1700.00, 32000.00, false),
('XUV300', 'Mahindra', 1600.00, 31000.00, true),
('Venue', 'Hyundai', 1550.00, 30000.00, true),
('Nexon', 'Tata', 1650.00, 32000.00, true),
('Baleno', 'Maruti Suzuki', 1400.00, 29000.00, false),
('Verna', 'Hyundai', 1750.00, 34000.00, true);

select * from cars;


-- Leases


CREATE TABLE leases (
    lease_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT,
    car_id INT,
    start_date DATE,
    end_date DATE,
    lease_type VARCHAR(20), -- "Daily" or "Monthly"
    total_cost DOUBLE,
    is_active BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id),
    FOREIGN KEY (car_id) REFERENCES cars(car_id)
);




INSERT INTO leases (customer_id, car_id, start_date, end_date, lease_type, total_cost, is_active) VALUES
(1, 1, '2025-04-01', '2025-04-10', 'Daily', 9000, TRUE),
(2, 2, '2025-03-15', '2025-04-15', 'Monthly', 18000, TRUE),
(3, 3, '2025-02-01', '2025-02-20', 'Daily', 10000, FALSE),
(4, 4, '2025-03-01', '2025-03-30', 'Monthly', 17000, TRUE),
(5, 5, '2025-01-10', '2025-01-25', 'Daily', 7500, FALSE),
(6, 6, '2025-04-05', '2025-04-25', 'Daily', 11000, TRUE),
(7, 7, '2025-02-10', '2025-03-10', 'Monthly', 16000, FALSE),
(8, 8, '2025-03-20', '2025-04-18', 'Monthly', 19000, TRUE),
(9, 9, '2025-01-01', '2025-01-15', 'Daily', 8500, FALSE),
(10, 10, '2025-04-01', '2025-04-20', 'Daily', 9500, TRUE);

select * from leases;


CREATE TABLE payments (
    payment_id INT PRIMARY KEY AUTO_INCREMENT,
    lease_id INT NOT NULL,
    amount_paid DOUBLE NOT NULL,
    payment_date DATE NOT NULL DEFAULT (CURRENT_DATE),
    FOREIGN KEY (lease_id) REFERENCES leases(lease_id)
);

INSERT INTO payments (lease_id, amount_paid, payment_date) VALUES
(1, 9000, '2025-04-01'),
(2, 18000, '2025-03-15'),
(3, 10000, '2025-02-01'),
(4, 17000, '2025-03-01'),
(5, 7500, '2025-01-10'),
(6, 11000, '2025-04-05'),
(7, 16000, '2025-02-10'),
(8, 19000, '2025-03-20'),
(9, 8500, '2025-01-01'),
(10, 9500, '2025-04-01');

select * from payments;
