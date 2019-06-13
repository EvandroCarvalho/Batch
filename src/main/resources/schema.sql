DROP TABLE customer IF EXISTS;

CREATE TABLE customer (
	customer_id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    name VARCHAR(100),
    cpf VARCHAR(11),
    phone VARCHAR(11),
    address VARCHAR(200)
    );