-- Create User table
CREATE TABLE User (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    role VARCHAR(20) DEFAULT 'CUSTOMER'
);

-- Create Hotel table
CREATE TABLE Hotel (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    location VARCHAR(100) NOT NULL,
    description TEXT,
    number_of_available_rooms INT
);

-- Create Booking table
CREATE TABLE Booking (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    hotel_id BIGINT NOT NULL,
    date DATE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES User(id),
    FOREIGN KEY (hotel_id) REFERENCES Hotel(id)
);

-- Sample data for User table
INSERT INTO User (email, password, first_name, last_name, role)
VALUES
    ('user1@example.com', 'password1', 'John', 'Doe', 'CUSTOMER'),
    ('user2@example.com', 'password2', 'Jane', 'Doe', 'HOTEL_MANAGER'),
    ('user3@example.com', 'password3', 'Alice', 'Smith', 'ADMIN');

-- Sample data for Hotel table
INSERT INTO Hotel (name, location, description, number_of_available_rooms)
VALUES
    ('Hotel A', 'Location A', 'Description A', 10),
    ('Hotel B', 'Location B', 'Description B', 15),
    ('Hotel C', 'Location C', 'Description C', 20);

-- Sample data for Booking table
INSERT INTO Booking (user_id, hotel_id, date)
VALUES
    (1, 1, '2024-04-15'),
    (2, 2, '2024-04-20'),
    (3, 3, '2024-04-25');
