CREATE TABLE users (
    user_name VARCHAR(50) NOT NULL PRIMARY KEY,
    user_pass VARCHAR(100) NOT NULL
);

CREATE TABLE user_roles (
    user_name VARCHAR(50) NOT NULL,
    role_name VARCHAR(50) NOT NULL,
    PRIMARY KEY (user_name, role_name),
    CONSTRAINT fk_user_roles_user FOREIGN KEY (user_name) REFERENCES users(user_name) ON DELETE CASCADE
);

INSERT INTO users (user_name, user_pass) VALUES
('admin', 'admin123'),
('dealer', 'dealer123');

INSERT INTO user_roles (user_name, role_name) VALUES
('admin', 'admin'),
('admin', 'dealer'),
('dealer', 'dealer');