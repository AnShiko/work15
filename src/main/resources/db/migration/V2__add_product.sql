CREATE TABLE products (
    id serial PRIMARY KEY,
    title VARCHAR(100),
    price NUMERIC
);

INSERT INTO products (title, price) VALUES ('Молоко', 70), ('Масло', 100), ('Хлеб', 30);