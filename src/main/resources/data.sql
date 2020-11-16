DROP TABLE IF EXISTS user cascade;

CREATE TABLE user (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(50) NOT NULL,
  password VARCHAR(50) NOT NULL,
  email VARCHAR(50) NOT NULL,
  name VARCHAR(50) NOT NULL,
  surname VARCHAR(50) NOT NULL,
  phone VARCHAR(50) NOT NULL,
  UNIQUE KEY username (username),
  UNIQUE KEY email (email)
);

DROP TABLE IF EXISTS address cascade;
 
CREATE TABLE address(
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  country VARCHAR(50) NOT NULL,
  city VARCHAR(50) NOT NULL,
  details VARCHAR(150) NOT NULL,
  user_id BIGINT NOT NULL,
  FOREIGN KEY (user_id) REFERENCES user(id)
);

DROP TABLE IF EXISTS book cascade;
 
CREATE TABLE book(
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(50) NOT NULL,
  author VARCHAR(150) NOT NULL,
  price DECIMAL NOT NULL
);

INSERT INTO book
    (title, author, price)
VALUES
    ('The Catcher in the Rye', 'J.D. Salinger', 15.99),
    ('Nine Stories', 'J.D. Salinger', 9.99),
    ('Franny and Zooey', 'J.D. Salinger', 19.64),
    ('The Great Gatsby', 'F. Scott. Fitzgerald', 14.95),
    ('Tender is the Night', 'F. Scott. Fitzgerald', 18.00),
    ('Pride and Prejudice', 'Jane Austen', 29.95)
;

DROP TABLE IF EXISTS stock;
 
CREATE TABLE stock(
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  book_id BIGINT NOT NULL,
  count INT NOT NULL DEFAULT 0,
  FOREIGN KEY (book_id) REFERENCES book(id),
  UNIQUE KEY book_id (book_id)
);

INSERT INTO stock
    (book_id, count)
VALUES
    (1, 15),
    (2, 25),
    (3, 120),
    (4, 90),
    (5, 1),
    (6, 55)
;

 
DROP TABLE IF EXISTS orders cascade;
 
CREATE TABLE orders(
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT NOT NULL,
  order_date DATE NOT NULL,
  total_price DECIMAL NOT NULL,
  status VARCHAR(50) NOT NULL,
  FOREIGN KEY (user_id) REFERENCES user(id)
);

DROP TABLE IF EXISTS order_content;
 
CREATE TABLE order_content(
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  order_id BIGINT NOT NULL,
  book_id BIGINT NOT NULL,
  price DECIMAL NOT NULL,
  count INT NOT NULL,
  FOREIGN KEY (order_id) REFERENCES orders(id),
  FOREIGN KEY (book_id) REFERENCES book(id)
);