INSERT INTO users (username, password, email, phone, role)
SELECT 'admin1', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuD7.7dA5V7V7V7V7V7V7V7V7V7V7V7', 'admin1@ccs.com', '01000000001', 'ROLE_ADMIN'
WHERE NOT EXISTS (SELECT 1 FROM users WHERE username = 'admin1');

INSERT INTO users (username, password, email, phone, role)
SELECT 'admin2', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuD7.7dA5V7V7V7V7V7V7V7V7V7V7', 'admin2@ccs.com', '01000000002', 'ROLE_ADMIN'
WHERE NOT EXISTS (SELECT 1 FROM users WHERE username = 'admin2');