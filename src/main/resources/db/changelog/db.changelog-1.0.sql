CREATE TABLE IF NOT EXISTS sensors(
    id SERIAL PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    range INT ?????,
    type SERIAL REFERENCES sensorType(type_id),
    unit VARCHAR(40) REFERENCES units(unit_id),
    location VARCHAR(40),
    description VARCHAR(200)
);

CREATE TABLE IF NOT EXISTS sensorType(
    type_id SERIAL PRIMARY KEY,
    type VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS units(
    unit_id SERIAL PRIMARY KEY,
    unit VARCHAR(40) NOT NULL UNIQUE
);


