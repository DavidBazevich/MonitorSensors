CREATE TABLE IF NOT EXISTS sensor(
    id SERIAL PRIMARY KEY,
    name VARCHAR(30) NOT NULL UNIQUE,
    range_from INT NOT NULL,
    range_to INT NOT NULL,
    type SERIAL REFERENCES type(type_id),
    unit VARCHAR(40) REFERENCES unit(unit_id),
    location VARCHAR(40),
    description VARCHAR(200)
);

CREATE TABLE IF NOT EXISTS type(
    type_id SERIAL PRIMARY KEY,
    name VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS unit(
    unit_id SERIAL PRIMARY KEY,
    name VARCHAR(40) NOT NULL UNIQUE
);


