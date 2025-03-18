CREATE TABLE IF NOT EXISTS users(
    id SERIAL PRIMARY KEY,
    name VARCHAR(40) NOT NULL,
    password VARCHAR(128) NOT NULL,
    createAt DATE,
    updateAt DATE
);

CREATE TABLE IF NOT EXISTS roles(
    id SERIAL PRIMARY KEY,
    name VARCHAR(25) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS token(
    id SERIAL PRIMARY KEY,
    token VARCHAR(256),
    createdAt TIMESTAMP,
    expiresAt TIMESTAMP,
    validatedAt TIMESTAMP
);

CREATE TABLE IF NOT EXISTS users_roles(
    users_id SERIAL,
    roles_id SERIAL,
    PRIMARY KEY (users_id, roles_id),
    FOREIGN KEY (users_id) REFERENCES users(id),
    FOREIGN KEY (roles_id) REFERENCES roles(id)
);