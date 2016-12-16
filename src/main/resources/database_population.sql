CREATE SCHEMA IF NOT EXISTS coffee;
USE coffee;

CREATE TABLE products(
  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  name VARCHAR(25) NOT NULL ,
  price FLOAT NOT NULL
);

CREATE TABLE coins
(
  id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  value DOUBLE NOT NULL
);

CREATE TABLE cashbox
(
  id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  amount INT DEFAULT 0 NOT NULL,
  coin_id BIGINT,
  CONSTRAINT cashbox_coins_id_fk FOREIGN KEY (coin_id) REFERENCES coins (id)
);

INSERT INTO products (name, price) VALUES ('black coffee', 0.79);
INSERT INTO products (name, price) VALUES ('double coffee', 1.46);
INSERT INTO products (name, price) VALUES ('coffee with milk', 0.93);
INSERT INTO products (name, price) VALUES ('caffe latte', 1.15);
INSERT INTO products (name, price) VALUES ('capucinno', 1.12);
INSERT INTO products (name, price) VALUES ('tea', 0.64);

INSERT INTO coins (value) VALUES (0.01);
INSERT INTO coins (value) VALUES (0.02);
INSERT INTO coins (value) VALUES (0.05);
INSERT INTO coins (value) VALUES (0.1);
INSERT INTO coins (value) VALUES (0.2);
INSERT INTO coins (value) VALUES (0.5);
INSERT INTO coins (value) VALUES (1);
INSERT INTO coins (value) VALUES (2);